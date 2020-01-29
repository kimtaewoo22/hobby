package com.gethobby.service.user.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gethobby.common.Search;
import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)

//@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
//		"classpath:config/context-aspect.xml",
//		"classpath:config/context-mybatis.xml",
//		"classpath:config/context-transaction.xml" })
@ContextConfiguration (locations = { "classpath:config/context-common.xml",
		 "classpath:config/context-mybatis.xml",
		 "classpath:config/context-transaction.xml"})


public class UserServiceTest {
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;	
	
	//@Test
	public void testAddUser() throws Exception{
		
		Map map = new HashMap<String, Object>();
		User user = new User();
		user.setUserId("korea");
		user.setName("한국");
		user.setPassword("7777");
		user.setPhone("01056781111");
		user.setBirth("11111111");
		user.setSex("0");
		 
		List list = new ArrayList<String>();
		list.add("A01");
		list.add("A02");
		list.add("A03");
		list.add("A04");
		list.add("A05");
		
		map.put("user", user);
		map.put("list", list);
		map.put("userId" , user.getUserId());
		
		userService.addUser(map);
		
		map = userService.getUser("korea");
		user = (User)map.get("user");
		Assert.assertEquals("korea",user.getUserId());
		Assert.assertEquals("한국",user.getName());
		Assert.assertEquals("7777",user.getPassword());
		Assert.assertEquals("01056781111", user.getPhone());
		Assert.assertEquals("11111111",user.getBirth());
		Assert.assertEquals("0", user.getSex());
		
	}
	
	//@Test
	public void testGetUser() throws Exception{
		
		User user = new User();
		Map<String, Object> map = new HashMap<String, Object>();
		map = userService.getUser("abc");
		user = (User)map.get("user");
		
		
		
		Assert.assertEquals("abc",user.getUserId());
		Assert.assertEquals("김태우",user.getName());
		Assert.assertEquals("1234",user.getPassword());
		Assert.assertEquals("01022223333", user.getPhone());
		Assert.assertEquals("20001111",user.getBirth());
		Assert.assertEquals("0", user.getSex());
	}
	
	//@Test
	public void deleteUser() throws Exception{
		User user = new User();
		Map<String, Object> map = new HashMap<String, Object>();
		map = userService.getUser("abc");
		user = (User)map.get("user");
			
		user.setRole("9");
		userService.deleteUser(user);
		
		Assert.assertEquals("9", user.getRole());
	}
	//@Test
	public void updateUser() throws Exception{
		User user = new User();
		Map<String, Object> map = new HashMap<String, Object>();
		map = userService.getUser("abc");
		user = (User)map.get("user");
		
		user.setAddress("중랑구");
		user.setDetailAddress("면목동");
		user.setPostCode("123-444");
		user.setPhone("01012345678");
		user.setNickName("홍길동");
		user.setProfileImage("1234.jpg");
		
		userService.updateUser(user);
		
		Assert.assertEquals("중랑구", user.getAddress());
		Assert.assertEquals("면목동", user.getDetailAddress());
		Assert.assertEquals("123-444", user.getPostCode());
		Assert.assertEquals("01012345678", user.getPhone());
		Assert.assertEquals("홍길동", user.getNickName());
		Assert.assertEquals("1234.jpg", user.getProfileImage());
		
	}
	
	//@Test
	public void changeUserCreator()throws Exception{
		
		User user = new User();
		Map<String, Object> map = new HashMap<String, Object>();
		map = userService.getUser("abc");
		user = (User)map.get("user");
		user.setRole("1");
		
		Assert.assertEquals("1", user.getRole());
	}
	
	//@Test
	public void TestgetUserListAdmin() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(2);
		
		Map<String,Object> map = userService.getUserListAdmin(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void TestgetStopUserListAdmin() throws Exception{
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(2);
		
		Map<String,Object> map = userService.getStopUserListAdmin(search);		
		List<Object> list =(List<Object>) map.get("list");
		
		Assert.assertEquals(1, list.size());
	}
	
}
