package com.gethobby.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gethobby.common.Search;
import com.gethobby.service.domain.Article;
import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserDAO;

@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//Constructor
	public UserDAOImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addUser(User user) throws Exception {		
		sqlSession.insert("UserMapper.addUser",user);
	}

	@Override
	public User getUser(String userId) throws Exception {
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}

	@Override
	public void deleteUser(User user) throws Exception {
		sqlSession.update("UserMapper.deleteUser", user);
	}

	@Override
	public void updateUser(User user) throws Exception {
		sqlSession.update("UserMapper.updateUser",user);
	}

	@Override
	public void changeUserCreator(User user) throws Exception {
		sqlSession.update("UserMapper.chageUserCreator",user);
	}

	@Override
	public void addHashtag(Map map) throws Exception {
		Map<String, String> resultmap = new HashMap<String, String>();
		for (int j = 0; j < ((List)map.get("list")).size(); j++) {
			resultmap.put("userId", (String)map.get("userId"));
			resultmap.put("hashCode",(String)((List<String>)map.get("list")).get(j));
			sqlSession.insert("UserMapper.addHashtag", resultmap);
		}		
	}
	@Override
	public List<String> getUserListAdmin(Search search) throws Exception {
		return sqlSession.selectList("UserMapper.getUserList",search);
	}

	@Override
	public List<String> getStopUserListAdmin(Search search) throws Exception {
		return sqlSession.selectList("UserMapper.getStopUserList",search);
	}

	@Override
	public List<String> getUserHashtag(String userId) throws Exception {		
		return sqlSession.selectList("UserMapper.getUserHashtag",userId);
	}

	@Override
	public void updateNewPassword(User user) throws Exception {
		sqlSession.update("UserMapper.updateNewPassword",user);
	}

	@Override
	public void updateHashtag(Map<String, Object> map) throws Exception {
		User user = new User();
		user = (User)map.get("user");
		sqlSession.delete("UserMapper.deleteHashtag",user.getUserId());
		
		map.put("userId", user.getUserId());	
		
		List<String> list = new ArrayList<String>();
		list = (List)map.get("list");
		for (int i = 0; i < list.size(); i++) {
			map.put("hashCode", list.get(i));
			sqlSession.insert("UserMapper.addHashtag", map);
		}				
		
	}

	@Override
	public void addNotice(Article article) throws Exception {
		sqlSession.insert("NoticeMapper.addNotice",article);
	}

	@Override
	public Article getNotice(int articleNo) throws Exception {
		return sqlSession.selectOne("NoticeMapper.getNotice",articleNo);
	}

	@Override
	public void updateNotice(Article article) throws Exception {
		sqlSession.update("NoticeMapper.updateNotice",article);
	}

	@Override
	public List<Article> getNoticeList(Search search) throws Exception {
		return sqlSession.selectList("NoticeMapper.getNoticeList",search);
	}
}
