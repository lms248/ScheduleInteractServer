package com.rcgl.servlet.client.schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.rcgl.bean.ScheduleBean;
import com.rcgl.dao.ScheduleDao;

/**
 * 获取日程列表
 * */
@WebServlet(description = "获取日程列表", urlPatterns = { "/RCGLServer/GetScheduleListToClient" })
public class GetScheduleListToClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetScheduleListToClient() {
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
		
		System.out.println("*************GetScheduleListToClient*****************");
		
		
		/*获取客户端发送过来的数据*/
		String userid = request.getParameter("userid");
		System.out.println("userid="+userid);
		/*Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);*/
		
		/*读取数据库数据*/
		if(userid.equals("0")){
			System.out.println(0);
			out.write("0");
		}
		else {
			List<ScheduleBean> scheduleList = new ArrayList<ScheduleBean>();
			scheduleList = ScheduleDao.loadByUserID(Integer.parseInt(userid));
			
			JSONObject jsonObject1 = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray jsonArray= new JSONArray();
			if(scheduleList.size()==0){
				System.out.println("没有日程");
				out.write("0");
				return;
			}
			for(int i=0;i<scheduleList.size();i++){
				jsonObject1.put("scheduleid", scheduleList.get(i).getId());
				jsonObject1.put("userid", scheduleList.get(i).getUserid());
				jsonObject1.put("dotime", scheduleList.get(i).getDotime());
				jsonObject1.put("content", scheduleList.get(i).getContent());
				jsonObject1.put("open", scheduleList.get(i).getOpen());
				jsonObject1.put("alarm", scheduleList.get(i).getAlarm());
				jsonObject1.put("status", scheduleList.get(i).getStatus());
				jsonObject1.put("time", scheduleList.get(i).getTime());
				jsonArray.add(jsonObject1);
			}
			jsonObject2.put("schedules", jsonArray);
			out.write(jsonObject2.toString());
			System.out.println(jsonObject2.toString());
		}
		
		out.flush();
		out.close();
	}

}
