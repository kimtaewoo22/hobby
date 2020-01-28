package com.gethobby.service.user;

import com.gethobby.service.domain.User;

public interface UserService {

	//회원가입
	public void addUser(User user) throws Exception;
	
	//회원상세조회
	public User getUser(String userId)throws Exception;
}
