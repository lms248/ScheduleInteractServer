package common.client;

import common.bean.CourseBean;
import common.config.Config;
import common.dao.CourseDao;

public class Client {

	public static void main(String[] args) {
		
		//Config.DB_CONFIG="D:/workspace/WebBase/WebContent/WEB-INF/config/c3p0-config.xml";
		System.out.println(Config.DB_CONFIG);
		System.out.println("**********"+CourseDao.loadById(1)+"**********");
		CourseBean cbean = CourseDao.loadById(1);
		System.out.println(cbean.getArticleid());
		System.out.println(cbean.getTitle());
		
	}

}
