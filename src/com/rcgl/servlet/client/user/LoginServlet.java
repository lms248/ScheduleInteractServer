package com.rcgl.servlet.client.user;

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

import com.rcgl.bean.ActivitiesBean;
import com.rcgl.bean.ScheduleBean;
import com.rcgl.bean.UserBean;
import com.rcgl.dao.ActivitiesDao;
import com.rcgl.dao.ScheduleDao;
import com.rcgl.dao.UserDao;

/**
 * 用户登录
 * @author lims
 * */
@WebServlet(urlPatterns = { "/RCGLServer/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
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
		
		System.out.println("*************LoginServlet*****************");
		
		
		/*获取客户端发送过来的数据*/
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		/*读取数据库数据*/
		UserBean ubean = new UserBean();
		ubean = UserDao.loadByUsername(username);
		JSONArray jsonArray= new JSONArray();
		JSONObject jsonObject_out_1 = new JSONObject();
		JSONObject jsonObject_out_2 = new JSONObject();
		
		if(password.equals(ubean.getPassword())){
			System.out.println("===========LoginServlet username >> "+username);
			System.out.println("===========LoginServlet password >> "+password);
			System.out.println("===========LoginServlet time >> "+time);
			
			System.out.println("===="+time+" LoginServlet 登录成功 ====");
			
			String nickname = ubean.getNickname();
			String signature = ubean.getSignature();
			String userPhone = ubean.getPhone();
			String userEmail = ubean.getEmail();
			String photo = ubean.getPhoto();
			if(nickname==null) nickname = "";
			if(signature==null) signature = "";
			if(userPhone==null) userPhone = "";
			if(userEmail==null) userEmail = "";
			if(photo==null) photo = "";
			
			jsonObject_out_1.put("id", ubean.getId());
			jsonObject_out_1.put("username", ubean.getUsername());
			jsonObject_out_1.put("password", ubean.getPassword());
			jsonObject_out_1.put("photo", photo);
			jsonObject_out_1.put("nickname", nickname);
			jsonObject_out_1.put("signature", signature);
			jsonObject_out_1.put("userPhone", userPhone);
			jsonObject_out_1.put("userEmail", userEmail);
			jsonObject_out_1.put("schedule_object", getSchedule(ubean.getId()));
			jsonObject_out_1.put("friend_object", getFriend(ubean.getId()));
			jsonObject_out_1.put("activities_object", getActivities(ubean.getId()));
			jsonObject_out_1.put("time", ubean.getTime());
			jsonArray.add(jsonObject_out_1);
			jsonObject_out_2.put("user",jsonArray);
			out.write(jsonObject_out_2.toString());//输出给客户端
			
			
			System.out.println("===="+jsonObject_out_2.toString()+"====");
		}
		else {
			out.write("-1");
			System.out.println("===="+time+" LoginServlet 密码或用户名错误，登录失败====");
		}
		
		out.flush();
		out.close();
	}
	
	/** 获取用户日程 */
	private String getSchedule(int userid){
		List<ScheduleBean> scheduleList = new ArrayList<ScheduleBean>();
		scheduleList = ScheduleDao.loadByUserID(userid);
		
		JSONObject scheduleObject1 = new JSONObject();
		JSONObject scheduleObject2 = new JSONObject();
		JSONArray scheduleArray= new JSONArray();
		for(int i=0;i<scheduleList.size();i++){
			scheduleObject1.put("scheduleid", scheduleList.get(i).getId());
			scheduleObject1.put("userid", scheduleList.get(i).getUserid());
			scheduleObject1.put("dotime", scheduleList.get(i).getDotime());
			scheduleObject1.put("content", scheduleList.get(i).getContent());
			scheduleObject1.put("open", scheduleList.get(i).getOpen());
			scheduleObject1.put("alarm", scheduleList.get(i).getAlarm());
			scheduleObject1.put("status", scheduleList.get(i).getStatus());
			scheduleObject1.put("time", scheduleList.get(i).getTime());
			scheduleArray.add(scheduleObject1);
		}
		scheduleObject2.put("schedules", scheduleArray);
		return scheduleObject2.toString();
	}
	
	/** 获取用户好友 */
	private String getFriend(int userid){
		UserBean ubean = new UserBean();
		ubean = UserDao.loadById(userid);
		
		List<UserBean> userList = new ArrayList<UserBean>();
		UserBean fbean = new UserBean();
		
		String f1 = ubean.getFriend();
		if(f1==null){
			f1="";
		}
		String friend[] = f1.split(",");
		for(int i=0;i<friend.length;i++){
			if(!friend[i].equals("")){
				fbean = UserDao.loadById(Integer.parseInt(friend[i]));
				userList.add(fbean);
			}
		}
		
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		JSONArray jsonArray= new JSONArray();
		
		for(int i=userList.size()-1;i>=0;i--){
			jsonObject1.put("id", userList.get(i).getId());
			jsonObject1.put("username", userList.get(i).getUsername());
			//jsonObject1.put("password", userList.get(i).getPassword());
			jsonObject1.put("time", userList.get(i).getTime());
			jsonArray.add(jsonObject1);
		}
		jsonObject2.put("user", jsonArray);
		return jsonObject2.toString();
	}
	
	/** 获取活动 */
	private String getActivities(int userid){
		List<ActivitiesBean> activitiesList = new ArrayList<ActivitiesBean>();
		activitiesList = ActivitiesDao.loadAllActivities();
		
		UserBean ubean = new UserBean();
		
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		JSONArray jsonArray= new JSONArray();
		if(activitiesList.size()==0){
			System.out.println("没有活动");
			return "0";
		}
		
		for(int i=activitiesList.size()-1;i>=0;i--){
			ubean = UserDao.loadById(activitiesList.get(i).getUserid());
			String photo = ubean.getPhoto();
			String image = activitiesList.get(i).getImage();
			String participant = activitiesList.get(i).getParticipant();
			String collector = activitiesList.get(i).getCollector();
			if(photo==null) photo="";
			if(image==null) image="";
			if(participant==null) participant="";
			if(collector==null) collector=""; 
			
			
			/*标记该用户是否加入了该活动，isJoin = "-1"为我发起，isJoin = "0"为未加入，isJoin = "1"为已加入*/
			String[] pt = participant.split(",");
			String isJoin = "0";//未加入
			if(Integer.valueOf(userid)==activitiesList.get(i).getUserid()){
				isJoin = "-1";//我发起
			}
			else{
				for(int j=0;j<pt.length;j++){
					if(String.valueOf(userid).equals(pt[j])){
						isJoin = "1";//已加入
						break;
					}
				}
			}
			/*标记该用户是否加入了该活动，isCollect = "0"为未收藏,isCollect = "1"为已收藏*/
			String[] ct = collector.split(",");
			String isCollect = "0";
			for(int k=0;k<ct.length;k++){
				if(String.valueOf(userid).equals(ct[k])){
					isCollect = "1";
					break;
				}
			}
			
			/*将参与者id转换成参与者的用户名*/
			String participant_name = "";
			if(participant!=null && !participant.equals("")){
				String[] participant_id = participant.split(",");
				for(int n=0; n<participant_id.length; n++){
					if(!participant_id[n].equals("")){
						if(participant_name.equals("")){
							participant_name = UserDao.loadById(Integer.parseInt(participant_id[n])).getUsername();
						}
						else participant_name = participant_name+","+UserDao.loadById(Integer.parseInt(participant_id[n])).getUsername();
					}
				}
			}
			jsonObject1.put("activitiesid", activitiesList.get(i).getId());
			jsonObject1.put("userid", activitiesList.get(i).getUserid());
			jsonObject1.put("username", ubean.getUsername());
			jsonObject1.put("photo", photo);
			jsonObject1.put("dotime", activitiesList.get(i).getDotime());
			jsonObject1.put("content", activitiesList.get(i).getContent());
			jsonObject1.put("image", image);
			jsonObject1.put("max_number_people", activitiesList.get(i).getMax_number_people());
			jsonObject1.put("already_number_people", activitiesList.get(i).getAlready_number_people());
			jsonObject1.put("participant", participant_name);
			jsonObject1.put("sort", activitiesList.get(i).getSort());
			jsonObject1.put("isJoin", isJoin);
			jsonObject1.put("isCollect", isCollect);
			jsonObject1.put("time", activitiesList.get(i).getTime());
			jsonArray.add(jsonObject1);
		}
		jsonObject2.put("activities", jsonArray);
		return jsonObject2.toString();
	}
		
}
