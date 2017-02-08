package com.rcgl.servlet.client.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
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
 * 获取用户列表
 * */
@WebServlet(description = "获取用户列表", urlPatterns = { "/RCGLServer/GetUserListToClient" })
public class GetUserListToClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetUserListToClient() {
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
		
		System.out.println("*************GetUserListToClient*****************");
		
		
		/*获取客户端发送过来的数据*/
		String id = request.getParameter("id");
		
		/*Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);*/
		
		/*读取数据库数据*/
		if(id.equals("0")){//获取全部用户列表
			List<UserBean> userList = new ArrayList<UserBean>();
			userList = UserDao.loadAllUser();
			
			JSONObject jsonObject1 = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray jsonArray= new JSONArray();
			
			for(int i=userList.size()-1;i>=0;i--){
				jsonObject1.put("id", userList.get(i).getId());
				jsonObject1.put("username", userList.get(i).getUsername());
				jsonObject1.put("password", userList.get(i).getPassword());
				jsonObject1.put("photo", userList.get(i).getPhoto());
				jsonObject1.put("time", userList.get(i).getTime());
				jsonArray.add(jsonObject1);
			}
			jsonObject2.put("user", jsonArray);
			out.write(jsonObject2.toString());
		}
		else {//根据用户名（账号）查找好友
			String username = request.getParameter("username");
			String friendname = request.getParameter("friendname");
			System.out.println("username="+username);
			UserBean ubean = new UserBean();
			UserBean fbean = new UserBean();
			ubean = UserDao.loadByUsername(username);
			fbean = UserDao.loadByUsername(friendname);
			
			JSONObject jsonObject1 = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray jsonArray= new JSONArray();
			
			String f1 = ubean.getFriend();
			if(f1==null){
				f1="";
			}
			String friend[] = f1.split(",");
			String mark="0";//初始状态，该用户还不是好友
			System.out.println("ubean.getId()="+ubean.getId());
			for(int i=0;i<friend.length;i++){
				System.out.println("friend["+i+"]="+friend[i]);
				if(friend[i].equals(String.valueOf(fbean.getId()))){
					mark = "1";//该用户已是好友
					System.out.println("该用户已是好友");
				}
			}
			
				jsonObject1.put("id", fbean.getId());
				jsonObject1.put("username", fbean.getUsername());
				jsonObject1.put("photo", fbean.getPhoto());
				jsonObject1.put("mark", mark);
				jsonObject1.put("time", fbean.getTime());
				jsonArray.add(jsonObject1);
			jsonObject2.put("user", jsonArray);
			out.write(jsonObject2.toString());
		}
		out.flush();
		out.close();
	}

}
