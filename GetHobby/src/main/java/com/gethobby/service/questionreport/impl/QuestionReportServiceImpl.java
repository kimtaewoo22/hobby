package com.gethobby.service.questionreport.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.service.domain.Question;
import com.gethobby.service.domain.Reply;
import com.gethobby.service.domain.Report;
import com.gethobby.service.hobbyclass.LessonDAO;
import com.gethobby.service.hobbyclass.LessonService;
import com.gethobby.service.questionreport.QuestionReportDAO;
import com.gethobby.service.questionreport.QuestionReportService;

@Service("questionReportServiceImpl")
public class QuestionReportServiceImpl implements QuestionReportService {
	@Autowired
	@Qualifier("questionReportDAOImpl")
	private QuestionReportDAO questionReportDAO;
	
	@Autowired
	@Qualifier("lessonDAOImpl")
	private LessonDAO lessonDAO;
	
	@Override
	public Question getQuestion(int questionNo) throws Exception {
		return questionReportDAO.getQuestion(questionNo);
	}

	@Override
	public List<Question> getQuestionList(Map<String, Object> inputData) throws Exception {
		return questionReportDAO.getQuestionList(inputData);
	}

	@Override
	public void addAnswerAdmin(Question question) throws Exception {
		questionReportDAO.addAnswerAdmin(question);
	}

	@Override
	public void addQuestion(Question question) throws Exception {
		questionReportDAO.addQuestion(question);
	}

	@Override
	public List<Question> getTotalQuestionAdmin(Map<String, Object> inputData) throws Exception {
		return questionReportDAO.getTotalQuestionAdmin(inputData);
	}

	@Override
	public void deleteQuestion(int questionNo) throws Exception {
		questionReportDAO.deleteQuestion(questionNo);
	}

	@Override
	public void addReport(Report report) throws Exception {
		questionReportDAO.addReport(report);
	}

	@Override
	public List<Report> getTotalReportAdmin(Map<String, Object> inputData) throws Exception {
		return questionReportDAO.getTotalReportListAdmin(inputData);
	}

	@Override
	public void processReportAdmin(Map<String, Object> inputData) throws Exception {
		String checkResult = (String)inputData.get("reportState");
		
		
		Report report = questionReportDAO.getReport(inputData);
		
		int articleNo = report.getArticleNo();
		int replyNo = report.getReplyNo();
		String userId = "";
		
		if ( checkResult.equals("2") ) {
			if ( articleNo != 0) {
				// 자유게시판, 사진게시판 게시글 삭제 
			}
			else if ( articleNo == 0 && replyNo >= 10000 ) {
				Reply lessonReply = lessonDAO.getLessonReply(replyNo);
				
				int totalReport = lessonReply.getTotalReport() + 1;
				userId = lessonReply.getUser().getUserId();
				
				if ( totalReport >= 5 ) {
					lessonDAO.deleteLessonReply(replyNo);
					// 이후 user의 totalReport도 1 추가시켜야함
				}
				else {
					Map<String, Object> updateData = new HashMap<String, Object>();
					updateData.put("totalReport", totalReport);
					updateData.put("replyNo", replyNo);
					
					lessonDAO.updateLesssonReplyTotalReport(updateData);
				}
			}
			else if ( articleNo == 0 && replyNo < 10000 ) { 
				// 자유게시판, 사진게시판 게시글 삭제 
			}
		}
		else if ( checkResult.equals("1") ) {
			// progerssReport로 단순 상태값만 변화시키고 끝 
		}
		
		questionReportDAO.processReportAdmin(inputData);
	}

}
