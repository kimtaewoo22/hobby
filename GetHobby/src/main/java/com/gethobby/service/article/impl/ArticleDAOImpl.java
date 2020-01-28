package com.gethobby.service.article.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gethobby.common.Search;
import com.gethobby.service.article.ArticleDAO;
import com.gethobby.service.domain.Article;


@Repository("articleDAOImpl")
public class ArticleDAOImpl implements ArticleDAO {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public ArticleDAOImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addArticle(Article article) throws Exception {
		sqlSession.insert("BoardArticleMapper.addBoardArticle", article);

	}

	@Override
	public Article getArticle(int articleNo) throws Exception {
		
		return sqlSession.selectOne("BoardArticleMapper.getBoardArticle", articleNo);
	}
	
	@Override
	public void updateTotalView(int articleNo) throws Exception {
		sqlSession.update("BoardArticleMapper.updateTotalView", articleNo);
		
	}

	@Override
	public void updateArticle(Article article) throws Exception {
		sqlSession.update("BoardArticleMapper.updateBoardArticle", article);
		
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		sqlSession.delete("BoardArticleMapper.deleteBoardArticle", articleNo);
		
	}

	@Override
	public int getFreeBoardTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("BoardArticleMapper.getFreeBoardTotalCount", search);
		
	}

	@Override
	public List<Article> getFreeBoardList(Search search) throws Exception {
		List<Article> articleList = sqlSession.selectList("BoardArticleMapper.getFreeBoardList", search);
		
		
		
		return articleList;
	}


}
