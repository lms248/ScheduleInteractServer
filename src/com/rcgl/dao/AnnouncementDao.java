package com.rcgl.dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rcgl.bean.AnnouncementBean;
import common.logger.Logger;
import common.logger.LoggerManger;
/**
 * 公告dao
 * @author lims
 * @date 2015-05-07
 */
public class AnnouncementDao {
	private static Logger log=LoggerManger.getLogger();
	
	/**
	 * 加载公告数据
	 * @param announcementid
	 * @return
	 */
	public static AnnouncementBean loadById(int announcementid){
		AnnouncementBean bean=null;
		try {
			bean=dbUtils.read(AnnouncementBean.class, "where id=?", announcementid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 加载倒序排序后相应公告数据
	 * @param count
	 * @return
	 */
	public static AnnouncementBean loadByCount(int count){
		AnnouncementBean bean=null;
		try {
			bean=dbUtils.read(AnnouncementBean.class, "order by id desc limit ?", count);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
			
	/**
	 * 加载所有公告列表
	 * @param AnnouncementType
	 * @return List
	 * @author lims
	 */
	public static List<AnnouncementBean> loadAllAnnouncement(){
		List<AnnouncementBean> announcementlist=new ArrayList<AnnouncementBean>();
		try {
			announcementlist=dbUtils.query(AnnouncementBean.class, " ");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return announcementlist;
	}
	
	
	/**
	 * 查询公告表的数据总数量
	 * @param
	 * @return int
	 * @author lims
	 */
	public static int Count(){
		int announcementCount=0;
		try {
			announcementCount=dbUtils.stat(AnnouncementBean.class, "select COUNT(*) from announcement");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return announcementCount;
	}
	
	/**
	 * 插入公告列表数据
	 * @param 
	 * @return 
	 * @author lims
	 */
	public static int save(AnnouncementBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 更新公告列表数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2014-12-22 
	 */
	public static int update(AnnouncementBean bean){
		try {
			return dbUtils.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 删除某条公告列表数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2014-12-22
	 */
	public static int delete(int id){
		try {
			return dbUtils.delete(AnnouncementBean.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
