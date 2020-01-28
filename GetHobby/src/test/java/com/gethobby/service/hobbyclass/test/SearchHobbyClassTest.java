package com.gethobby.service.hobbyclass.test;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gethobby.common.Search;
import com.gethobby.service.domain.ClassAssess;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Lesson;
import com.gethobby.service.domain.LessonTimes;
import com.gethobby.service.domain.User;
import com.gethobby.service.hobbyclass.SearchHobbyClassService;

@RunWith(SpringJUnit4ClassRunner.class)
/*
@ContextConfiguration (locations = { "classpath:config/context-common.xml",
									 "classpath:config/context-aspect.xml",
									 "classpath:config/context-mybatis.xml",
									 "classpath:config/context-transaction.xml"})
*/
@ContextConfiguration (locations = { "classpath:config/context-common.xml",
		 							 "classpath:config/context-mybatis.xml",
		 							 "classpath:config/context-transaction.xml"})
public class SearchHobbyClassTest {

	@Autowired
	@Qualifier("searchHobbyClassServiceImpl")
	private SearchHobbyClassService searchHobbyClassService;
	
	@Value("#{hashtagProperties}")
	private Properties hashtag;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	//@Test
	public void testGetHobbyClass() throws Exception {
		HobbyClass hobbyClass = new HobbyClass();
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		
		inputData.put("hobbyClassNo", 10000);
		inputData.put("userId", "a@a.a");
		hobbyClass = searchHobbyClassService.getHobbyClass(inputData);
		
		System.out.println("-----------------hobbyClass ? : " + hobbyClass);
		System.out.println("----------------hobbyClass steamCheck ? : " + hobbyClass.getSteamCheck());
		System.out.println("-------------hobbyClass grade ? : " + hobbyClass.getTotalGrade());
		
		Assert.assertEquals(10000, hobbyClass.getHobbyClassNo());
	}
	
	@Test
	public void testHobbyClassList() throws Exception {
		Search search = new Search();
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		search.setSearchCondition("1");
		search.setSearchKeyword("t");

		List<String> hashtagList = new ArrayList<String>();
		hashtagList.add("M01");
		hashtagList.add("E03");
		
		//search.setHashtag(hashtagList);

		//search.setCategory("E");
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("search", search); 
		inputData.put("userId", "a@a.a");

		inputData.put("classState", "3");
		
		List<HobbyClass> listHobbyClass = searchHobbyClassService.getHobbyClassList(inputData);
		
		for(HobbyClass hobbyClass : listHobbyClass) {
			System.out.println("----------hobbyClass ? : " + hobbyClass);
		}
	}
	
	//@Test 
	public void testAssessContent() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(pageSize);
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("search", search);
		inputData.put("hobbyClassNo", 10000);
		
		List<ClassAssess> listAssessContent = searchHobbyClassService.getHobbyClassAssessContent(inputData); 
		
		for(ClassAssess classAssess : listAssessContent) {
			System.out.println("-----------------------classAssess ? : " + classAssess);
		}
	}
	
	//@Test
	public void testLessonContent() throws Exception {
		List<Lesson> lessonList = searchHobbyClassService.getHobbyClassLessonContent(10000);
		
		for ( Lesson lesson : lessonList ) { 
			System.out.println("-----lesson ? : " + lesson);
		}
	}
	
	//@Test
	public void testLessonTimes() throws Exception {
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("userId", "d@d.d");
		inputData.put("hobbyClassNo", 10000);
		
		List<LessonTimes> lessonTimesList = searchHobbyClassService.getLessonTimesList(inputData);
		
		for ( LessonTimes lessonTimes : lessonTimesList ) {
			System.out.println("-----lessonTimes ? : " + lessonTimes);
		}
		
	}
	
	//@Test
	public void testPopularHobbyClassList() throws Exception {
		Map<String, Object> inputData = new HashMap<String, Object>();
		
		Search search = new Search();
		// ����ȭ��ǥ �������� ������ȭ��ǥ �������� Ȯ�ο� ����
		String check = "0";
		
		// ����ȭ��ǥ�� 
		if ( check.equals("0")) { 
			// ���������� (2)�� ���� ��������
			search.setCurrentPage(2 - 1);
		}
		// ������ȭ��ǥ�� 
		else if ( check.equals("1")) {
			// ���������� (2)�� ���� ��������
			search.setCurrentPage(2 + 1);
		}
		// currentPage == 4�϶� 1�� 1�϶� 4�� 
		
		search.setPageSize(pageSize);
		
		inputData.put("search", search);
		inputData.put("userId", "a@a.a");
		
		List<HobbyClass> popularHobbyClassList = searchHobbyClassService.getPopularHobbyClassList(inputData);
		
		for (HobbyClass hobbyClass : popularHobbyClassList) {
			System.out.println("-------hobbyClass ? : " + hobbyClass);
		}
	}
	
	//@Test
	public void testRegisterHobbyClassList() throws Exception {
		Map<String, Object> inputData = new HashMap<String, Object>();
		
		Search search = new Search();
		// ����ȭ��ǥ �������� ������ȭ��ǥ �������� 
		String check = "0";
		
		// ����ȭ��ǥ�� 
		if ( check.equals("0")) { 
			// ���������� (2)�� ���� ��������
			search.setCurrentPage(2 - 1);
		}
		// ������ȭ��ǥ�� 
		else if ( check.equals("1")) {
			// ���������� (2)�� ���� ��������
			search.setCurrentPage(2 + 1);
		}
		// currentPage == 4�϶� 1�� 1�϶� 4�� 
		
		search.setPageSize(pageSize);
		
		inputData.put("search", search);
		inputData.put("userId", "a@a.a");
		
		List<HobbyClass> registerHobbyClassList = searchHobbyClassService.getRegisterHobbyClassList(inputData);
		
		for (HobbyClass hobbyClass : registerHobbyClassList) { 
			System.out.println("-------hobbyClass ? : " + hobbyClass);
		}
	}
	
	//@Test
	public void testAddHobbyClassAssess() throws Exception {
		ClassAssess classAssess = new ClassAssess();
		
		User user = new User();
		user.setUserId("a@a.a");
		
		classAssess.setUser(user);
		
		HobbyClass hobbyClass = new HobbyClass();
		hobbyClass.setHobbyClassNo(10000);
		
		classAssess.setHobbyClass(hobbyClass);
		
		classAssess.setAssessStar(4);
		classAssess.setAssessContent("�̰� ���ƿ� ��õ");
		
		searchHobbyClassService.addHobbyClassAssess(classAssess);
	}
	
	//@Test
	public void testRecommendHobbyClassList() throws Exception {
		List<String> hashtagList = searchHobbyClassService.getUserSelectHashtag("a@a.a");
		
		for (String a : hashtagList ) { 
			System.out.println("-------a ? : " + a);
		}
		
		Search search = new Search();
		
		search.setCurrentPage(1);
		search.setPageSize(pageSize);
		
		search.setHashtag(hashtagList);
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		
		inputData.put("search", search);
		inputData.put("userId", "a@a.a");
		inputData.put("classState", "3");
		
		List<HobbyClass> recommendHobbyClassList = searchHobbyClassService.getHobbyClassList(inputData);
		
		for(HobbyClass hobbyClass : recommendHobbyClassList) {
			System.out.println("------------hobbyClass ? : " + hobbyClass);
		}
		
		
	}
	
}
