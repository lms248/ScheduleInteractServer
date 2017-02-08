package com.rcgl.servlet.client.activities;

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
import com.rcgl.bean.UserBean;
import com.rcgl.dao.ActivitiesDao;
import com.rcgl.dao.UserDao;
import com.rcgl.utils.UploadImageUtil;

/**
 * 添加活动
 * @author lims
 * @date 2015-03-24
 * */
@WebServlet(urlPatterns = { "/RCGLServer/AddActivities" })
public class AddActivities extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddActivities() {
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
		
		System.out.println("*************AddActivities*****************");
		
		
		/*获取客户端发送过来的数据*/
		String userid = request.getParameter("userid");
		String content = request.getParameter("content");
		String dotime = request.getParameter("doTime");
		String image = request.getParameter("image");
		String max_number_people = request.getParameter("max_number_people");
		String sort = request.getParameter("sort");
		System.out.println("userid="+userid);
		System.out.println("content="+content);
		System.out.println("dotime="+dotime);
		System.out.println("max_number_people="+max_number_people);
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		String timepath = time.replaceAll("-", "");
		timepath = timepath.replaceAll(" ", "");
		timepath = timepath.replaceAll(":", "");
		String filename = "act"+timepath+".png";
		String filepath = request.getSession().getServletContext().getRealPath("/upload")+"/"+userid+"/activities";
		
		if(!userid.equals("0")&&image!=null){
			/*上传图片文件到服务端*/
			UploadImageUtil.uploadImage(image, filepath, filename);
			String imageUrl = userid+"/activities/"+filename;
			ActivitiesBean actbean = new ActivitiesBean();
			actbean.setUserid(Integer.parseInt(userid));
			actbean.setContent(content);
			actbean.setDotime(dotime);
			actbean.setImage(imageUrl);
			actbean.setMax_number_people(Integer.parseInt(max_number_people));
			actbean.setAlready_number_people(0);
			actbean.setComment("");
			actbean.setSort(sort);
			actbean.setTime(time);
			
			//将数据存储到数据库
			ActivitiesDao.save(actbean);
			
			//读取该用户的活动数据到手机客户端
			out.write(getActivities(Integer.parseInt(userid)));
			System.out.println(getActivities(Integer.parseInt(userid)));
			
			//out.print("1");
				
		}
		else {
			out.print("-1");
		}
		out.flush();
		out.close();
	}
	
	/** 获取用户活动 */
	private String getActivities(int userid){
		List<ActivitiesBean> activitiesList = new ArrayList<ActivitiesBean>();
		activitiesList = ActivitiesDao.loadAllActivities();
		
		UserBean ubean = new UserBean();
		
		
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		JSONArray jsonArray= new JSONArray();
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
