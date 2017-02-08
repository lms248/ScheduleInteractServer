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
 * 添加日程
 * */
@WebServlet(urlPatterns = { "/RCGLServer/AddSchedule" })
public class AddSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddSchedule() {
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
		
		System.out.println("*************AddSchedule*****************");
		
		
		/*获取客户端发送过来的数据*/
		String userid = request.getParameter("userid");
		String content = request.getParameter("content");
		String dotime = request.getParameter("doTime");
		String open = request.getParameter("openRead");
		String alarm = request.getParameter("alarm");
		System.out.println("userid="+userid);
		System.out.println("content="+content);
		System.out.println("dotime="+dotime);
		System.out.println("alarm="+alarm);
		System.out.println("open="+open);
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		if(!userid.equals("0")){
			ScheduleBean sbean = new ScheduleBean();
			sbean.setUserid(Integer.parseInt(userid));
			sbean.setContent(content);
			sbean.setDotime(dotime);
			sbean.setOpen(Integer.parseInt(open));
			sbean.setAlarm(Integer.parseInt(alarm));
			sbean.setStatus(0);
			sbean.setTime(time);
			
			//将数据存储到数据库
			ScheduleDao.save(sbean);
				
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
