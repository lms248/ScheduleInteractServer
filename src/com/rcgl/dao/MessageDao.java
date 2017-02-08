package com.rcgl.dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rcgl.bean.MessageBean;
import common.logger.Logger;
import common.logger.LoggerManger;

public class MessageDao {
	private static Logger log=LoggerManger.getLogger();
	
	/**
	 * 加载所有消息数据列表
	 * @param 
	 * @return List
	 * @author lims
	 * @date 2015-03-05
	 */
	public static List<MessageBean> loadAllMessage(){
		List<MessageBean> messageList=new ArrayList<MessageBean>();
		try {
			messageList=dbUtils.query(MessageBean.class, " ");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return messageList;
	}
	

	/**
	 * 加载作业数据
	 * @param messageid
	 * @return
	 * @author lims
	 * @date 2015-03-05
	 */
	public static MessageBean loadById(int messageid){
		MessageBean bean=null;
		try {
			bean=dbUtils.read(MessageBean.class, "where id=?", messageid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 加载作业数据
	 * @param senderid
	 * @return
	 * @author lims
	 * @date 2015-03-05
	 */
	public static MessageBean loadBySenderId(int senderid){
		MessageBean bean=null;
		try {
			bean=dbUtils.read(MessageBean.class, "where sender=?", senderid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 根据年级加载对应消息列表
	 * @param receiver
	 * @return List
	 * @author lims
	 * @date 2015-03-05
	 */
	public static List<MessageBean> loadByReceiver(int receiver){
		List<MessageBean> messagelist=new ArrayList<MessageBean>();
		try {
			messagelist=dbUtils.query(MessageBean.class, "where receiver=?", receiver);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return messagelist;
	}
	
	/**
	 * 根据年级加载对应消息列表
	 * @param messagetype
	 * @return List
	 * @author lims
	 * @date 2015-03-05
	 */
	public static List<MessageBean> loadByMessageType(String messagetype){
		List<MessageBean> messagelist=new ArrayList<MessageBean>();
		try {
			messagelist=dbUtils.query(MessageBean.class, "where messagetype like ?", "%"+messagetype+"%");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return messagelist;
	}
	
	/**
	 * 根据消息名加载对应的消息资料
	 * @param messagename
	 * @return bean
	 * @author lims
	 * @date 2015-03-05
	 */
	public static MessageBean loadByMessagename(String messagename){
		MessageBean ubean = new MessageBean();
		try {
			ubean=dbUtils.read(MessageBean.class, "where messagename = ?", messagename);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return ubean;
	}
	
	/**
	 * 查询消息数据列表的数据总数量
	 * @param 
	 * @return int
	 * @author lims
	 * @date 2015-03-05
	 */
	public static int Count(){
		int messageCount=0;
		try {
			messageCount=dbUtils.stat(MessageBean.class, "select COUNT(*) from message");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return messageCount;
	}
	
	/**
	 * 插入消息数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-05
	 */
	public static int save(MessageBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 更新消息数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-05
	 */
	public static int update(MessageBean bean){
		try {
			return dbUtils.update(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 删除某条消息列表数据
	 * @param 
	 * @return 
	 * @author lims
	 * @date 2015-03-05
	 */
	public static int delete(int id){
		try {
			return dbUtils.delete(MessageBean.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
