package com.rcgl.test;

import java.util.ArrayList;
import java.util.List;

import com.rcgl.bean.UserBean;
import com.rcgl.dao.UserDao;

import common.config.Config;
import common.db.Pojo;

public class UpdateMultiUserTest {

	public static void main(String[] args) {
		Config.DB_CONFIG="D:/workspace/RCGL/WebContent/WEB-INF/config/c3p0-config.xml";
		
		List<UserBean> userlist=new ArrayList<UserBean>();
		UserBean ubean = new UserBean();
		ubean.setId(23);
		ubean.setPassword("111");
		ubean.setTime("111");
		ubean.setFriend("24");
		ubean.setUsername("222");
		userlist.add(ubean);
		
		//UserDao.update(ubean);
		
		/*ubean = new UserBean();
		ubean.setId(24);
		ubean.setFriend("23");
		userlist.add(ubean);*/
		
		for(int i=0;i<userlist.size();i++){
			System.out.println("userid = "+userlist.get(i).getId());
			System.out.println("friend = "+userlist.get(i).getFriend());
		}
		
		UserDao.updateMultiUser(userlist);

	}

}
