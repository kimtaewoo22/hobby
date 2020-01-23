package com.gethobby.service.domain;

import java.util.Date;

public class User {

	/// Field
	private String userId;
	private String sex;
	private String	role;
	private String phone;
	private String name;
	private String profileImage;
	private int totalReport;
	private String birth;
	private String password;
	private String rePassword;
	private String beforePassword;
	private String nickName;
	private String pastCode;
	private String address;
	private String detailAddress;
	private String reitreMessage;
	private String stopReason;
	private Date retireDate;
	private Date stopDate;
	private Date addDate;
	
	/// Constructor
	public User() {
	}
	
	/// Getter, Setter 
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public int getTotalReport() {
		return totalReport;
	}

	public void setTotalReport(int totalReport) {
		this.totalReport = totalReport;
	}

	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getBeforePassword() {
		return beforePassword;
	}

	public void setBeforePassword(String beforePassword) {
		this.beforePassword = beforePassword;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPastCode() {
		return pastCode;
	}

	public void setPastCode(String pastCode) {
		this.pastCode = pastCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getReitreMessage() {
		return reitreMessage;
	}

	public void setReitreMessage(String reitreMessage) {
		this.reitreMessage = reitreMessage;
	}

	public String getStopReason() {
		return stopReason;
	}

	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}

	public Date getRetireDate() {
		return retireDate;
	}

	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", sex=" + sex + ", role=" + role + ", phone=" + phone + ", name=" + name
				+ ", profileImage=" + profileImage + ", totalReport=" + totalReport + ", birth=" + birth + ", password="
				+ password + ", rePassword=" + rePassword + ", beforePassword=" + beforePassword + ", nickName="
				+ nickName + ", pastCode=" + pastCode + ", address=" + address + ", detailAddress=" + detailAddress
				+ ", reitreMessage=" + reitreMessage + ", stopReason=" + stopReason + ", retireDate=" + retireDate
				+ ", stopDate=" + stopDate + ", addDate=" + addDate + "]";
	}	
	
}
