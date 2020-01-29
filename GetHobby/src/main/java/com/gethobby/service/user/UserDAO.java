package com.gethobby.service.user;

import java.util.List;
import java.util.Map;

import com.gethobby.common.Search;
import com.gethobby.service.domain.User;

//ȸ���������� CRUD �߻�ȭ/ĸ��ȭ�� DAO Interface Definition
public interface UserDAO {

	//Insert
	public void addUser(User	user) throws Exception;
	
	//Select
	public User getUser(String userId)throws Exception;
	
	//Select
	public List<String> getUserHashtag(String userId)throws Exception;
	
	//Update
	public void deleteUser(User user)throws Exception;
	
	//Update
	public void updateUser(User user)throws Exception;
	
	//Update
	public void changeUserCreator(User user) throws Exception;
	
	//Insert
	public void addHashtag(Map map) throws Exception;
	
	//Select
	public List<String> getUserListAdmin(Search search) throws Exception;
	
	//Select
	public List<String> getStopUserListAdmin(Search search) throws Exception;
}
