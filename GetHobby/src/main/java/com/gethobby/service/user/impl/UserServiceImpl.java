package com.gethobby.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.common.Search;
import com.gethobby.service.domain.Article;
import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserDAO;
import com.gethobby.service.user.UserService;


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
	public Map<String, Object> getUser(String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", userDAO.getUser(userId));
		map.put("list", userDAO.getUserHashtag(userId));
		return map;		
	}

	@Override
	public void deleteUser(User user) throws Exception {
		userDAO.deleteUser(user);
		
	}

	@Override
	public void updateUser(Map<String, Object> map) throws Exception {
		userDAO.updateUser((User)map.get("user"));
		userDAO.updateHashtag(map);
		
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

	@Override
	public void updateNewPassword(User user) throws Exception {
		userDAO.updateNewPassword(user);
	}

	@Override
	public void addNotice(Article article) throws Exception {
		userDAO.addNotice(article);
	}

	@Override
	public Article getNotice(int articleNo) throws Exception {
		return userDAO.getNotice(articleNo);
	}

	@Override
	public void updateNotice(Article article) throws Exception {
		userDAO.updateNotice(article);
	}

	@Override
	public List<Article> getNoticeList(Search search) throws Exception {
		return userDAO.getNoticeList(search);
	}


}
