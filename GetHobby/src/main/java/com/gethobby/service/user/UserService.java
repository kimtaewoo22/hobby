package com.gethobby.service.user;

import java.util.List;
import java.util.Map;

import com.gethobby.common.Search;
import com.gethobby.service.domain.User;

public interface UserService {

	//회원 가입
	public void addUser(Map map) throws Exception;
	
	//회원 상세조회
	public User getUser(String userId)throws Exception;
	
	//회원 탈퇴
	public void deleteUser(User user)throws Exception;
	
	//회원 정보 수정
	public void updateUser(User user)throws Exception;
	
	//크리에이터 전환
	public void changeUserCreator(User user) throws Exception;
	
	//회원 목록
	public Map<String,Object> getUserListAdmin(Search search)throws Exception;
	
	//정지 회원 목록
	public Map<String,Object> getStopUserListAdmin(Search search) throws Exception;
}
