package com.rcgl.servlet.client.friend;

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

import com.rcgl.bean.UserBean;
import com.rcgl.dao.UserDao;

/**
 * 获取好友列表
 * */
@WebServlet(description = "获取好友列表", urlPatterns = { "/RCGLServer/GetFriendListToClient" })
public class GetFriendListToClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetFriendListToClient() {
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
		
		System.out.println("*************GetFriendListToClient*****************");
		
		
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
			UserBean ubean = new UserBean();
			ubean = UserDao.loadById(Integer.parseInt(userid));
			
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
			
			//冒泡排序
			for (int i = 0; i < userList.size(); i++) {
				for(int j = 0; j<userList.size()-i-1; j++){
					//这里-i主要是每遍历一次都把最大的i个数沉到最底下去了，没有必要再替换了
					if(userList.get(j).getUsername().toString().compareTo(userList.get(j+1).getUsername().toString())<0){
						UserBean temp = userList.get(j);
						userList.set(j, userList.get(j+1));
						userList.set(j+1, temp);
					}
				}
			}
			
			JSONObject jsonObject1 = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray jsonArray= new JSONArray();
			String photo;
			for(int i=userList.size()-1;i>=0;i--){
				photo = userList.get(i).getPhoto();
				if(photo==null) photo="";
				jsonObject1.put("id", userList.get(i).getId());
				jsonObject1.put("username", userList.get(i).getUsername());
				//jsonObject1.put("password", userList.get(i).getPassword());
				jsonObject1.put("photo", photo);
				System.out.println("photo>>"+userList.get(i).getPhoto());
				jsonObject1.put("time", userList.get(i).getTime());
				jsonArray.add(jsonObject1);
			}
			jsonObject2.put("user", jsonArray);
			out.write(jsonObject2.toString());
			System.out.println(jsonObject2.toString());
		}
		
		out.flush();
		out.close();
	}

}
