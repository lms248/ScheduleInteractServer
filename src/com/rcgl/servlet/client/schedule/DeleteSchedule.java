package com.rcgl.servlet.client.schedule;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcgl.dao.ScheduleDao;

/**
 * 删除对应id的日程数据
 * @author lims
 * @date 2015-06-15
 * */
@WebServlet(urlPatterns="/RCGLServer/DeleteSchedule")
public class DeleteSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteSchedule() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String scheduleid = request.getParameter("scheduleid");
		ScheduleDao.delete(Integer.parseInt(scheduleid));//删除对于id的schedule表数据
		
		out.write("日程列表中ID为"+scheduleid+"的数据删除成功！");
		
		System.out.println("====DeleteSchedule删除日程完成====");
		
		out.flush();
		out.close();
	}

}
