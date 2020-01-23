package com.gethobby.service.domain;

public class Lesson {
	
	/// Field
	private int lessonId;
	private HobbyClass hobbyClass;
	private String lessonTitle;
	private String lessonImage;
	private String lessonIntro;
	private String lessonContent;
	private String lessonProject;
	private String lessonVideo;
	
	/// Constructor 
	public Lesson() {
	}
	
	/// Getter, Setter
	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public HobbyClass getHobbyClass() {
		return hobbyClass;
	}

	public void setHobbyClass(HobbyClass hobbyClass) {
		this.hobbyClass = hobbyClass;
	}

	public String getLessonTitle() {
		return lessonTitle;
	}

	public void setLessonTitle(String lessonTitle) {
		this.lessonTitle = lessonTitle;
	}

	public String getLessonImage() {
		return lessonImage;
	}

	public void setLessonImage(String lessonImage) {
		this.lessonImage = lessonImage;
	}

	public String getLessonIntro() {
		return lessonIntro;
	}

	public void setLessonIntro(String lessonIntro) {
		this.lessonIntro = lessonIntro;
	}

	public String getLessonContent() {
		return lessonContent;
	}

	public void setLessonContent(String lessonContent) {
		this.lessonContent = lessonContent;
	}

	public String getLessonProject() {
		return lessonProject;
	}

	public void setLessonProject(String lessonProject) {
		this.lessonProject = lessonProject;
	}

	public String getLessonVideo() {
		return lessonVideo;
	}

	public void setLessonVideo(String lessonVideo) {
		this.lessonVideo = lessonVideo;
	}

	@Override
	public String toString() {
		return "Lesson [lessonId=" + lessonId + ", hobbyClass=" + hobbyClass + ", lessonTitle=" + lessonTitle
				+ ", lessonImage=" + lessonImage + ", lessonIntro=" + lessonIntro + ", lessonContent=" + lessonContent
				+ ", lessonProject=" + lessonProject + ", lessonVideo=" + lessonVideo + "]";
	}
	
	
	

}
