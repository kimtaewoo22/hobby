package com.gethobby.service.user;

import com.gethobby.service.domain.User;

public interface UserService {

	//ȸ������
	public void addUser(User user) throws Exception;
	
	//ȸ������ȸ
	public User getUser(String userId)throws Exception;
}
