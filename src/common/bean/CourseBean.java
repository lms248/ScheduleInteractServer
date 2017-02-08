package common.bean;

import common.db.Pojo;

public class CourseBean extends Pojo {
	private static final long serialVersionUID = 1L;
	private int courseid;
	private String title;
	private String image;
	private int articleid;
	private int gradetype;
	private int coursetype;
	private String time;
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public int getGradetype() {
		return gradetype;
	}
	public void setGradetype(int gradetype) {
		this.gradetype = gradetype;
	}
	public int getCoursetype() {
		return coursetype;
	}
	public void setCoursetype(int coursetype) {
		this.coursetype = coursetype;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
