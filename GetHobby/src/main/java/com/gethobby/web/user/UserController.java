package com.gethobby.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserService;

//ȸ������ Controller
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
		
		System.out.println("addUser() GET ����::::::::::::::");
		
		System.out.println("/user/addUser:GET");
		return null;
	}
	
	@RequestMapping(value="addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) throws Exception{
		
		System.out.println("addUser() POST ���� ::::::::::::::::::::");
		
//		userService.addUser(user);
		
		return null;
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user")User user, HttpSession session) throws Exception{
		
		User dbUser = userService.getUser(user.getUserId());
		
		if(user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
		}
		
		return null;
	}
	
	@RequestMapping(value = "logout" , method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return null;
	}
	
	@RequestMapping(value = "getUser", method = RequestMethod.GET)
	public String getUser(@RequestParam("userId") String userId, Model model) throws Exception{
		User user = userService.getUser(userId);
		
		model.addAttribute("user", user);
		
		return null;
	}
}
