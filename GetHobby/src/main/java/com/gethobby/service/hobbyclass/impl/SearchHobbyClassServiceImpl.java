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

import com.gethobby.common.Search;
import com.gethobby.service.domain.ClassAssess;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Lesson;
import com.gethobby.service.domain.LessonTimes;
import com.gethobby.service.hobbyclass.SearchHobbyClassDAO;
import com.gethobby.service.hobbyclass.SearchHobbyClassService;

@Service("searchHobbyClassServiceImpl")
public class SearchHobbyClassServiceImpl implements SearchHobbyClassService {
	
	@Autowired
	@Qualifier("searchHobbyClassDAOImpl")
	private SearchHobbyClassDAO searchHobbyClassDAO;
	
	@Value("#{hashtagProperties}")
	private Properties hashtag;
	
	@Override
	public HobbyClass getHobbyClass(Map<String, Object> inputData) throws Exception {
		HobbyClass hobbyClass = searchHobbyClassDAO.getHobbyClass(inputData);
		
		hobbyClass.setTotalGrade( Math.round(hobbyClass.getTotalGrade() * 10) / 10.0 );
		
		List<String> setHashtagList = new ArrayList<String>();
		List<String> hashtagList = hobbyClass.getHashtag();
		
		for(int i = 0; i < hashtagList.size(); i++) {
			String hashtagString = new String(hashtag.getProperty(hashtagList.get(i)).getBytes("ISO-8859-1"), "utf-8");
			setHashtagList.add(hashtagString);
		}
		
		hobbyClass.setHashtag(setHashtagList);
		
		return hobbyClass;
	}

	@Override
	public List<String> getHobbyClassHashtag(int hobbyClassNo) throws Exception {
		return searchHobbyClassDAO.getHobbyClassHashtag(hobbyClassNo);
	}

	@Override
	public Map<String, Object> getHobbyClassList(Map<String, Object> inputData) throws Exception {
		List<HobbyClass> hobbyClassList = searchHobbyClassDAO.getHobbyClassList(inputData);
		
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
		
		int totalCount = searchHobbyClassDAO.getHobbyClassListTotalCount(inputData);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("total", totalCount);
		returnMap.put("list", hobbyClassList);
		
		return returnMap;
	}

	@Override
	public Map<String, Object> getHobbyClassAssessContent(Map<String, Object> inputData) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", searchHobbyClassDAO.getHobbyClassAssessContent(inputData));
		returnMap.put("total", searchHobbyClassDAO.getHobbyClassAssessContentTotalCount(inputData));
		return returnMap;
	}

	@Override
	public List<LessonTimes> getHobbyClassLessonContent(Map<String, Object> inputData) throws Exception {
		return searchHobbyClassDAO.getHobbyClassLessonContent(inputData);
	}

	@Override
	public List<LessonTimes> getLessonTimesList(Map<String, Object> inputData) throws Exception {
		return searchHobbyClassDAO.getLessonTimesList(inputData);
	}

	@Override
	public Map<String, Object> getPopularHobbyClassList(Map<String, Object> inputData) throws Exception {
		List<HobbyClass> hobbyClassList = searchHobbyClassDAO.getPopularHobbyClassList(inputData);
		
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
		
		int totalCount = searchHobbyClassDAO.getPopularHobbyClassListTotalCount(inputData);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", hobbyClassList);
		returnMap.put("total", totalCount);
		
		return returnMap;
	}

	@Override
	public List<HobbyClass> getRegisterHobbyClassList(Map<String, Object> inputData) throws Exception {
		List<HobbyClass> hobbyClassList = searchHobbyClassDAO.getRegisterHobbyClassList(inputData);
		
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
	public void addHobbyClassAssess(ClassAssess classAssess) throws Exception {
		searchHobbyClassDAO.addHobbyClassAssess(classAssess);
	}

	@Override
	public List<String> getUserSelectHashtag(String userId) throws Exception {
		return searchHobbyClassDAO.getUserSelectHashtag(userId);
	}

}
