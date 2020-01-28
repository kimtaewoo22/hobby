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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gethobby.common.Search;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.User;
import com.gethobby.service.hobbyclass.SearchHobbyClassService;

@RestController
@RequestMapping("/hobbyClass/*")
public class SearchHobbyClassRestController {
	
	@Autowired
	@Qualifier("searchHobbyClassServiceImpl")
	private SearchHobbyClassService searchHobbyClassService;
	
	@Value("#{hashtagProperties}")
	private Properties hashtag;
	
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
		
		// 임의로 pageSize 설정 
		search.setPageSize(10);
		
		System.out.println("-------search ? : " + search);
		
		// hashtag가 있을 경우 category null 처리 
		if ( search.getHashtag() != null ) {
			search.setCategory(null);
		}
		
		System.out.println("-------search ? : " + search);
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("search", search);
		
		String userId = null;
		User user = (User)session.getAttribute("user");
		
		if ( user != null ) {
			userId = user.getUserId();
		}
		
		inputData.put("userId", userId);
		
		List<HobbyClass> hobbyClassList = searchHobbyClassService.getHobbyClassList(inputData);
		
		for(HobbyClass hobbyClass : hobbyClassList) {
			System.out.println("--------hobbyClass ? : " + hobbyClass);
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		return null;
	}
}
