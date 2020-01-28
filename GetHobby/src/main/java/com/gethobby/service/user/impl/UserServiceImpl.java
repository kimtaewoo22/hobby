package com.gethobby.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.common.Search;
import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserDAO;
import com.gethobby.service.user.UserService;


//ȸ�� ���� ���� ����
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userDAOImpl")
	private UserDAO userDAO;
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public UserServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addUser(Map	map) throws Exception {
		
			
		userDAO.addUser((User)map.get("user"));
		userDAO.addHashtag(map);
		
	}

	@Override
	public User getUser(String userId) throws Exception {
		return userDAO.getUser(userId);		
	}

	@Override
	public void deleteUser(User user) throws Exception {
		userDAO.deleteUser(user);
		
	}

	@Override
	public void updateUser(User user) throws Exception {
		userDAO.updateUser(user);
	}

	@Override
	public void changeUserCreator(User user) throws Exception {
		userDAO.changeUserCreator(user);
	}

	@Override
	public Map<String, Object> getUserListAdmin(Search search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list = userDAO.getUserListAdmin(search);
		map.put("list", list);
		 return map;
	}

	@Override
	public Map<String, Object> getStopUserListAdmin(Search search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list = userDAO.getStopUserListAdmin(search);
		map.put("list", list);
		return map;
	}
	
	



	

}
