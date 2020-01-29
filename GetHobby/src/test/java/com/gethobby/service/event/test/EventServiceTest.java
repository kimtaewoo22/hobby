package com.gethobby.service.event.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
	
	//@Test
	public void testaddEventAdmin()throws Exception{
		
		Event event = new Event();
		event.setEventTitle("이벤트Test"); // 검색할 떄 이벤트 이름으로 찾으므로 Unique 할 수 있도록 
		event.setEventStartDate( Date.valueOf("2020-02-10")); //DATE 타입
		//Date startDate = Date.valueOf("2020-02-10");
		System.out.println("startDate: "+event.getEventStartDate());
		System.out.println(Date.valueOf("2020-02-10"));
		event.setEventEndDate( Date.valueOf("2020-02-29")); //DATE 타입
		//Date endDate = Date.valueOf("2020-02-29");
		System.out.println("endDate: "+event.getEventEndDate());
		event.setEventContent("이벤트 내용입니다.내용~~~~~");
		event.setEventDiscount(10);
		event.setEventImage("fff.jpg");
	
		
		List<String> list = new ArrayList<String>();
		
//		for (int i = 0; i < 6 ; i++) {
//			list.add(i, selectedCategory);
//		}
		list.add(0, "e"); //exercise
		list.add(1, "m"); //music
		list.add(2, "h"); //handcraft
		list.add(3, "c"); //cook
		
		event.setCategory(list); //
		
		System.out.println("\n"+event);
		
		eventService.addEventAdmin(event);
		
		System.out.println("성공");
		
	}
	
	@Test
	public void testGetEvent()throws Exception{
		
		Event event = new Event();
		//event.setEventTitle("이벤트Test"); //찾으려는 이벤트 이름
		
		event = eventService.getEvent("이벤트Test");
		
		System.out.println("성공확인: "+event);
		
		Assert.assertEquals("fff.jpg", event.getEventImage());
		//Assert.assertEquals("20-02-29", event.getEventStartDate());
		
		
	}
	
	

}
