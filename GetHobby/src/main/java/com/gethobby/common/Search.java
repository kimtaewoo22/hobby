package com.gethobby.common;

import java.util.List;

public class Search {
	
	/// Field
	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	private int endRowNum;
	private int startRowNum;
	private String category;
	private List<String> hashtag;
	private int articleTypeCode;
	
	/// Constructor
	public Search() {
	}
	
	/// Getter, Setter
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getHashtag() {
		return hashtag;
	}

	public void setHashtag(List<String> hashtag) {
		this.hashtag = hashtag;
	}

	public int getArticleTypeCode() {
		return articleTypeCode;
	}

	public void setArticleTypeCode(int articleTypeCode) {
		this.articleTypeCode = articleTypeCode;
	}

	@Override
	public String toString() {
		return "Search [currentPage=" + currentPage + ", searchCondition=" + searchCondition + ", searchKeyword="
				+ searchKeyword + ", pageSize=" + pageSize + ", endRowNum=" + endRowNum + ", startRowNum=" + startRowNum
				+ ", category=" + category + ", hashtag=" + hashtag + ", articleTypeCode=" + articleTypeCode + "]";
	}
	
	

}
