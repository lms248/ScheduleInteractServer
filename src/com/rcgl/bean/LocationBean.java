package com.rcgl.bean;

import common.db.Pojo;
/**
 * 附近的人定位bean
 * @author lims
 * @date 2015-05-05
 */
public class LocationBean extends Pojo {
	private static final long serialVersionUID = 1L;
	/** 用户ID */
	private int id;
	/** 用户ID */
	private int userid;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	/** 时间 */
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
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
