package com.gethobby.service.user.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserDAO;

//회원 관리 DAO CRUD 구현
@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO {

	@Autowired
	@Qualifier("sqlSession Template")
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
		
		System.out.println("@@@@@@@@@@@@@@@@@@@");
		sqlSession.insert("UserMapper.addUser",user);
	}

	@Override
	public User getUser(String userId) throws Exception {
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}

}
