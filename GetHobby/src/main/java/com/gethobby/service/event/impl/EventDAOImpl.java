package com.gethobby.service.event.impl;

import java.util.HashMap;
import java.util.List;
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		//위처럼 통채로 넣어보고 Mapper에서 꺼내서 안써지면 아래처럼 빼서 넣어줄것.
		//map.put("searchCondition", search.getSearchCondition()); => mapper에서 조건만들거 아니면 필요없음
		//searchCondition - 전체/ 진행중/ 종료
		//searchCondition = 진행중 ==> 
		
		//map.put("pagesize", search.getPageSize());
		
		//Date객체로 SYSDATE뽑아냄. => String 문자열 형식(yy/mm/dd)으로 형변환.
		
		//map.put("sysdate", sysdate);
		
		if( (search.getSearchCondition()).equals("진행중") ) {
			//sqlSession.selectList("EventMapper.getEventListInProgress", map);
		}
		
		
		//sqlSession.selectList
		return null;
	}

	@Override
	public Event getEvent(String eventTitle) throws Exception {
		
		System.out.println("DAOImpl로 넘어온 eventTitle: "+eventTitle);
		
		Event event = new Event(); 
		List<Event> list = sqlSession.selectList("EventMapper.getEvent", eventTitle);
		for (int i = 0; i < list.size(); i++) {
			list.get(i);
			System.out.println(list.get(i));
		}
		
		//event Category가 여러개일 시 해당 event Domain의 category (List Type)에 집어넣어 리턴=> 리턴타입 Event로 가능
		return null; /////////////수정수정수정
	}
	
	
	@Override
	public void insertEventAdmin(Event event) throws Exception {
		
		List<String> category = event.getCategory();
		System.out.println("category: "+category);
		
		//여기서 "EventMapper.getEvent", eventTitle로 정보가져와서 저장하려는 이벤트이름과 겹치지않는지 검증.
		
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < category.size(); i++) {
			category.get(i);
			System.out.println(i+"번째 카테고리: "+category.get(i) );
			
			String sendCategory = category.get(i);
			System.out.println(i+"번째 전송카테고리: "+sendCategory);
			
			map.put("sendCategory", sendCategory);
			map.put("event", event);
			
			sqlSession.insert("EventMapper.addEventAdmin", map);
			
		}
	}

	@Override
	public Map<String, Object> getEventListAdmin(Search search) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		//위처럼 통채로 넣어보고 Mapper에서 꺼내서 안써지면 아래처럼 빼서 넣어줄것.
		//map.put("pagesize", search.getPageSize());

		//searchCondition 필요없음 => 전체목록 최신순으로 정렬해 페이징처리해 전송
		
		//Date객체로 SYSDATE뽑아냄. => String 문자열 형식(yy/mm/dd)으로 형변환.
		
		//map.put("sysdate", sysdate);
		
		//sqlSession.selectList("EventMapper.getEventListNewest", map);
		//==> 최신순으로 뽑아오는 건 똑같으니까 이걸로 뽑아오돼 eventImage는 쓰지않음.(View에서)
		
		return null;
	}

	@Override
	public void updateEventAdmin(Event event) throws Exception {
		sqlSession.update("EventMapper.updateEventAdmin", event);
		
		//category 주의
		//가져온
	}

}
