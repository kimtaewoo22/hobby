package com.gethobby.service.event;

import java.util.Map;

import com.gethobby.common.Search;
import com.gethobby.service.domain.Event;

public interface EventDAO {
	
	Event event = new Event();
	
	public Map<String, Object>getEventList(Search search)throws Exception;
	
	public Event getEvent(String eventTitle)throws Exception;
	
	public void insertEventAdmin(Event event)throws Exception;
	
	public Map<String, Object>getEventListAdmin(Search search)throws Exception;
	
	public void updateEventAdmin(Event event)throws Exception;

}
