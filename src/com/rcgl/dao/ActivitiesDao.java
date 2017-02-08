package com.rcgl.dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rcgl.bean.ActivitiesBean;
import common.logger.Logger;
import common.logger.LoggerManger;

public class ActivitiesDao {
	private static Logger log=LoggerManger.getLogger();
	
	/**
	 * 加载所有活动数据列表
	 * @param 
	 * @return List
	 * @author lims
	 * @date 2015-03-24
	 */
	public static List<ActivitiesBean> loadAllActivities(){
		List<ActivitiesBean> activitiesList=new ArrayList<ActivitiesBean>();
		try {
			activitiesList=dbUtils.query(ActivitiesBean.class, "order by id asc");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return activitiesList;
	}
	

	/**
	 * 加载活动数据
	 * @param activitiesid
	 * @return
	 * @author lims
	 * @date 2015-03-24
	 */
	public static ActivitiesBean loadById(int activitiesid){
		ActivitiesBean bean=null;
		try {
			bean=dbUtils.read(ActivitiesBean.class, "where id=?", activitiesid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 根据用户ID加载对应活动列表
	 * @param userid
	 * @return List
	 * @author lims
	 * @date 2015-03-24
	 */
	public static List<ActivitiesBean> loadByUserID(int userid){
		List<ActivitiesBean> activitieslist=new ArrayList<ActivitiesBean>();
		try {
			activitieslist=dbUtils.query(ActivitiesBean.class, "where userid=? order by dotime ASC", userid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return activitieslist;
	}
	
	/**
	 * 根据活动内容加载对应的活动资料
	 * @param content
	 * @return List
	 * @author lims
	 * @date 2015-03-24
	 */
	public static List<ActivitiesBean> loadByContent(String content){
		List<ActivitiesBean> activitieslist=new ArrayList<ActivitiesBean>();
		try {
			activitieslist=dbUtils.query(ActivitiesBean.class, "where content like ?", "%"+content+"%");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return activitieslist;
	}
	
	/**
	 * 查询活动数据列表的数据总数量
	 * @param 
	 * @return int
	 * @author lims
	 * @date 2015-03-24
	 */
	public static int Count(){
		int activitiesCount=0;
		try {
			activitiesCount=dbUtils.stat(ActivitiesBean.class, "select COUNT(*) from activities");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return activitiesCount;
	}
	
	/**
	 * 插入活动数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-24
	 */
	public static int save(ActivitiesBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 更新活动数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-24
	 */
	public static int update(ActivitiesBean bean){
		try {
			return dbUtils.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 删除某条活动列表数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-24
	 */
	public static int delete(int id){
		try {
			return dbUtils.delete(ActivitiesBean.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
