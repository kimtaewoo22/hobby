package com.gethobby.service.article.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gethobby.common.Search;
import com.gethobby.service.article.ArticleService;
import com.gethobby.service.article.ReplyService;
import com.gethobby.service.domain.Article;
import com.gethobby.service.domain.Favor;
import com.gethobby.service.domain.User;
import com.gethobby.service.user.UserService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = { "classpath:config/context-common.xml",
		 "classpath:config/context-mybatis.xml",
		 "classpath:config/context-transaction.xml"})
public class ArticleTest {
	
	@Autowired
	@Qualifier("articleServiceImpl")
	private ArticleService articleService;
	
	@Autowired
	@Qualifier("replyServiceImpl")
	private ReplyService replyService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
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
		
		articleService.addBoardArticle(article);
		
	}
	
//	@Test
	@Rollback(value = false)
	public void testGetArticle() throws Exception {
		int articleNo = 10;
		Article article = articleService.getBoardArticle(articleNo);
//		Assert.assertEquals(10, article.getTotalView());
		articleService.updateTotalView(article.getArticleNo());
		
		
//		Assert.assertEquals("a@google.com", article.getUser().getUserId());
//		Assert.assertNull("Null 아님", article.getArticleImage());
		article = articleService.getBoardArticle(articleNo);
		System.out.println("게시글 조회수: " + article.getTotalView() );
		System.out.println("\n\n\n\n 게시글: " + article + "\n\n\n\n ");
	}
	
//	@Test
	public void testUpdateArticle() throws Exception {
		
		Article article = articleService.getBoardArticle(2);
		article.setArticleTitle("두번째 게시글 제목은 수정되었다...");
//		article.setArticleContent(article.getArticleContent());
		articleService.updateBoardArticle(article);
		
		
		Assert.assertEquals("두번째 게시글 제목은 수정되었다...", article.getArticleTitle());
		
	}
	
//	@Test
	public void testDeleteArticle() throws Exception {
		
//		Article article = articleService.getArticle(2);
		
		int articleNo = 15;
		
		articleService.getBoardArticle(articleNo);
		Assert.assertEquals(1, replyService.getBoardReplyTotalCount(articleNo));
		
		
		articleService.deleteBoardArticle(articleNo);
		
		Assert.assertNull(articleService.getBoardArticle(articleNo));
		Assert.assertEquals(0, replyService.getBoardReplyTotalCount(articleNo));
		
	}
	
//	@Test
	public void testGetFreeBoardTotalCount() throws Exception {
		
		Search search = new Search();
		
		search.setArticleType("000");
		search.setSearchCondition("0");
		search.setSearchKeyword("자유");
		
		System.out.println("\n\n\n\n\n\n " + articleService.getFreeBoardTotalCount(search));
		
		
		
	}

	
	
	
	
//	@Test
	public void testGetFreeBoardList() throws Exception {
		Search search = new Search();
		
		search.setStartRowNum(1);
		search.setEndRowNum(3);
		search.setArticleType("000");
		search.setSearchCondition("0");
		search.setSearchKeyword("자유");
		
		Map<String, Object> map = articleService.getFreeBoardList(search);
		
		List<Article> articleList = (List<Article>) map.get("list");
//		System.out.println("\n\n\n\n\n\n " + articleList + "\n\n\n\n\n\n ");
		
		System.out.println("\n\n\n\n\n\n ");
//		for(int i = 0; i < articleList.size(); i++) {
		for(Article article : articleList) {
			System.out.println("\t\t\t\t\t " + article);
		}
		System.out.println(articleService.getFreeBoardTotalCount(search));
		System.out.println("\n\n\n\n\n\n ");
	}
	
	
	
	
//	@Test
	public void testGetPhotoBoardList() throws Exception {
		Search search = new Search();
		
		search.setStartRowNum(1);
		search.setEndRowNum(3);
		
		Map<String, Object> map = articleService.getPhotoBoardList(search);
		
		System.out.println("\n\n\n\n\n\n " + map.get("list") + "\n\n\n\n\n\n ");
		
//		search.setSearchCondition("0");
//		search.setSearchKeyword("사진");
//		
//		List<Article> articleList = articleService.getPhotoBoardList(search);
////		System.out.println("\n\n\n\n\n\n " + articleList + "\n\n\n\n\n\n ");
//		
//		System.out.println("\n\n\n\n\n\n ");
////		for(int i = 0; i < articleList.size(); i++) {
//		for(Article article : articleList) {
//			System.out.println("\t\t\t\t\t " + article);
//		}
		System.out.println(articleService.getPhotoBoardTotalCount(search));
		System.out.println("\n\n\n\n\n\n ");
	}
	
	@Test
//	@Rollback(value=false)
	public void testAddFavor() throws Exception {
		
		Favor favor = new Favor();
		Map<String, Object> map = userService.getUser("a@google.com");
		User user = (User)map.get("user");
		
		int articleNo = 10;
		Article article = articleService.getBoardArticle(articleNo);
		
		
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println(article.getTotalFavor());
		System.out.println("\n\n\n\n\n\n\n");
		
		Assert.assertEquals(3, article.getTotalFavor());
		
		favor.setArticle(article);
		favor.setUser(user);
		articleService.addFavor(favor);
		
		article = articleService.getBoardArticle(articleNo);
		Assert.assertEquals(4, article.getTotalFavor());
		
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println(article.getTotalFavor());
		System.out.println("\n\n\n\n\n\n\n");
		
		
	}
	
//	@Test
	public void testDeleteFavor() throws Exception {
		Favor favor = new Favor();
		Map<String, Object> map = userService.getUser("a@google.com");
		User user = (User)map.get("user");
		
		int articleNo = 10;
		Article article = articleService.getBoardArticle(articleNo);
		
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println(article.getTotalFavor());
		System.out.println("\n\n\n\n\n\n\n");
		
		Assert.assertEquals(1, article.getTotalFavor());
		
		favor.setArticle(article);
		favor.setUser(user);
		articleService.deleteFavor(favor);
		
		article = articleService.getBoardArticle(articleNo);
		Assert.assertEquals(0, article.getTotalFavor());
		
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println(article.getTotalFavor());
		System.out.println("\n\n\n\n\n\n\n");
		
	}
	
	
	
}
