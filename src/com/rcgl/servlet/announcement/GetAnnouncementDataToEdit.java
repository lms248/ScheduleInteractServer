package com.rcgl.servlet.announcement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.rcgl.bean.AnnouncementBean;
import com.rcgl.dao.AnnouncementDao;

/**
 * 获取公告数据到编辑平台
 * @author lims
 * @date 2015-05-07
 * */
@WebServlet(name="GetAnnouncementDataToEdit",urlPatterns="/servlet/GetAnnouncementDataToEdit")
public class GetAnnouncementDataToEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAnnouncementDataToEdit() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/*response.setContentType("application/json");*/
		PrintWriter out = response.getWriter();
		
		String id_str = request.getParameter("id");
		int id = Integer.parseInt(id_str);
		
		AnnouncementBean anbean = new AnnouncementBean();
		anbean = AnnouncementDao.loadById(id);
		
		String briefinfo = anbean.getBriefinfo();
		String contents = anbean.getContents();
			
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("briefinfo", briefinfo);
		jsonObject.put("contents", contents);
		
		out.write(jsonObject.toString());
		
		System.out.println("====GetAnnouncementDataToEdit获取公告到编辑平台====");
			
		out.flush();
		out.close();
	}

}
