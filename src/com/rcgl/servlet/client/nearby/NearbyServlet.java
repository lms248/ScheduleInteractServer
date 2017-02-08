package com.rcgl.servlet.client.nearby;

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

import com.rcgl.bean.LocationBean;
import com.rcgl.bean.UserBean;
import com.rcgl.dao.LocationDao;
import com.rcgl.dao.UserDao;
import com.rcgl.nearby.util.GPSUtil;

/**
 * 附近的人定位
 * @author lims
 * @date 2015-05-05
 */
@WebServlet(name="NearbyServlet",urlPatterns="/RCGLServer/NearbyServlet")
public class NearbyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("NearbyServlet附近的人定位");
		
		String userid = request.getParameter("userid");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		
		
		System.out.println("userid===="+userid);
		System.out.println("longitude===="+longitude);
		System.out.println("latitude===="+latitude);
		
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		LocationBean ltbean = new LocationBean();
		ltbean = LocationDao.loadByUserId(Integer.parseInt(userid));
		if(null==ltbean){//新增
			ltbean = new LocationBean();
			ltbean.setUserid(Integer.parseInt(userid));
			ltbean.setLatitude(latitude);
			ltbean.setLongitude(longitude);
			ltbean.setTime(time);
			LocationDao.save(ltbean);
		}
		else{//更新
			ltbean.setLatitude(latitude);
			ltbean.setLongitude(longitude);
			ltbean.setTime(time);
			LocationDao.update(ltbean);
		}
		
		
		UserBean ubean = new UserBean();
		ubean = UserDao.loadById(Integer.parseInt(userid));
		String friends = ubean.getFriend();
		if(friends==null) friends="";
		String[] friend = friends.split(",");
		
		// 显示附近的人
		List<LocationBean> locationList = new ArrayList<LocationBean>();
		locationList = LocationDao.loadAllLocation();
		ubean = new UserBean();
		
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		JSONArray jsonArray= new JSONArray();
		
		int isfriend = 0;//0表示不是朋友,也不是自己
		for(int i=0;i<locationList.size();i++){
			isfriend = 0;
			if(!friends.equals("")){
				for(int n=0; n<friend.length; n++){
					if(locationList.get(i).getUserid()==Integer.parseInt(friend[n])){
						System.out.println("getUserid()"+locationList.get(i).getUserid());
						System.out.println("friend[]"+friend[n]);
						isfriend = 1;//1表示是好友
						break;
					}
				}
				if(isfriend==0 && locationList.get(i).getUserid()==Integer.parseInt(userid)){
					isfriend = 2;//2表示是自己
				}
			}
			else{
				if(isfriend==0 && locationList.get(i).getUserid()==Integer.parseInt(userid)){
					isfriend = 2;//2表示是自己
				}
			}
			
			Double d = GPSUtil.computeDistance(Double.valueOf(latitude), Double.valueOf(longitude), Double.valueOf(locationList.get(i).getLatitude()), Double.valueOf(locationList.get(i).getLongitude()));
			String distance = d.intValue()+"";//米
			ubean = UserDao.loadById(locationList.get(i).getUserid());
			jsonObject1.put("locationid", locationList.get(i).getId());
			jsonObject1.put("userid", locationList.get(i).getUserid());
			jsonObject1.put("username", ubean.getUsername());
			jsonObject1.put("photo", ubean.getPhoto()+"");
			jsonObject1.put("nickname", ubean.getNickname()+"");
			jsonObject1.put("signature", ubean.getSignature()+"");
			jsonObject1.put("distance", distance+"");
			jsonObject1.put("isfriend", isfriend+"");
			jsonObject1.put("time", locationList.get(i).getTime());
			jsonArray.add(jsonObject1);
		}
		jsonObject2.put("location", jsonArray);
		out.write(jsonObject2.toString());
		System.out.println(jsonObject2.toString());
		
		out.flush();
		out.close();
	}
	
}
