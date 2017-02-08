package com.rcgl.bean;

import common.db.Pojo;
/**
 * 用户bean
 * @author lims
 * @date 2015-03-01
 */
public class UserBean extends Pojo {
	private static final long serialVersionUID = 1L;
	/** 用户ID */
	private int id;
	/** 用户名（账号） */
	private String username;
	/** 密码 */
	private String password;
	/** 头像 */
	private String photo;
	/** 用户昵称 */
	private String nickname;
	/** 签名描述 */
	private String signature;
	/** 手机号码 */
	private String phone;
	/** 邮箱 */
	private String email;
	/** 好友列表 */
	private String friend;
	/** 注册时间 */
	private String time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
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
