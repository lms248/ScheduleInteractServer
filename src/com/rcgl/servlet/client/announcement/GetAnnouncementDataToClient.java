package com.rcgl.servlet.client.announcement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.rcgl.bean.AnnouncementBean;
import com.rcgl.dao.AnnouncementDao;
/**
 * 获取公告数据到客户端主页
 * @author lims
 */
@WebServlet(urlPatterns = { "/QGXServer/GetAnnouncementDataToClient" })
public class GetAnnouncementDataToClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAnnouncementDataToClient() {
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		AnnouncementBean anbean = null;
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray= new JSONArray();
		
		if(id==-1){//加载全部的公告
			List<AnnouncementBean> list = new ArrayList<AnnouncementBean>();
			list = AnnouncementDao.loadAllAnnouncement();
			
			for(int i=0; i<list.size(); i++){		
				jsonObject.put("id", list.get(i).getId());
				jsonObject.put("briefinfo", list.get(i).getBriefinfo());
				jsonObject.put("contents", "/upload/html/"+list.get(i).getContents());
				jsonObject.put("time", list.get(i).getTime());
				jsonArray.add(jsonObject);
			}
		}
		else if(id==0){//加载最新的公告
			anbean = AnnouncementDao.loadByCount(1);
			jsonObject.put("id", anbean.getId());
			jsonObject.put("briefinfo", anbean.getBriefinfo());
			jsonObject.put("contents", "/upload/html/"+anbean.getContents());
			jsonObject.put("time", anbean.getTime());
		}
		else {//加载对应id的公告
			anbean = AnnouncementDao.loadById(id);
			jsonObject.put("id", anbean.getId());
			jsonObject.put("briefinfo", anbean.getBriefinfo());
			jsonObject.put("contents", "/upload/html/"+anbean.getContents());
			jsonObject.put("time", anbean.getTime());
		}
		
		
		/** 将公告数据转化成JSONArray格式返回
		 * @data：2014.12.03
		 * @author：LiMusheng
		 **/
		jsonArray.add(jsonObject);
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("announcement",jsonArray);
		
		out.write(jsonObject2.toString());//输出给客户端
		
		System.out.println("====GetAnnouncementDataToClient获取公告数据到客户端====");
		
		out.flush();
		out.close();
	}

}
