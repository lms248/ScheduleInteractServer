package com.rcgl.dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rcgl.bean.LocationBean;
import common.logger.Logger;
import common.logger.LoggerManger;

/**
 * 定位数据操作
 * @author lims
 * @date 2015-05-05
 */
public class LocationDao {
	private static Logger log=LoggerManger.getLogger();
	
	/**
	 * 加载所有定位数据列表
	 * @param 
	 * @return List
	 * @author lims
	 * @date 2015-05-05
	 */
	public static List<LocationBean> loadAllLocation(){
		List<LocationBean> locationList=new ArrayList<LocationBean>();
		try {
			locationList=dbUtils.query(LocationBean.class, " ");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return locationList;
	}
	

	/**
	 * 加载定位数据
	 * @param locationid
	 * @return
	 * @author lims
	 * @date 2015-05-05
	 */
	public static LocationBean loadById(int locationid){
		LocationBean bean=null;
		try {
			bean=dbUtils.read(LocationBean.class, "where id=?", locationid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 根据用户ID加载定位数据
	 * @param userid
	 * @return
	 * @author lims
	 * @date 2015-05-05
	 */
	public static LocationBean loadByUserId(int userid){
		LocationBean bean=null;
		try {
			bean=dbUtils.read(LocationBean.class, "where userid=?", userid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 查询定位数据列表的数据总数量
	 * @param 
	 * @return int
	 * @author lims
	 * @date 2015-05-05
	 */
	public static int Count(){
		int locationCount=0;
		try {
			locationCount=dbUtils.stat(LocationBean.class, "select COUNT(*) from location");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return locationCount;
	}
	
	/**
	 * 插入定位数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-05-05
	 */
	public static int save(LocationBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 更新定位数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-05-05
	 */
	public static int update(LocationBean bean){
		try {
			return dbUtils.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 同步更新多个定位数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-05-05
	 */
	public static int[] updateMultiLocation(List list){
		try {
			return dbUtils.update(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除某条定位列表数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-05-05
	 */
	public static int delete(int id){
		try {
			return dbUtils.delete(LocationBean.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
