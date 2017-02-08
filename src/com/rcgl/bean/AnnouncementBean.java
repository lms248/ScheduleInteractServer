package com.rcgl.bean;

import common.db.Pojo;
/**
 * 公告bean
 * @author lims
 * @date 2015-05-07
 * */
public class AnnouncementBean extends Pojo {
	private static final long serialVersionUID = 1L;
	/** 公告ID */
	private int id;
	/** 公告简述 */
	private String briefinfo;
	/** 公告详细内容 */
	private String contents;
	/** 最近操作时间 */
	private String time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBriefinfo() {
		return briefinfo;
	}
	public void setBriefinfo(String briefinfo) {
		this.briefinfo = briefinfo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
