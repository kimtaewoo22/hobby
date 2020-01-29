package com.gethobby.service.event.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.common.Search;
import com.gethobby.service.domain.Event;
import com.gethobby.service.event.EventDAO;
import com.gethobby.service.event.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	@Qualifier("eventDAOImpl")
	EventDAO eventDAO;
	
	//Constructor
	public EventServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(getClass()+" default Constructor ȣ��");
	}
	

	public void setEventDAO(EventDAO eventDAO) {
		//this.eventDAO = eventDAO;
		System.out.println(getClass()+" .setEventDAO ȣ��");
	}


	@Override
	public Map<String, Object> getEventList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getEvent(String eventTitle) throws Exception {
		return eventDAO.getEvent(eventTitle);
	}
	
	@Override
	public void addEventAdmin(Event event) throws Exception {
		eventDAO.insertEventAdmin(event);

	}

	@Override
	public Map<String, Object> getEventListAdmin(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEventAdmin(int eventNo) throws Exception {
		// TODO Auto-generated method stub

	}

}
