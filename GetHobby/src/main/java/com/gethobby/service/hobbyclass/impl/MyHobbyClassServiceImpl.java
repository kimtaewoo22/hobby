package com.gethobby.service.hobbyclass.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.User;
import com.gethobby.service.hobbyclass.MyHobbyClassDAO;
import com.gethobby.service.hobbyclass.MyHobbyClassService;
import com.gethobby.service.hobbyclass.SearchHobbyClassDAO;

@Service("myHobbyClassServiceImpl")
public class MyHobbyClassServiceImpl implements MyHobbyClassService {
	@Autowired
	@Qualifier("myHobbyClassDAOImpl")
	private MyHobbyClassDAO myHobbyClassDAO;
	
	@Autowired
	@Qualifier("searchHobbyClassDAOImpl")
	private SearchHobbyClassDAO searchHobbyClassDAO; 
	
	@Value("#{hashtagProperties}")
	private Properties hashtag;

	@Override
	public List<HobbyClass> getSteamHobbyClass(Map<String, Object> inputData) throws Exception {
		List<HobbyClass> hobbyClassList = myHobbyClassDAO.getSteamHobbyClassList(inputData);
		
		for ( int i = 0; i < hobbyClassList.size(); i++ ) {
			HobbyClass hobbyClass = hobbyClassList.get(i);
			
			List<String> hashtagList = searchHobbyClassDAO.getHobbyClassHashtag(hobbyClass.getHobbyClassNo());
			
			List<String> setHashtagList = new ArrayList<String>();
			
			for (int j = 0; j < hashtagList.size(); j++) {
				String hashtagString = new String(hashtag.getProperty(hashtagList.get(j)).getBytes("ISO-8859-1"), "utf-8");
				setHashtagList.add(hashtagString);
			}
			
			hobbyClass.setHashtag(setHashtagList);
			
			hobbyClass.setTotalGrade( Math.round(hobbyClass.getTotalGrade() * 10) / 10.0 );
		}
		
		return hobbyClassList;
	}

	@Override
	public List<HobbyClass> getRecentlyHobbyClass(Map<String, Object> inputData) throws Exception {
		List<Integer> hobbyClassNoList = (ArrayList<Integer>)inputData.get("hobbyClassNoList");
		
		List<HobbyClass> hobbyClassList = new ArrayList<HobbyClass>();
		
		for (int i = 0; i < hobbyClassNoList.size(); i++) {
			HobbyClass hobbyClass = new HobbyClass();
			
			Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("hobbyClassNo", hobbyClassNoList.get(i));
			map.put("userId", inputData.get("userId"));
			
			hobbyClass = searchHobbyClassDAO.getHobbyClass(map);
			
			hobbyClass.setTotalGrade( Math.round(hobbyClass.getTotalGrade() * 10) / 10.0 );
			
			List<String> hashtagList = searchHobbyClassDAO.getHobbyClassHashtag(hobbyClass.getHobbyClassNo());
			List<String> setHashtagList = new ArrayList<String>();
			
			for (int j = 0; j < hashtagList.size(); j++) {
				String hashtagString = new String(hashtag.getProperty(hashtagList.get(j)).getBytes("ISO-8859-1"), "utf-8");
				setHashtagList.add(hashtagString);
			}
			
			hobbyClass.setHashtag(setHashtagList);
			
			hobbyClassList.add(hobbyClass);
		}
		
		return hobbyClassList;
	}

	@Override
	public List<HobbyClass> getPurchaseHobbyClassSchedult(String userId) throws Exception {
		return myHobbyClassDAO.getPurchaseHobbyClassSchedule(userId);
	}

	@Override
	public List<User> getHobbyClassBuyerStats(int hobbyClassNo) throws Exception {
		return myHobbyClassDAO.getHobbyClassBuyerStats(hobbyClassNo);
	}

	@Override
	public void addSteamHobbyClass(Map<String, Object> inputData) throws Exception {
		myHobbyClassDAO.addSteamHobbyClass(inputData);
		myHobbyClassDAO.updateHobbyClassSteam(inputData);
	}

	@Override
	public void updateHobbyClassSteam(Map<String, Object> inputData) throws Exception {
		myHobbyClassDAO.updateHobbyClassSteam(inputData);
	}

	@Override
	public void deleteSteamHobbyClass(Map<String, Object> inputData) throws Exception {
		myHobbyClassDAO.deleteSteamHobbyClass(inputData);
		myHobbyClassDAO.updateHobbyClassSteam(inputData);
	}
}
