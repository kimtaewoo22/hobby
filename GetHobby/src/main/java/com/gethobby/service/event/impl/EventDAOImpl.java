package com.gethobby.service.event.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gethobby.common.Search;
import com.gethobby.service.domain.Event;
import com.gethobby.service.event.EventDAO;

@Repository
public class EventDAOImpl implements EventDAO {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	//Constructor
	public EventDAOImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(getClass()+" default ");
	}

	public void setSqlSession(SqlSession sqlSession) {
		//this.sqlSession = sqlSession;
		System.out.println(getClass()+".setSqlSession ");
	}

	@Override
	public Map<String, Object> getEventList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getEvent(int eventNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void insertEventAdmin(Event event) throws Exception {
		sqlSession.insert("EventMapper.addEventAdmin", event);
	}

	@Override
	public Map<String, Object> getEventListAdmin(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEventAdmin(Event event) throws Exception {
		// TODO Auto-generated method stub

	}

}
