package com.gethobby.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserDAO;
import com.gethobby.service.user.UserService;


//회원 관리 서비스 구현
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
	public void addUser(User user) throws Exception {
		userDAO.addUser(user);
	}

	@Override
	public User getUser(String userId) throws Exception {
		return userDAO.getUser(userId);		
	}

}
