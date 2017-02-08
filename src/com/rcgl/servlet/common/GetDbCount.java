package com.rcgl.servlet.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcgl.dao.ActivitiesDao;
import com.rcgl.dao.AnnouncementDao;
import com.rcgl.dao.LocationDao;
import com.rcgl.dao.MessageDao;
import com.rcgl.dao.ScheduleDao;
import com.rcgl.dao.UserDao;

/**
 * 获取数据的分页显示页数
 * @author lims
 * */
@WebServlet(name="GetDbCount",urlPatterns="/servlet/GetDbCount")
public class GetDbCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetDbCount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String dbtype = request.getParameter("dbtype"); 
		int pageCount = 0;
		if(dbtype.equals("userdb")){
			int dbcount = UserDao.Count();
			if(dbcount%20==0) pageCount=dbcount/20;
			else pageCount=dbcount/20+1;
			out.print(pageCount);
		}
		else if(dbtype.equals("scheduledb")){
			int dbcount = ScheduleDao.Count();
			if(dbcount%20==0) pageCount=dbcount/20;
			else pageCount=dbcount/20+1;
			out.print(pageCount);
		}
		else if(dbtype.equals("activitiesdb")){
			int dbcount = ActivitiesDao.Count();
			if(dbcount%20==0) pageCount=dbcount/20;
			else pageCount=dbcount/20+1;
			out.print(pageCount);
		}
		else if(dbtype.equals("messagedb")){
			int dbcount = MessageDao.Count();
			if(dbcount%20==0) pageCount=dbcount/20;
			else pageCount=dbcount/20+1;
			out.print(pageCount);
		}
		else if(dbtype.equals("locationdb")){
			int dbcount = LocationDao.Count();
			if(dbcount%20==0) pageCount=dbcount/20;
			else pageCount=dbcount/20+1;
			out.print(pageCount);
		}
		else if(dbtype.equals("announcementdb")){
			int dbcount = AnnouncementDao.Count();
			if(dbcount%10==0) pageCount=dbcount/10;
			else pageCount=dbcount/10+1;
			out.print(pageCount);
		}
		else out.print(pageCount);
	}

}
