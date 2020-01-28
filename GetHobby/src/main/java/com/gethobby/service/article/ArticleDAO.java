package com.gethobby.service.article;

import java.util.List;

import com.gethobby.common.Search;
import com.gethobby.service.domain.Article;

public interface ArticleDAO {
	
	public void addArticle(Article article) throws Exception;
	
	public Article getArticle(int articleNo) throws Exception;
	
	public void updateTotalView(int articleNo) throws Exception;
	
	public void updateArticle(Article article) throws Exception;
	
	public void deleteArticle(int articleNo) throws Exception;
	
	public int getFreeBoardTotalCount(Search search) throws Exception;
	
	public List<Article> getFreeBoardList(Search search) throws Exception;

}
