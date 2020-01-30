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
import com.gethobby.service.domain.Favor;


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
	public void addBoardArticle(Article article) throws Exception {
		sqlSession.insert("BoardArticleMapper.addBoardArticle", article);

	}

	@Override
	public Article getBoardArticle(int articleNo) throws Exception {
		
		return sqlSession.selectOne("BoardArticleMapper.getBoardArticle", articleNo);
	}
	
	@Override
	public void updateTotalView(int articleNo) throws Exception {
		sqlSession.update("BoardArticleMapper.updateTotalView", articleNo);
		
	}

	@Override
	public void updateBoardArticle(Article article) throws Exception {
		sqlSession.update("BoardArticleMapper.updateBoardArticle", article);
		
	}

	@Override
	public void deleteBoardArticle(int articleNo) throws Exception {
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

	@Override
	public List<Article> getPhotoBoardList(Search search) throws Exception {
		List<Article> articleList = sqlSession.selectList("BoardArticleMapper.getPhotoBoardList", search);
		
		System.out.println("\t\t\t\t\t " + articleList);
		
		return articleList;
	}

	@Override
	public int getPhotoBoardTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("BoardArticleMapper.getPhotoBoardTotalCount", search);
	}

	@Override
	public void addFavor(Favor favor) throws Exception {
		sqlSession.insert("FavorMapper.addFavor", favor);
		
	}

	@Override
	public void deleteFavor(Favor favor) throws Exception {
		sqlSession.delete("FavorMapper.deleteFavor",  favor);
	}


}
