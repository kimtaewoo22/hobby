package com.gethobby.service.hobbyclass;

import java.util.List;
import java.util.Map;

import com.gethobby.service.domain.LessonTimes;
import com.gethobby.service.domain.Reply;


public interface LessonDAO {
	public LessonTimes getLesson(Map<String, Object> inputData) throws Exception;
	
	public LessonTimes getArrowLesson(Map<String, Object> inputData) throws Exception; 
	
	public void addLessonReply(Reply reply) throws Exception;
	
	public void updateLessonReply(Reply reply) throws Exception;
	
	public void deleteLessonReply(int replyNo) throws Exception; 
	
	public List<Reply> getLessonReplyList(Map<String, Object> inputData) throws Exception;
	
	public void addLessonTimes(Map<String, Object> inputData) throws Exception;
	
	public void updateLessonTimes(Map<String, Object> inputData) throws Exception;
	
	public Reply getLessonReply(int replyNo) throws Exception;
	
	public void updateLesssonReplyTotalReport(Map<String, Object> updateData) throws Exception;
}
