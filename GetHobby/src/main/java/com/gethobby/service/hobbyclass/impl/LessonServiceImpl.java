package com.gethobby.service.hobbyclass.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.service.domain.LessonTimes;
import com.gethobby.service.domain.Reply;
import com.gethobby.service.hobbyclass.LessonDAO;
import com.gethobby.service.hobbyclass.LessonService;

@Service("lessonServiceImpl")
public class LessonServiceImpl implements LessonService {
	@Autowired
	@Qualifier("lessonDAOImpl")
	private LessonDAO lessonDAO;

	@Override
	public LessonTimes getLesson(Map<String, Object> inputData) throws Exception {
		return lessonDAO.getLesson(inputData);
	}

	@Override
	public LessonTimes getArrowLesson(Map<String, Object> inputData) throws Exception {
		return lessonDAO.getArrowLesson(inputData);
	}

	@Override
	public void addLessonReply(Reply reply) throws Exception {
		lessonDAO.addLessonReply(reply);	
	}

	@Override
	public void updateLessonReply(Reply reply) throws Exception {
		lessonDAO.updateLessonReply(reply);
	}

	@Override
	public void deleteLessonReply(int replyNo) throws Exception {
		lessonDAO.deleteLessonReply(replyNo);
	}

	@Override
	public List<Reply> getLessonReplyList(Map<String, Object> inputData) throws Exception {
		return lessonDAO.getLessonReplyList(inputData);
	}

	@Override
	public void addLessonTimes(Map<String, Object> inputData) throws Exception {
		lessonDAO.addLessonTimes(inputData);
	}

	@Override
	public void updateLessonTimes(Map<String, Object> inputData) throws Exception {
		lessonDAO.updateLessonTimes(inputData);
	}

	@Override
	public Reply getLessonReply(int replyNo) throws Exception {
		return lessonDAO.getLessonReply(replyNo);
	}
}
