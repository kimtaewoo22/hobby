package com.gethobby.service.article.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.common.Search;
import com.gethobby.service.article.ArticleDAO;
import com.gethobby.service.article.ArticleService;
import com.gethobby.service.domain.Article;
import com.gethobby.service.domain.Favor;

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
	public void addBoardArticle(Article article) throws Exception {
		articleDAO.addBoardArticle(article);

	}

	@Override
	public Article getBoardArticle(int articleNo) throws Exception {
		
		return articleDAO.getBoardArticle(articleNo);
	}

	@Override
	public void updateTotalView(int articleNo) throws Exception {
		articleDAO.updateTotalView(articleNo);
		
	}

	@Override
	public void updateBoardArticle(Article article) throws Exception {
		articleDAO.updateBoardArticle(article);
		
	}

	@Override
	public void deleteBoardArticle(int articleNo) throws Exception {
		articleDAO.deleteBoardArticle(articleNo);
		
	}

	@Override
	public int getFreeBoardTotalCount(Search search) throws Exception {
		
		return articleDAO.getFreeBoardTotalCount(search);
	}

	@Override
	public Map<String, Object> getFreeBoardList(Search search) throws Exception {
		
		List<Article> articleList = articleDAO.getFreeBoardList(search);
		
		
//		for (int i = 0; i < articleList.size(); i++) {
//			articleList.get(i).setPurchaseProd((Product)sqlSession.selectOne("ProductMapper.getProduct", list.get(i).getPurchaseProd().getProdNo()));
//		}
		
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		map.put("totalCount", articleDAO.getFreeBoardTotalCount(search));
		map.put("list", articleList);

		return map;
		
	}

	@Override
	public Map<String, Object> getPhotoBoardList(Search search) throws Exception {
		
		List<Article> articleList = articleDAO.getPhotoBoardList(search);
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		map.put("totalCount", articleDAO.getPhotoBoardTotalCount(search));
		map.put("list", articleList);

		return map;	
	}

	@Override
	public int getPhotoBoardTotalCount(Search search) throws Exception {
		return articleDAO.getPhotoBoardTotalCount(search);
	}

	@Override
	public void addFavor(Favor favor) throws Exception {
		articleDAO.addFavor(favor);
		
	}

	@Override
	public void deleteFavor(Favor favor) throws Exception {
		articleDAO.deleteFavor(favor);
		
	}



}
