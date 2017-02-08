package com.rcgl.bean;

import common.db.Pojo;
/**
 * 活动bean
 * @author lims
 * @date 2015-03-23
 */
public class ActivitiesBean extends Pojo {
	private static final long serialVersionUID = 1L;
	/** 活动ID */
	private int id;
	/** 发布者ID */
	private int userid;
	/** 参与者 */
	private String participant;
	/** 活动标题 */
	private String title;
	/** 活动内容 */
	private String content;
	/** 活动图片 */
	private String image;
	/** 活动时间 */
	private String dotime;
	/** 允许最多人数 */
	private int max_number_people;
	/** 已经报名人数 */
	private int already_number_people;
	/** 评论 */
	private String comment;
	/** 活动类型分类，“1”运动，“2”旅行，“3”学习，“休闲”，“5”其他 */
	private String sort;
	/** 收藏者 */
	private String collector;
	/** 标记 */
	private String mark;
	/** 发布时间 */
	private String time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDotime() {
		return dotime;
	}
	public void setDotime(String dotime) {
		this.dotime = dotime;
	}
	public int getMax_number_people() {
		return max_number_people;
	}
	public void setMax_number_people(int max_number_people) {
		this.max_number_people = max_number_people;
	}
	public int getAlready_number_people() {
		return already_number_people;
	}
	public void setAlready_number_people(int already_number_people) {
		this.already_number_people = already_number_people;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getCollector() {
		return collector;
	}
	public void setCollector(String collector) {
		this.collector = collector;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
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
