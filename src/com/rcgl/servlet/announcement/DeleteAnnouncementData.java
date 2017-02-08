package com.rcgl.servlet.announcement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcgl.dao.AnnouncementDao;

/**
 * 删除对应公告id的公告数据
 * @author lims
 * @date 2015-05-07
 * */
@WebServlet(name="DeleteAnnouncementData",urlPatterns="/servlet/DeleteAnnouncementData")
public class DeleteAnnouncementData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteAnnouncementData() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		AnnouncementDao.delete(Integer.parseInt(id));//删除对于id的announcement表数据
		
		out.write("公告列表中ID为"+id+"的数据删除成功！");
		
		System.out.println("====DeleteAnnouncementData删除公告完成====");
		
		out.flush();
		out.close();
	}

}
