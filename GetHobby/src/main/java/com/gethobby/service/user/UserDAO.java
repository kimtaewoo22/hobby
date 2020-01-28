package com.gethobby.service.user;

import com.gethobby.service.domain.User;

//회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface UserDAO {

	//Insert
	public void addUser(User user) throws Exception;
	
	//Select
	public User getUser(String userId)throws Exception;
	
}
