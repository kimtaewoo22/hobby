package com.gethobby.service.event;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.gethobby.common.Search;
import com.gethobby.service.domain.Event;

@Service
public interface EventService {

	public Map<String, Object>getEventList(Search search)throws Exception;
	
	public Event getEvent(String eventTitle)throws Exception;
	
	public void addEventAdmin(Event event)throws Exception;
	
	public Map<String, Object>getEventListAdmin(Search search)throws Exception;
	
	public void updateEventAdmin(int eventNo)throws Exception;
		
}
