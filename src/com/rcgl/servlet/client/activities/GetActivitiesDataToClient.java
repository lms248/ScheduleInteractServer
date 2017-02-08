package com.rcgl.servlet.client.activities;

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

import com.rcgl.bean.ActivitiesBean;
import com.rcgl.bean.UserBean;
import com.rcgl.dao.ActivitiesDao;
import com.rcgl.dao.UserDao;

/**
 * 获取某条活动数据
 * @author lims
 * @date 2015-04-29
 * */
@WebServlet(urlPatterns = { "/RCGLServer/GetActivitiesDataToClient" })
public class GetActivitiesDataToClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetActivitiesDataToClient() {
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
		
		System.out.println("*************GetActivitiesDataToClient*****************");
		
		
		/** 获取客户端发送过来的数据 */
		String activitiesid = request.getParameter("activitiesid");
		String userid = request.getParameter("userid");
		System.out.println("activitiesid="+activitiesid);
		System.out.println("userid="+userid);
		/*Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);*/
		
		/*读取数据库数据*/
		if(activitiesid.equals("0")){
			out.write("0");
		}
		else {
			ActivitiesBean actbean = new ActivitiesBean();
			actbean = ActivitiesDao.loadById(Integer.parseInt(activitiesid));
			
			JSONObject jsonObject1 = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray jsonArray= new JSONArray();
			if(actbean==null){
				System.out.println("没有活动");
				out.write("0");
				return;
			}
			
			UserBean ubean = new UserBean();
			ubean = UserDao.loadById(actbean.getUserid());
			String photo = ubean.getPhoto();
			String image = actbean.getImage();
			String participant = actbean.getParticipant();
			String collector = actbean.getCollector();
			if(photo==null) photo="";
			if(image==null) image="";
			if(participant==null) participant="";
			if(collector==null) collector=""; 
			
			
			/*标记该用户是否加入了该活动，isJoin = "-1"为我发起，isJoin = "0"为未加入，isJoin = "1"为已加入*/
			String[] pt = participant.split(",");
			String isJoin = "0";//未加入
			if(Integer.valueOf(userid)==actbean.getUserid()){
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
			System.out.println("participant_name"+participant_name);
			jsonObject1.put("activitiesid", actbean.getId());
			jsonObject1.put("userid", actbean.getUserid());
			jsonObject1.put("username", ubean.getUsername());
			jsonObject1.put("photo", photo);
			jsonObject1.put("dotime", actbean.getDotime());
			jsonObject1.put("content", actbean.getContent());
			jsonObject1.put("image", image);
			jsonObject1.put("max_number_people", actbean.getMax_number_people());
			jsonObject1.put("already_number_people", actbean.getAlready_number_people());
			jsonObject1.put("participant", participant_name);
			jsonObject1.put("sort", actbean.getSort());
			jsonObject1.put("isJoin", isJoin);
			jsonObject1.put("isCollect", isCollect);
			jsonObject1.put("time", actbean.getTime());
			jsonArray.add(jsonObject1);

			jsonObject2.put("activities", jsonArray);
			out.write(jsonObject2.toString());
			System.out.println(jsonObject2.toString());
		}
		
		out.flush();
		out.close();
	}

}
