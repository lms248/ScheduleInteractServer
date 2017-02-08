package common.dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.bean.CourseBean;
import common.logger.Logger;
import common.logger.LoggerManger;

public class CourseDao {
	private static Logger log=LoggerManger.getLogger();
	
	/**
	 * 加载课程数据
	 * @param courseid
	 * @return
	 */
	public static CourseBean loadById(int courseid){
		CourseBean bean=null;
		try {
			bean=dbUtils.read(CourseBean.class, "where courseid=?", courseid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 根据年级类型加载课程数据
	 * @param gradetype
	 * @return List
	 * @author lims
	 */
	public static List loadByCourseType(int gradetype){
		List courselist=new ArrayList();
		try {
			courselist=dbUtils.query(CourseBean.class, "where gradetype=?", gradetype);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return courselist;
	}
	
	/**
	 * 加载所有课程列表
	 * @param courseType
	 * @return List
	 * @author lims
	 */
	public static List loadAllCourse(){
		List courselist=new ArrayList();
		try {
			courselist=dbUtils.query(CourseBean.class, " ");
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return courselist;
	}
	
	/**
	 * 插入课程列表数据
	 * @param 
	 * @return 
	 * @author lims
	 */
	public static int save(CourseBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
}
