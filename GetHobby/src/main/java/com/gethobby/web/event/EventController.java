package com.gethobby.web.event;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gethobby.common.Search;
import com.gethobby.service.event.EventService;

@Controller
@RequestMapping("/event/*")
public class EventController {

	@Autowired
	@Qualifier("eventServiceImpl")
	private EventService eventService;
	

	
	//Constructor
	public EventController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping(value = "/addEventCategory")
	public String addEventCategory(HttpServletRequest request)throws Exception{
	
		//System.out.println("성공");
		
       // String[] arr = request.getParameterValues("cate");
        
       // System.out.println("파라미터이름:"+request.getParameterNames());


	 //String[] arr = request.getParameterValues("myBtn");
    
   // System.out.println("파라미터이름:"+request.getParameterNames());
    
    //model.addAttribute("arr", arr);
    return "forward:/event/ok.jsp";
    
	}
	
	@RequestMapping(value = "eventList")
	public String eventList(@ModelAttribute("search")Search search)throws Exception{
		
		search.setPageSize(pageSize*3); 
		//jsp에서 받아온 search에 searchCondition들어있음. (진행중/ 종료/ 전체)
		//service갈때 들고가는 search에 (진행중/ 종료/ 전체)의 searchCondition도 들어있음.
 		System.out.println("eventList할 떄 들고갈 pageSize확인(9면 OK) :"+search.getPageSize());
		
		
		return "";
	}
	

}
