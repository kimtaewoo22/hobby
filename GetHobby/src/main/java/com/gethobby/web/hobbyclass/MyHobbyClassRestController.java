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
import com.gethobby.service.hobbyclass.MyHobbyClassService;
import com.gethobby.service.hobbyclass.SearchHobbyClassService;

@RestController
@RequestMapping("/myHobbyClass/*")
public class MyHobbyClassRestController {
	
	@Autowired
	@Qualifier("myHobbyClassServiceImpl")
	private MyHobbyClassService myHobbyClassService;
	
	@Autowired
	@Qualifier("searchHobbyClassServiceImpl")
	private SearchHobbyClassService searchHobbyClassService;
	
	@Value("#{hashtagProperties}")
	private Properties hashtag;
	
	public MyHobbyClassRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value = "json/addSteamHobbyClass" )
	public Map<String, Object> addSteamHobbyClass(@RequestBody Map<String, String> jsonMap, HttpSession session) throws Exception {
		User user = (User)session.getAttribute("user");
		
		// c@c.c가 로그인했다고 가정 
		String userId = "c@c.c";
		
		int steamCount = Integer.parseInt(jsonMap.get("steamCount")) + 1;
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("userId", userId);
		inputData.put("hobbyClassNo", jsonMap.get("hobbyClassNo"));
		inputData.put("steamCount", steamCount);
		
		myHobbyClassService.addSteamHobbyClass(inputData);
		
		HobbyClass hobbyClass = searchHobbyClassService.getHobbyClass(inputData);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("hobbyClass", hobbyClass);
		
		return returnMap;
	}
	
	@RequestMapping( value = "json/deleteSteamHobbyClass" ) 
	public Map<String, Object> deleteSteamHobbyClass( @RequestBody Map<String, String> jsonMap, HttpSession session ) throws Exception {
		User user = (User)session.getAttribute("user");
		
		// c@c.c가 로그인했다고 가정 
		String userId = "c@c.c";
		System.out.println("-------jsonMap steamCount ? : " + jsonMap.get("steamCount"));
		int steamCount = Integer.parseInt(jsonMap.get("steamCount")) + - 1;
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("userId", userId);
		inputData.put("hobbyClassNo", jsonMap.get("hobbyClassNo"));
		inputData.put("steamCount", steamCount);
		
		myHobbyClassService.deleteSteamHobbyClass(inputData);
		
		HobbyClass hobbyClass = searchHobbyClassService.getHobbyClass(inputData);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("hobbyClass", hobbyClass);
		
		return returnMap;
	}
}
