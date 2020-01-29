package com.gethobby.service.hobbyclass.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gethobby.common.Search;
import com.gethobby.service.domain.ClassAssess;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Lesson;
import com.gethobby.service.domain.LessonTimes;
import com.gethobby.service.hobbyclass.SearchHobbyClassDAO;

@Repository("searchHobbyClassDAOImpl")
public class SearchHobbyClassDAOImpl implements SearchHobbyClassDAO {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	public SearchHobbyClassDAOImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public HobbyClass getHobbyClass(Map<String, Object> inputData) throws Exception {
		HobbyClass returnClass = sqlSession.selectOne("HobbyClassMapper.getSearchHobbyClass", inputData);
		returnClass.setHashtag(sqlSession.selectList("HobbyClassMapper.getHobbyClassHashtag", inputData.get("hobbyClassNo")));
		return returnClass;
	}


	@Override
	public List<String> getHobbyClassHashtag(int hobbyClassNo) throws Exception {
		return sqlSession.selectList("HobbyClassMapper.getHobbyClassHashtag", hobbyClassNo);
	}


	@Override
	public List<HobbyClass> getHobbyClassList(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectList("HobbyClassMapper.getHobbyClassList", inputData);
	}


	@Override
	public List<ClassAssess> getHobbyClassAssessContent(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectList("HobbyClassMapper.getHobbyClassAssessContent", inputData);
	}


	@Override
	public List<LessonTimes> getHobbyClassLessonContent(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectList("HobbyClassMapper.getHobbyClassLessonContent", inputData);
	}


	@Override
	public List<LessonTimes> getLessonTimesList(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectList("HobbyClassMapper.getLessonTimesList", inputData);
	}


	@Override
	public List<HobbyClass> getPopularHobbyClassList(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectList("HobbyClassMapper.getpopularHobbyClassList", inputData);
	}


	@Override
	public List<HobbyClass> getRegisterHobbyClassList(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectList("HobbyClassMapper.getRegisterHobbyClassList", inputData);
	}


	@Override
	public void addHobbyClassAssess(ClassAssess classAssess) throws Exception {
		sqlSession.insert("HobbyClassMapper.addClassAssess", classAssess);
		
	}


	@Override
	public List<String> getUserSelectHashtag(String userId) throws Exception {
		return sqlSession.selectList("HobbyClassMapper.getUserSelectHashtag", userId);
	}


	@Override
	public int getHobbyClassAssessContentTotalCount(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectOne("HobbyClassMapper.getHobbyClassAssessContentTotalCount", inputData);
	}


	@Override
	public int getHobbyClassListTotalCount(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectOne("HobbyClassMapper.getHobbyClassListTotalCount", inputData);
	}


	@Override
	public int getPopularHobbyClassListTotalCount(Map<String, Object> inputData) throws Exception {
		return sqlSession.selectOne("HobbyClassMapper.getPopularHobbyClassListTotalCount", inputData);
	}

}
