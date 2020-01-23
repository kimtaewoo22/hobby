package com.gethobby.service.domain;

import java.sql.Date;

public class HobbyClass {

	/// Field
	private int hobbyClassId;
	private User user;
	private Event event;
	private String hobbyClassName;
	private String hobbyClassImage;
	private String hobbyClassIntro;
	private int hobbyClassPrice;
	private String kitName;
	private String kitImage;
	private int kitPrice;
	private String kitIntro;
	private Date startDate;
	private Date endDate;
	private Date lessonDate;
	private String category;
	private String hashtag;
	private int lessonTotalCount;
	private int hobbyClassPersonnel;
	private String hobbyClassState;
	private int totalMoney;
	private int steamCount;
	private int totalGrade;
	private boolean steamCheck;
	
	/// Constructor
	public HobbyClass() {
	}
	
	/// Method
	public int getHobbyClassId() {
		return hobbyClassId;
	}
	
	public void setHobbyClassId(int hobbyClassId) {
		this.hobbyClassId = hobbyClassId;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public String getHobbyClassName() {
		return hobbyClassName;
	}
	
	public void setHobbyClassName(String hobbyClassName) {
		this.hobbyClassName = hobbyClassName;
	}
	
	public String getHobbyClassImage() {
		return hobbyClassImage;
	}
	
	public void setHobbyClassImage(String hobbyClassImage) {
		this.hobbyClassImage = hobbyClassImage;
	}
	
	public String getHobbyClassIntro() {
		return hobbyClassIntro;
	}
	
	public void setHobbyClassIntro(String hobbyClassIntro) {
		this.hobbyClassIntro = hobbyClassIntro;
	}
	
	public int getHobbyClassPrice() {
		return hobbyClassPrice;
	}
	
	public void setHobbyClassPrice(int hobbyClassPrice) {
		this.hobbyClassPrice = hobbyClassPrice;
	}
	
	public String getKitName() {
		return kitName;
	}
	
	public void setKitName(String kitName) {
		this.kitName = kitName;
	}
	
	public String getKitImage() {
		return kitImage;
	}
	
	public void setKitImage(String kitImage) {
		this.kitImage = kitImage;
	}
	
	public int getKitPrice() {
		return kitPrice;
	}
	
	public void setKitPrice(int kitPrice) {
		this.kitPrice = kitPrice;
	}
	
	public String getKitIntro() {
		return kitIntro;
	}
	
	public void setKitIntro(String kitIntro) {
		this.kitIntro = kitIntro;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getLessonDate() {
		return lessonDate;
	}
	
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getHashtag() {
		return hashtag;
	}
	
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
	public int getLessonTotalCount() {
		return lessonTotalCount;
	}
	
	public void setLessonTotalCount(int lessonTotalCount) {
		this.lessonTotalCount = lessonTotalCount;
	}
	
	public int getHobbyClassPersonnel() {
		return hobbyClassPersonnel;
	}
	
	public void setHobbyClassPersonnel(int hobbyClassPersonnel) {
		this.hobbyClassPersonnel = hobbyClassPersonnel;
	}
	
	public String getHobbyClassState() {
		return hobbyClassState;
	}
	
	public void setHobbyClassState(String hobbyClassState) {
		this.hobbyClassState = hobbyClassState;
	}
	
	public int getTotalMoney() {
		return totalMoney;
	}
	
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public int getSteamCount() {
		return steamCount;
	}
	
	public void setSteamCount(int steamCount) {
		this.steamCount = steamCount;
	}
	
	public int getTotalGrade() {
		return totalGrade;
	}
	
	public void setTotalGrade(int totalGrade) {
		this.totalGrade = totalGrade;
	}
	
	public boolean isSteamCheck() {
		return steamCheck;
	}
	
	public void setSteamCheck(boolean steamCheck) {
		this.steamCheck = steamCheck;
	}

	@Override
	public String toString() {
		return "HobbyClass [hobbyClassId=" + hobbyClassId + ", user=" + user + ", event=" + event + ", hobbyClassName="
				+ hobbyClassName + ", hobbyClassImage=" + hobbyClassImage + ", hobbyClassIntro=" + hobbyClassIntro
				+ ", hobbyClassPrice=" + hobbyClassPrice + ", kitName=" + kitName + ", kitImage=" + kitImage
				+ ", kitPrice=" + kitPrice + ", kitIntro=" + kitIntro + ", startDate=" + startDate + ", endDate="
				+ endDate + ", lessonDate=" + lessonDate + ", category=" + category + ", hashtag=" + hashtag
				+ ", lessonTotalCount=" + lessonTotalCount + ", hobbyClassPersonnel=" + hobbyClassPersonnel
				+ ", hobbyClassState=" + hobbyClassState + ", totalMoney=" + totalMoney + ", steamCount=" + steamCount
				+ ", totalGrade=" + totalGrade + ", steamCheck=" + steamCheck + "]";
	}
	
	
	
}