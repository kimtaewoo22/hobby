package com.gethobby.service.article.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.common.Search;
import com.gethobby.service.article.ArticleDAO;
import com.gethobby.service.article.ArticleService;
import com.gethobby.service.domain.Article;

@Service("articleServiceImpl")
public class ArticleSerivceImpl implements ArticleService {

	@Autowired
	@Qualifier("articleDAOImpl")
	private ArticleDAO articleDAO;
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}
	
	public ArticleSerivceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addArticle(Article article) throws Exception {
		articleDAO.addArticle(article);

	}

	@Override
	public Article getArticle(int articleNo) throws Exception {
		
		return articleDAO.getArticle(articleNo);
	}

	@Override
	public void updateTotalView(int articleNo) throws Exception {
		articleDAO.updateTotalView(articleNo);
		
	}

	@Override
	public void updateArticle(Article article) throws Exception {
		articleDAO.updateArticle(article);
		
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		articleDAO.deleteArticle(articleNo);
		
	}

	@Override
	public int getFreeBoardTotalCount(Search search) throws Exception {
		
		return articleDAO.getFreeBoardTotalCount(search);
	}

	@Override
	public List<Article> getFreeBoardList(Search search) throws Exception {
		
		List<Article> articleList = articleDAO.getFreeBoardList(search);
		
		return articleList;
	}


}
