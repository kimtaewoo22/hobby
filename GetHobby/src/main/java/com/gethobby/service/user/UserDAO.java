package com.gethobby.service.user;

import com.gethobby.service.domain.User;

//ȸ���������� CRUD �߻�ȭ/ĸ��ȭ�� DAO Interface Definition
public interface UserDAO {

	//Insert
	public void addUser(User user) throws Exception;
	
	//Select
	public User getUser(String userId)throws Exception;
	
}
