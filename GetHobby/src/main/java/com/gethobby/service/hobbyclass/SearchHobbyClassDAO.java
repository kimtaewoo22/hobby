package com.gethobby.service.hobbyclass;

import java.util.List;
import java.util.Map;

import com.gethobby.common.Search;
import com.gethobby.service.domain.ClassAssess;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Lesson;
import com.gethobby.service.domain.LessonTimes;

public interface SearchHobbyClassDAO {
	public HobbyClass getHobbyClass(Map<String, Object> inputData) throws Exception; 
 
	public List<String> getHobbyClassHashtag(int hobbyClassNo) throws Exception;
	
	public List<HobbyClass> getHobbyClassList(Map<String, Object> inputData) throws Exception;
	
	public List<ClassAssess> getHobbyClassAssessContent(Map<String, Object> inputData) throws Exception;
	
	public List<LessonTimes> getHobbyClassLessonContent(Map<String, Object> inputData) throws Exception;
	
	public List<LessonTimes> getLessonTimesList(Map<String, Object> inputData) throws Exception;
	
	public List<HobbyClass> getPopularHobbyClassList(Map<String, Object> inputData) throws Exception;
	
	public List<HobbyClass> getRegisterHobbyClassList(Map<String, Object> inputData) throws Exception;
	
	public void addHobbyClassAssess(ClassAssess classAssess) throws Exception;
	
	public List<String> getUserSelectHashtag(String userId) throws Exception;
	
	public int getHobbyClassAssessContentTotalCount(Map<String, Object> inputData) throws Exception;
	
	public int getHobbyClassListTotalCount(Map<String, Object> inputData) throws Exception;
	
	public int getPopularHobbyClassListTotalCount(Map<String, Object> inputData) throws Exception;
}
