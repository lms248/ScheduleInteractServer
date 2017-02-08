package com.rcgl.bean;

import common.db.Pojo;
/**
 * 消息bean
 * @author lims
 * @date 2015-03-05
 */
public class MessageBean extends Pojo {
	private static final long serialVersionUID = 1L;
	/** 消息ID */
	private int id;
	/** 消息发送者 */
	private int sender;
	/** 消息接受者 */
	private int receiver;
	/** 分享活动id */
	private int activitiesid;
	/** 消息类型,1为添加好友请求，2为活动分享 */
	private String type;
	/** 消息处理状态 */
	private int status;
	/** 发布时间 */
	private String time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public int getActivitiesid() {
		return activitiesid;
	}
	public void setActivitiesid(int activitiesid) {
		this.activitiesid = activitiesid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
