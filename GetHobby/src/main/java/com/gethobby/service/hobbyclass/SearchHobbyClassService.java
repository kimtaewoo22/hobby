package com.gethobby.service.hobbyclass;

import java.util.List;
import java.util.Map;

import com.gethobby.common.Search;
import com.gethobby.service.domain.ClassAssess;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Lesson;
import com.gethobby.service.domain.LessonTimes;

public interface SearchHobbyClassService {
	public HobbyClass getHobbyClass(Map<String, Object> inputData) throws Exception;
	
	// 쓸지 안쓸지 모름 hashtag만 뽑는거 테스트 
	public List<String> getHobbyClassHashtag(int hobbyClassNo) throws Exception;
	
	public List<HobbyClass> getHobbyClassList(Map<String, Object> inputData) throws Exception;
	
	public List<ClassAssess> getHobbyClassAssessContent(Map<String, Object> inputData) throws Exception;
	
	public List<Lesson> getHobbyClassLessonContent(int hobbyClassNo) throws Exception;
	
	public List<LessonTimes> getLessonTimesList(Map<String, Object> inputData) throws Exception;
	
	public List<HobbyClass> getPopularHobbyClassList(Map<String, Object> inputData) throws Exception;
	
	public List<HobbyClass> getRegisterHobbyClassList(Map<String, Object> inputData) throws Exception;
	
	public void addHobbyClassAssess(ClassAssess classAssess) throws Exception;
	
	public List<String> getUserSelectHashtag(String userId) throws Exception;
}
