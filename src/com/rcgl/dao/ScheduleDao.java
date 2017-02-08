package com.rcgl.dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rcgl.bean.ScheduleBean;
import common.logger.Logger;
import common.logger.LoggerManger;

public class ScheduleDao {
	private static Logger log=LoggerManger.getLogger();
	
	/**
	 * 加载所有日程数据列表
	 * @param 
	 * @return List
	 * @author lims
	 * @date 2015-03-07
	 */
	public static List<ScheduleBean> loadAllSchedule(){
		List<ScheduleBean> scheduleList=new ArrayList<ScheduleBean>();
		try {
			scheduleList=dbUtils.query(ScheduleBean.class, " ");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return scheduleList;
	}
	

	/**
	 * 加载日程数据
	 * @param scheduleid
	 * @return
	 * @author lims
	 * @date 2015-03-07
	 */
	public static ScheduleBean loadById(int scheduleid){
		ScheduleBean bean=null;
		try {
			bean=dbUtils.read(ScheduleBean.class, "where id=?", scheduleid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 根据用户ID加载对应日程列表
	 * @param userid
	 * @return List
	 * @author lims
	 * @date 2015-03-07
	 */
	public static List<ScheduleBean> loadByUserID(int userid){
		List<ScheduleBean> schedulelist=new ArrayList<ScheduleBean>();
		try {
			schedulelist=dbUtils.query(ScheduleBean.class, "where userid=? order by dotime ASC", userid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return schedulelist;
	}
	
	/**
	 * 根据日程名加载对应的日程资料
	 * @param content
	 * @return List
	 * @author lims
	 * @date 2015-03-07
	 */
	public static List<ScheduleBean> loadByContent(String content){
		List<ScheduleBean> schedulelist=new ArrayList<ScheduleBean>();
		try {
			schedulelist=dbUtils.query(ScheduleBean.class, "where content like ?", "%"+content+"%");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return schedulelist;
	}
	
	/**
	 * 查询日程数据列表的数据总数量
	 * @param 
	 * @return int
	 * @author lims
	 * @date 2015-03-07
	 */
	public static int Count(){
		int scheduleCount=0;
		try {
			scheduleCount=dbUtils.stat(ScheduleBean.class, "select COUNT(*) from schedule");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return scheduleCount;
	}
	
	/**
	 * 插入日程数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-07
	 */
	public static int save(ScheduleBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 更新日程数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-07
	 */
	public static int update(ScheduleBean bean){
		try {
			return dbUtils.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 删除某条日程列表数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-07
	 */
	public static int delete(int id){
		try {
			return dbUtils.delete(ScheduleBean.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
