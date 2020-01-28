package com.gethobby.service.user.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })


public class UserServiceTest {
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;	
	
	@Test
	public void testAddUser() throws Exception{
		
		User user = new User();
		user.setUserId("abc");
		user.setName("±èÅÂ¿ì");
		user.setPassword("1234");
		user.setBirth("20001111");
		user.setSex("0");
		
		userService.addUser(user);
		
		user = userService.getUser("abc");
		
		Assert.assertEquals("abc",user.getUserId());
		Assert.assertEquals("±èÅÂ¿ì",user.getName());
		Assert.assertEquals("1234",user.getPassword());
		Assert.assertEquals("20001111",user.getBirth());
		Assert.assertEquals("0", user.getSex());
		
	}
	
}
