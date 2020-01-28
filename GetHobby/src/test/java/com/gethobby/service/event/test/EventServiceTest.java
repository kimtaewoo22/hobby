package com.gethobby.service.event.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gethobby.service.domain.Event;
import com.gethobby.service.event.EventService;

/*
 *	FileName :  EventServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath:config/context-common.xml",
									"classpath:config/context-mybatis.xml",
		 							"classpath:config/context-transaction.xml"} )
public class EventServiceTest {
	
	@Autowired
	@Qualifier("eventServiceImpl")
	private EventService eventService;

	public EventServiceTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void addEventAdmin()throws Exception{
		
		Event event = new Event();
		event.setEventTitle("2020이벤트");
		event.setEventStartDate( Date.valueOf("2020-01-01")); //DATE 타입
		Date startDate = Date.valueOf("2020-01-01");
		System.out.println("startDate: "+startDate);
		event.setEventEndDate( Date.valueOf("2020-01-31")); //DATE 타입
		//Date endDate = Date.valueOf("2020-01-01");
		System.out.println( Date.valueOf("2020-01-01"));
		event.setEventContent("이벤트 내용입니다. 1월 동안 진행됩니다.");
		
		List<String> list = new ArrayList<String>();
		
//		for (int i = 0; i < 6 ; i++) {
//			list.add(i, selectedCategory);
//		}
		list.add(0, "exercise");
		list.add(1, "music");
		
		event.setCategory(list); //운동 카테고리
		
		System.out.println("\n"+event);
		
		eventService.addEventAdmin(event);
		
	}
	

}
