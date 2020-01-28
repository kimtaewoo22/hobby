package com.gethobby.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserService;

//회원관리 Controller
@Controller
@RequestMapping("/user/*")
public class UserController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	

	public UserController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="addUser", method = RequestMethod.GET)
	public String addUser() throws Exception{
		
		System.out.println("addUser() GET 시작::::::::::::::");
		
		System.out.println("/user/addUser:GET");
		return null;
	}
	
	@RequestMapping(value="addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) throws Exception{
		
		System.out.println("addUser() POST 시작 ::::::::::::::::::::");
		
		userService.addUser(user);
		
		return null;
	}
	
	
}
