package com.gethobby.service.article.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gethobby.common.Search;
import com.gethobby.service.article.ArticleService;
import com.gethobby.service.domain.Article;
import com.gethobby.service.domain.User;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = { "classpath:config/context-common.xml",
		 "classpath:config/context-mybatis.xml",
		 "classpath:config/context-transaction.xml"})
public class ArticleTest {
	
	@Autowired
	@Qualifier("articleServiceImpl")
	private ArticleService articleService;
	
	
//	@Test
	public void testAddArticle() throws Exception {
		Article article = new Article();
		User user = new User();
		user.setUserId("a@google.com");
		article.setUser(user);
		article.setBoardCode("0");
		article.setArticleType("002");
		article.setArticleTitle("두번째 게시글");
		article.setArticleContent("두번째 게시글 입니다.");
		
		articleService.addArticle(article);
		
	}
	
//	@Test
	@Rollback(value = false)
	public void testGetArticle() throws Exception {
		Article article = articleService.getArticle(3);
//		Assert.assertEquals(10, article.getTotalView());
		articleService.updateTotalView(article.getArticleNo());
		
		
		Assert.assertEquals("a@google.com", article.getUser().getUserId());
		Assert.assertNull("Null 아님", article.getArticleImage());
		System.out.println("게시글 내용: " + article.getArticleContent() );
		System.out.println("\n\n\n\n 게시글: " + article + "\n\n\n\n ");
	}
	
//	@Test
	public void testUpdateArticle() throws Exception {
		
		Article article = articleService.getArticle(2);
		article.setArticleTitle("두번째 게시글 제목은 수정되었다...");
//		article.setArticleContent(article.getArticleContent());
		articleService.updateArticle(article);
		
		
		Assert.assertEquals("두번째 게시글 제목은 수정되었다...", article.getArticleTitle());
		
	}
	
//	@Test
	public void testDeleteArticle() throws Exception {
		
//		Article article = articleService.getArticle(2);
		
		articleService.deleteArticle(2);
		
		Assert.assertNull(articleService.getArticle(2));
		
	}
	
//	@Test
	public void testGetFreeBoardTotalCount() throws Exception {
		
		Search search = new Search();
		
		search.setArticleType("000");
		search.setSearchCondition("0");
		search.setSearchKeyword("자유");
		
		System.out.println("\n\n\n\n\n\n " + articleService.getFreeBoardTotalCount(search));
		
		
		
	}

	
	
	
	
	@Test
	public void testGetFreeBoardList() throws Exception {
		Search search = new Search();
		
		search.setArticleType("001");
		search.setSearchCondition("1");
		search.setSearchKeyword("꿀팁");
		
		List<Article> articleList = articleService.getFreeBoardList(search);
//		System.out.println("\n\n\n\n\n\n " + articleList + "\n\n\n\n\n\n ");
		
		System.out.println("\n\n\n\n\n\n ");
//		for(int i = 0; i < articleList.size(); i++) {
		for(Article article : articleList) {
			System.out.println("\t\t\t\t\t " + article);
		}
		System.out.println(articleService.getFreeBoardTotalCount(search));
		System.out.println("\n\n\n\n\n\n ");
	}
}
