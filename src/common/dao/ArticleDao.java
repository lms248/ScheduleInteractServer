package common.dao;

import static common.db.DbUtils.dbUtils;

import java.sql.SQLException;

import common.bean.ArticleBean;
import common.logger.Logger;
import common.logger.LoggerManger;

public class ArticleDao {
	private static Logger log=LoggerManger.getLogger();
	
	/**
	 * 加载文章数据
	 * @param courseid
	 * @return
	 * @author lims
	 */
	public static ArticleBean loadById(int articleid){
		ArticleBean bean=null;
		try {
			bean=dbUtils.read(ArticleBean.class, "where articleid=?", articleid);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 插入课程列表数据
	 * @param 
	 * @return 
	 * @author lims
	 */
	public static int save(ArticleBean bean){
		try {
			return dbUtils.insert(bean);
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
}
