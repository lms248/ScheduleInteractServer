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
 * 日程管理
 * @author lims
 * @date 2015-04-30
 * */
@WebServlet(urlPatterns = { "/RCGLServer/ScheduleManage" })
public class ScheduleManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScheduleManage() {
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
		
		System.out.println("*************ScheduleManage*****************");
		
		
		/*获取客户端发送过来的数据*/
		String userid = request.getParameter("userid");
		String scheduleid = request.getParameter("scheduleid");
		String operate = request.getParameter("operate");
		System.out.println("userid="+userid);
		System.out.println("scheduleid="+scheduleid);
		System.out.println("operate="+operate);
		
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		if(!userid.equals("0")){
			ScheduleBean sbean = new ScheduleBean();
			sbean = ScheduleDao.loadById(Integer.parseInt(scheduleid));
			if(operate.equals("complete")){
				String complete = request.getParameter("complete");
				sbean.setStatus(Integer.parseInt(complete));
			}
			else if(operate.equals("alarm")){
				String alarm = request.getParameter("alarm");
				sbean.setAlarm(Integer.parseInt(alarm));
			}
			else if(operate.equals("open")){
				String open = request.getParameter("open");
				sbean.setOpen(Integer.parseInt(open));
			}
			sbean.setTime(time);
			
			//将数据存储到数据库
			ScheduleDao.update(sbean);
				
			//读取该用户的日程数据到手机客户端
			out.write(getSchedule(Integer.parseInt(userid)));
			System.out.println(getSchedule(Integer.parseInt(userid)));
			
			//out.print("1");
				
		}
		else {
			out.print("-1");
		}
		out.flush();
		out.close();
	}
	
	/** 获取用户日程 */
	private String getSchedule(int userid){
		List<ScheduleBean> scheduleList = new ArrayList<ScheduleBean>();
		scheduleList = ScheduleDao.loadByUserID(userid);
		
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		JSONArray jsonArray= new JSONArray();
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
		return jsonObject2.toString();
	}
}
