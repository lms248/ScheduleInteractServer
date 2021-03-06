package com.rcgl.servlet.announcement;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcgl.bean.AnnouncementBean;
import com.rcgl.dao.AnnouncementDao;

/**
 * 添加公告数据到数据库
 * @author lims
 * @date 2015-05-07
 * */
@WebServlet(name="AddAnnouncementData",urlPatterns="/servlet/AddAnnouncementData")
public class AddAnnouncementData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddAnnouncementData() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		/*获取需要添加到数据库的数据*/
		String briefinfo = request.getParameter("briefInfo");
		String contents = request.getParameter("contents");
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		/*将获取到的数据形成bean形式*/
		AnnouncementBean anbean = new AnnouncementBean();
		anbean.setBriefinfo(briefinfo);
		anbean.setContents(contents);
		anbean.setTime(time);
		
		/*将bean数据存入数据库*/
		AnnouncementDao.save(anbean);
		
		System.out.println("===="+time+" AddAnnouncementData添加公告完成====");
		
		out.flush();
		out.close();
	}

}
