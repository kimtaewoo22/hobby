package com.gethobby.service.questionreport.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gethobby.service.domain.Question;
import com.gethobby.service.domain.Report;
import com.gethobby.service.questionreport.QuestionReportDAO;

@Repository("questionReportDAOImpl")
public class QuestionReportDAOImpl implements QuestionReportDAO {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	@Override
	public Question getQuestion(int questionNo) throws Exception {
		return sqlSession.selectOne("QuestionReportMapper.getQuestion", questionNo);
	}

	@Override
	public List<Question> getQuestionList(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectList("QuestionReportMapper.getQuestionList", inputData);
	}

	@Override
	public void addAnswerAdmin(Question question) throws Exception {
		sqlSession.update("QuestionReportMapper.addAnswerAdmin", question);
	}

	@Override
	public void addQuestion(Question question) throws Exception {
		sqlSession.insert("QuestionReportMapper.addQuestion", question);
	}

	@Override
	public List<Question> getTotalQuestionAdmin(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectList("QuestionReportMapper.getTotalQuestionAdmin", inputData);
	}

	@Override
	public void deleteQuestion(int questionNo) throws Exception {
		sqlSession.delete("QuestionReportMapper.deleteQuestion", questionNo);
	}

	@Override
	public void addReport(Report report) throws Exception {
		sqlSession.insert("QuestionReportMapper.addReport", report);
	}

	@Override
	public List<Report> getTotalReportListAdmin(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectList("QuestionReportMapper.getTotalReportListAdmin", inputData);
	}

	@Override
	public void processReportAdmin(Map<String, Object> inputData) throws Exception {
		sqlSession.update("QuestionReportMapper.progressReportAdmin", inputData);
	}

	@Override
	public Report getReport(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectOne("QuestionReportMapper.getReport", inputData);
	}

}
