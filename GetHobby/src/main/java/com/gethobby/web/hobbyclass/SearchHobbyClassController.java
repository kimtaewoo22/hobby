package com.gethobby.web.hobbyclass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gethobby.common.Page;
import com.gethobby.common.Search;
import com.gethobby.service.domain.ClassAssess;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Lesson;
import com.gethobby.service.domain.LessonTimes;
import com.gethobby.service.domain.User;
import com.gethobby.service.hobbyclass.SearchHobbyClassService;

@Controller
@RequestMapping("/searchHobbyClass/*")
public class SearchHobbyClassController {
	@Autowired
	@Qualifier("searchHobbyClassServiceImpl")
	private SearchHobbyClassService searchHobbyClassService;
	
	@Value("#{hashtagProperties}")
	private Properties hashtag;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	public SearchHobbyClassController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value = "getSearchHobbyClassIntro" )
	public String getSearchHobbyClassIntro( @RequestParam("hobbyClassNo") String hobbyClassNo, HttpSession session, Model model ) throws Exception {
		Map<String, Object> inputData = new HashMap<String, Object>();
		
		System.out.println("----------hobbyClassNo ? : " + hobbyClassNo);
		
		User user = (User)session.getAttribute("user");
		
		// c@c.c가 로그인했다고 가정 
		inputData.put("userId", "c@c.c");
		inputData.put("hobbyClassNo", hobbyClassNo);
		
		HobbyClass hobbyClass = searchHobbyClassService.getHobbyClass(inputData);
		System.out.println("------hobbyClass ? : " + hobbyClass);
		model.addAttribute("hobbyClass", hobbyClass);
		
		return "forward:/hobbyclass/getHobbyClassIntro.jsp";
	}
	
	@RequestMapping( value = "getSearchHobbyClassKit" )
	public String getSearchHobbyClassKit( @RequestParam("hobbyClassNo") String hobbyClassNo, HttpSession session, Model model ) throws Exception {
		Map<String, Object> inputData = new HashMap<String, Object>();
		
		System.out.println("----------hobbyClassNo ? : " + hobbyClassNo);
		
		User user = (User)session.getAttribute("user");
		
		// c@c.c가 로그인했다고 가정 
		inputData.put("userId", "c@c.c");
		inputData.put("hobbyClassNo", hobbyClassNo);
		
		HobbyClass hobbyClass = searchHobbyClassService.getHobbyClass(inputData);
		System.out.println("------hobbyClass ? : " + hobbyClass);
		model.addAttribute("hobbyClass", hobbyClass);
		
		return "forward:/hobbyclass/getHobbyClassKit.jsp";
	}
	
	@RequestMapping( value = "getHobbyClassAssessContent" )
	public String getHobbyClassAssessContent( @RequestParam("hobbyClassNo") String hobbyClassNo, Model model ) throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(pageSize);
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("hobbyClassNo", hobbyClassNo);
		inputData.put("search", search);
		
		Map<String, Object> returnMap = searchHobbyClassService.getHobbyClassAssessContent(inputData); 
		
		List<ClassAssess> listAssessContent = (List<ClassAssess>)returnMap.get("list"); 
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)returnMap.get("total")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("listAssessContent", listAssessContent);
		model.addAttribute("resultPage", resultPage);
		
		return "forward:/hobbyclass/getHobbyClassAssessContent.jsp";
	}
	
	@RequestMapping( value = "getHobbyClassLessonContent" )
	public String getHobbyClassLessonContent( @RequestParam("hobbyClassNo") String hobbyClassNo, HttpSession session, Model model ) throws Exception {
		
		User user = (User)session.getAttribute("user");
		String userId = "a@a.a";
		
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("hobbyClassNo", hobbyClassNo);
		inputData.put("userId", userId);
		
		List<LessonTimes> lessonTimesList = searchHobbyClassService.getHobbyClassLessonContent(inputData);
		
		model.addAttribute("lesson", lessonTimesList);
		
		System.out.println("-----lessonTimesList.get(0)? : " + lessonTimesList.get(0));
		return "forward:/hobbyclass/getHobbyClassLessonContent.jsp";
	}
	
	@RequestMapping( value = "getPopularHobbyClassList" )
	public String getPopularHobbyClassList(Model model, HttpSession session) throws Exception {
		User user = (User)session.getAttribute("user");
		
		// a@a.a가 로그인했다고 가정 
		String userId = "a@a.a";
		
		Search search = new Search(); 
		search.setCurrentPage(1);
		search.setPageSize(pageSize);
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("userId", userId);
		inputData.put("search", search);
		
		Map<String, Object> map = searchHobbyClassService.getPopularHobbyClassList(inputData);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("total")).intValue(), pageUnit, pageSize);
		List<HobbyClass> hobbyClassList = (List<HobbyClass>)map.get("list");
		
		model.addAttribute("list", hobbyClassList);
		model.addAttribute("resultPage", resultPage);

		return "forward:/hobbyclass/listPopularHobbyClass.jsp";
	}
}
