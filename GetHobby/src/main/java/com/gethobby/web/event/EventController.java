package com.gethobby.web.event;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	

}
