package com.gethobby.web.hobbyclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gethobby.common.Page;
import com.gethobby.common.Search;
import com.gethobby.service.domain.ClassAssess;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.User;
import com.gethobby.service.hobbyclass.SearchHobbyClassService;

@RestController
@RequestMapping("/searchHobbyClass/*")
public class SearchHobbyClassRestController {
	
	@Autowired
	@Qualifier("searchHobbyClassServiceImpl")
	private SearchHobbyClassService searchHobbyClassService;
	
	@Value("#{hashtagProperties}")
	private Properties hashtag;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	public SearchHobbyClassRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value ="json/getSearchHashtag" )
	public Map<String, List<String>> getSearchHashtag( @RequestBody Map<String, String> categoryCode ) throws Exception {
		String hashtagCode = categoryCode.get("categoryCode");
		System.out.println("------hashtagCode ? : " + hashtagCode);
		Map<String, List<String>> returnMap = new HashMap<String, List<String>>();
		
		List<String> hashtagCodeList = new ArrayList<String>();
		List<String> hashtagNameList = new ArrayList<String>();
		
		for(int i = 0; i < 10; i++) {
			String newHashtagCode = hashtagCode + "0" + i;
			hashtagCodeList.add(newHashtagCode);
			System.out.println("-------newHashtagCode ? : " + newHashtagCode);
			String hashtagString = new String(hashtag.getProperty(newHashtagCode).getBytes("ISO-8859-1"), "utf-8");
			hashtagNameList.add(hashtagString);
		}
		
		returnMap.put("hashtagCodeList", hashtagCodeList);
		returnMap.put("hashtagNameList", hashtagNameList);
		
		return returnMap;
	}
	
	@RequestMapping( value = "json/getHobbyClassList" ) 
	public Map<String, Object> getHobbyClassList( @RequestBody Map<String, Object> jsonMap, HttpSession session ) throws Exception {
		System.out.println(jsonMap.get("search"));
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String mapString = objectMapper.writeValueAsString(jsonMap);
		JSONObject jsonObject = (JSONObject)JSONValue.parse(mapString);
		
		// Search 도메인 ajax로 가져오기 
		Search search = objectMapper.readValue(jsonObject.get("search").toString(), Search.class);
		
		// 클래스 진행상태 가져오기 
		String classState = (String)jsonMap.get("stateValue");
		// 임의로 pageSize 설정 
		search.setPageSize(3);
		
		System.out.println("-------search ? : " + search);
		System.out.println("-------classState ? : " + classState);
		
		// hashtag가 있을 경우 category null 처리 
		if ( search.getHashtag() != null ) {
			search.setCategory(null);
		}
		
		// hashtag 전체 선택 시 hashtag[0] = all이므로 이 경우에 hashtag null처리 
		if ( search.getHashtag().get(0).equals("all") ) {
			search.setHashtag(null);
		}
		
		
		// 클래스 진행상태 0이면 null 처리 
		if ( classState.equals("0") ) {
			classState = null;
		}
		
		System.out.println("-------search ? : " + search);
		System.out.println("-------classState ? : " + classState);
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("search", search);
		inputData.put("classState", classState);
		
		String userId = null;
		User user = (User)session.getAttribute("user");
		
		if ( user != null ) {
			userId = user.getUserId();
		}
		
		// c@c.c가 로그인했다고 가정 
		inputData.put("userId", "c@c.c");
		
		Map<String, Object> map = searchHobbyClassService.getHobbyClassList(inputData);
		List<HobbyClass> hobbyClassList = (List<HobbyClass>)map.get("list");
		
		for(HobbyClass hobbyClass : hobbyClassList) {
			System.out.println("--------hobbyClass ? : " + hobbyClass);
		}
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("total")).intValue(), pageUnit, pageSize);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("hobbyClassList", hobbyClassList);
		returnMap.put("resultPage", resultPage);
		System.out.println("----resultPage ? : " + resultPage);
		return returnMap;
	}
	
	@RequestMapping( value = "json/getHobbyClassAssessContent" )
	public Map<String, Object> getHobbyClassAssessContent( @RequestBody Map<String, String> jsonMap ) throws Exception {
		Search search = new Search();
		search.setCurrentPage(Integer.parseInt(jsonMap.get("currentPage")));
		search.setPageSize(pageSize);
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("hobbyClassNo", jsonMap.get("hobbyClassNo"));
		inputData.put("search", search);
		
		Map<String, Object> map = searchHobbyClassService.getHobbyClassAssessContent(inputData);
		
		List<ClassAssess> listAssessContent = (List<ClassAssess>)map.get("list"); 
		
		for(ClassAssess c : listAssessContent) {
			System.out.println("----------c ? : " + c);
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("listAssessContent", listAssessContent);
		
		return returnMap;
	}
	
	@RequestMapping( value = "json/getPopularHobbyClassList" )
	public Map<String, Object> getPopularHobbyClassList( @RequestBody Map<String, String> jsonMap, HttpSession session ) throws Exception {
		Search search = new Search();
		
		int currentPage = Integer.parseInt(jsonMap.get("currentPage"));
		int maxPage = Integer.parseInt(jsonMap.get("maxPage"));
		
		if( jsonMap.get("check").equals("0") ) {
			currentPage -= 1;
			
			if ( currentPage < 1 ) {
				currentPage = maxPage;
			}
		}
		else if ( jsonMap.get("check").equals("1") ) {
			currentPage += 1;
			
			if ( currentPage > maxPage ) {
				currentPage = 1;
			}
		}
		
		search.setCurrentPage(currentPage);
		search.setPageSize(pageSize);
		
		User user = (User)session.getAttribute("user");
		
		String userId = null;
		
		if ( user != null ) { 
			userId = user.getUserId();
		}
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("search", search);
		inputData.put("userId", userId);
		
		Map<String, Object> map = searchHobbyClassService.getPopularHobbyClassList(inputData);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", map.get("list"));
		returnMap.put("currentPage", currentPage);
		
		return returnMap;
	}
	
}
