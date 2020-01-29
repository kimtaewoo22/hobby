package com.gethobby.service.user;

import java.util.List;
import java.util.Map;

import com.gethobby.common.Search;
import com.gethobby.service.domain.Article;
import com.gethobby.service.domain.User;

public interface UserService {

	//회원 가입
	public void addUser(Map map) throws Exception;
	
	//회원 상세조회
	public Map<String,Object> getUser(String userId)throws Exception;
	
	//회원 탈퇴
	public void deleteUser(User user)throws Exception;
	
	//회원 정보 수정
	public void updateUser(Map<String,Object> map)throws Exception;
	
	//크리에이터 전환
	public void changeUserCreator(User user) throws Exception;
	
	//회원 목록
	public Map<String,Object> getUserListAdmin(Search search)throws Exception;
	
	//정지 회원 목록
	public Map<String,Object> getStopUserListAdmin(Search search) throws Exception;
	
	//패스워드 변경
	public void updateNewPassword(User user) throws Exception;
	
	//공지사항 등록
	public void addNotice(Article article) throws Exception;
	
	//공지사항 상세조회
	public Article getNotice(int articleNo)throws Exception;
	
	//공지사항 수정
	public void updateNotice(Article article) throws Exception;
	
	//공지사항 목록 조회
	public List<Article> getNoticeList(Search search) throws Exception;	
	
}
