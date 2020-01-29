package com.gethobby.service.purchase;

import java.util.List;
import java.util.Map;

import com.gethobby.common.Search;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Purchase;

public interface PurchaseDAO {
	
	public HobbyClass getComponentOption(int hobbyClassNo) throws Exception; // Out : Ŭ���� ����, �غ� ����, �غ� �̸�, Ŭ���� �̸�, Ŭ����id, 
	
	public Purchase getPayment(String userId) throws Exception; // Out : �������(�̸�,�޴���,����ּ�,�������ּ�,�����ȣ) / ������ �Է½� flagó�� ���
	
	public int addPayment(Purchase purchase) throws Exception; // In : �������(�̸�,�޴���,����ּ�,�������ּ�,�����ȣ,��û����), ����ǰ��, ���űݾ�, �������, Ŭ����ID
	
	public List getPaymentHistoryList(String userId) throws Exception; // Out : ����Id, ���Ż���, ��ۻ���, ���Ű���, ����ǰ��, �غ���ü���, Ŭ���� ����, �̹���, ī�װ�, �ؽ��±�
	
	public Purchase getDelivery(String userId) throws Exception; // Out : ������� ���
	
	public int requestRefund(String purchaseId) throws Exception;
	
	// Admin
	public List getPaymentHistoryListAdmin(Search search) throws Exception; // Out : ����ǰ��, ����ID, ȸ��ID,����,�������, ����
	
	public int updateRefund(String purchaseId) throws Exception; 
	
	public int addDeliveryInfo(Purchase purchase) throws Exception;
	
	public int getMoneyAnalysisSum(Map<String, Object> keyWordAndWhat) throws Exception;
	
	public int getMoneyAnalysisAvg(Map<String, Object> keyWordAndWhat) throws Exception;
	
	public int getMoneyAnalysisCount(Map<String, Object> keyWordAndWhat) throws Exception;
	
	public List getCreatorList() throws Exception; // Out : ũ�������� ȸ�� ����Ʈ
	
}//end of interface PurchaseDAO
