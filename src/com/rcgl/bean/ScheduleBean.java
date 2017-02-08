package com.rcgl.bean;

import common.db.Pojo;
/**
 * 日程bean
 * @author lims
 * @date 2015-03-07
 */
public class ScheduleBean extends Pojo {
	private static final long serialVersionUID = 1L;
	/** 日程ID */
	private int id;
	/** 用户ID */
	private int userid;
	/** 日程内容 */
	private String content;
	/** 日程实行时间 */
	private String dotime;
	/** 是否公开标记，0为不公开，1为公开 */
	private int open;
	/** 是否设置日程提醒标记，0为不提醒，1为提醒 */
	private int alarm;
	/** 日程完成状态,0为未完成，1为完成 */
	private int status;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDotime() {
		return dotime;
	}
	public void setDotime(String dotime) {
		this.dotime = dotime;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public int getAlarm() {
		return alarm;
	}
	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
