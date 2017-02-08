package com.rcgl.dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rcgl.bean.UserBean;
import common.logger.Logger;
import common.logger.LoggerManger;

public class UserDao {
	private static Logger log=LoggerManger.getLogger();
	
	/**
	 * 加载所有用户数据列表
	 * @param 
	 * @return List
	 * @author lims
	 * @date 2015-03-01
	 */
	public static List<UserBean> loadAllUser(){
		List<UserBean> userList=new ArrayList<UserBean>();
		try {
			userList=dbUtils.query(UserBean.class, " ");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return userList;
	}
	

	/**
	 * 加载用户数据
	 * @param userid
	 * @return
	 * @author lims
	 * @date 2015-03-01
	 */
	public static UserBean loadById(int userid){
		UserBean bean=null;
		try {
			bean=dbUtils.read(UserBean.class, "where id=?", userid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 根据用户类型加载对应用户列表
	 * @param usertype
	 * @return List
	 * @author lims
	 * @date 2015-03-01
	 */
	public static List<UserBean> loadByUserType(String usertype){
		List<UserBean> userlist=new ArrayList<UserBean>();
		try {
			userlist=dbUtils.query(UserBean.class, "where usertype like ?", "%"+usertype+"%");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return userlist;
	}
	
	/**
	 * 根据用户名加载对应的用户资料
	 * @param username
	 * @return bean
	 * @author lims
	 * @date 2015-03-01
	 */
	public static UserBean loadByUsername(String username){
		UserBean ubean = new UserBean();
		try {
			ubean=dbUtils.read(UserBean.class, "where username = ?", username);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return ubean;
	}
	
	/**
	 * 根据用户名模糊查询对应的用户资料
	 * @param username
	 * @return List
	 * @author lims
	 * @date 2015-05-06
	 */
	public static List<UserBean> loadByUsernameLike(String username){
		List<UserBean> userlist=new ArrayList<UserBean>();
		try {
			userlist=dbUtils.query(UserBean.class, "where username like ?", "%"+username+"%");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return userlist;
	}
	
	/**
	 * 查询用户数据列表的数据总数量
	 * @param 
	 * @return int
	 * @author lims
	 * @date 2015-03-01
	 */
	public static int Count(){
		int userCount=0;
		try {
			userCount=dbUtils.stat(UserBean.class, "select COUNT(*) from user");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return userCount;
	}
	
	/**
	 * 插入用户数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-01
	 */
	public static int save(UserBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 更新用户数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-04
	 */
	public static int update(UserBean bean){
		try {
			return dbUtils.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 同步更新多个用户数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-18
	 */
	public static int[] updateMultiUser(List list){
		try {
			return dbUtils.update(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除某条用户列表数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-01
	 */
	public static int delete(int id){
		try {
			return dbUtils.delete(UserBean.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
