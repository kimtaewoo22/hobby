package com.gethobby.service.purchase.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.common.Search;
import com.gethobby.service.domain.AuthData;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Purchase;
import com.gethobby.service.openhobbyclass.OpenHobbyClassDAO;
import com.gethobby.service.openhobbyclass.OpenHobbyClassService;
import com.gethobby.service.purchase.PurchaseDAO;
import com.gethobby.service.purchase.PurchaseService;


@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService{
	
		///Field
		@Autowired
		@Qualifier("purchaseDAOImpl")
		private PurchaseDAO purchaseDAO;
		public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
			this.purchaseDAO = purchaseDAO;
		}
		@Autowired
		@Qualifier("openHobbyClassDAOImpl")
		private OpenHobbyClassDAO openHobbyClassDAO;
		public void setOpenHobbyClassDAO(OpenHobbyClassDAO openHobbyClassDAO) {
			this.openHobbyClassDAO = openHobbyClassDAO;
		}
		@Autowired
		@Qualifier("openHobbyClassServiceImpl")
		private OpenHobbyClassService openHobbyClassService;
		public void setOpenHobbyClassService(OpenHobbyClassService openHobbyClassService) {
			this.openHobbyClassService = openHobbyClassService;
		}
		
		
		///Constructor
		public PurchaseServiceImpl() {
			System.out.println(this.getClass());
		}
			
		public HobbyClass getComponentOption(int hobbyClassNo) throws Exception {
			return purchaseDAO.getComponentOption(hobbyClassNo);
		}

		public Purchase getPayment(String userId) throws Exception {
			Purchase purchase = purchaseDAO.getDelivery(userId);
			
			if(purchase == null) {
				return purchaseDAO.getPayment(userId);
			}
			return purchase;
		}

		public int addPayment(Purchase purchase) throws Exception {
			openHobbyClassService.updateTotalMoney(purchase.getHobbyClass().getHobbyClassNo(), purchase.getPurchasePrice(), "1");
			openHobbyClassService.updateTotalStudent(purchase.getHobbyClass().getHobbyClassNo(), 1, "1");
			return purchaseDAO.addPayment(purchase);
		}

		public List getPaymentHistoryList(String userId) throws Exception {
			List returnList = new ArrayList();		
			List purchaseList = purchaseDAO.getPaymentHistoryList(userId);
			for(int i=0; i<purchaseList.size(); i++) {
				Map<String, Object> returnMap = new HashMap<String, Object>();
				returnMap.put("purchase", purchaseList.get(i));
				returnMap.put("hobbyclass", openHobbyClassService.getHobbyClass(((Purchase)purchaseList.get(i)).getHobbyClass().getHobbyClassNo(), "510"));
				if( ((Purchase)purchaseList.get(i)).getComponentOption().equals("1")){
					returnMap.put("kit", openHobbyClassService.getHobbyClass(((Purchase)purchaseList.get(i)).getHobbyClass().getHobbyClassNo(), "530"));
				}
				returnList.add(returnMap);
			}
			return returnList;
		}

		public Purchase getDelivery(String userId) throws Exception {
			return purchaseDAO.getDelivery(userId);
		}

		public int requestRefund(Purchase purchase) throws Exception {
			openHobbyClassService.updateTotalMoney(purchase.getHobbyClass().getHobbyClassNo(), purchase.getPurchasePrice(), "0");
			openHobbyClassService.updateTotalStudent(purchase.getHobbyClass().getHobbyClassNo(), 1, "0");                                                          
					
			return purchaseDAO.requestRefund(purchase.getPurchaseId());
		}
		
		public boolean complete(Purchase purchase) throws Exception {
			String API_URL = "https://api.iamport.kr";
			String imp_key = "";
			String imp_secret = "";
			
			//Get AccessToken
			HttpClient httpClient = new DefaultHttpClient();
			String url = "https://api.iamport.kr";
			HttpPost httpPost = new HttpPost(API_URL+"/users/getToken");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Connection","keep-alive");
			httpPost.setHeader("Content-Type", "application/json");
			
			ObjectMapper objectMapper = new ObjectMapper();
			AuthData authData = new AuthData(imp_key, imp_secret);
			String data = objectMapper.writeValueAsString(authData);
			StringEntity httpEntity = new StringEntity(data);
			httpPost.setEntity(httpEntity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity responseHttpEntity = httpResponse.getEntity();
			InputStream is = responseHttpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

			String temp="";
			String response="";
			while( (temp = br.readLine()) != null) {
				response += temp;
			}
			JSONObject jsonObj = (JSONObject)JSONValue.parse(((JSONObject)JSONValue.parse(response)).get("response").toString());
			String token  = jsonObj.get("access_token").toString();
			
			//Get Payment
			HttpClient paymentHttpClient = new DefaultHttpClient();
			String paymentUrl = API_URL+"/payments/"+purchase.getPurchaseId();
			HttpGet paymentHttpGet = new HttpGet(paymentUrl);
			paymentHttpGet.addHeader("Accept", "application/json");
			paymentHttpGet.addHeader("Authorization", token);
			
			HttpResponse paymentHttpResponse = paymentHttpClient.execute(paymentHttpGet);
			HttpEntity paymentHttpEntity = paymentHttpResponse.getEntity();
			InputStream paymentIs = paymentHttpEntity.getContent();
			BufferedReader paymentBr = new BufferedReader(new InputStreamReader(paymentIs,"UTF-8"));
			
			// check everythings_fine
			if (paymentHttpResponse.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + paymentHttpResponse.getStatusLine().getStatusCode());
			}
			
			String paymentTemp="";
			String paymentResponse="";
			while( (paymentTemp = paymentBr.readLine()) != null) {
				paymentResponse+= paymentTemp;
			}
			JSONObject jsonObj11 = (JSONObject)JSONValue.parse(paymentResponse);
			ObjectMapper finalObjectMapper = new ObjectMapper();
			HashMap paymentMap = finalObjectMapper.readValue(jsonObj11.get("response").toString(), HashMap.class);

			purchase.setPayMethod(paymentMap.get("pay_method").toString());
			purchase.setPurchasePrice((Integer)paymentMap.get("amount"));
			System.out.println(purchase.toString());
			
			return true;
		}

		public List getPaymentHistoryListAdmin(Search search) throws Exception {
			return purchaseDAO.getPaymentHistoryListAdmin(search);
		}

		public int updateRefund(String purchaseId) throws Exception {
			return purchaseDAO.updateRefund(purchaseId);
		}

		public int addDeliveryInfo(Purchase purchase) throws Exception {
			return purchaseDAO.addDeliveryInfo(purchase);
		}

		public int getMoneyAnalysisSum(Map<String, Object> keyWordAndWhat) throws Exception {
			return purchaseDAO.getMoneyAnalysisSum(keyWordAndWhat);
		}

		public int getMoneyAnalysisAvg(Map<String, Object> keyWordAndWhat) throws Exception {
			return purchaseDAO.getMoneyAnalysisAvg(keyWordAndWhat);
		}
		
		public int getMoneyAnalysisCount(Map<String, Object> keyWordAndWhat) throws Exception {
			return purchaseDAO.getMoneyAnalysisCount(keyWordAndWhat);
		}

}
