package com.rcgl.servlet.client.message;

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
import com.rcgl.bean.MessageBean;
import com.rcgl.bean.UserBean;
import com.rcgl.dao.ActivitiesDao;
import com.rcgl.dao.MessageDao;
import com.rcgl.dao.UserDao;

/**
 * 获取好友列表
 * */
@WebServlet(description = "获取好友列表", urlPatterns = { "/RCGLServer/GetMessageListToClient" })
public class GetMessageListToClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetMessageListToClient() {
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
		
		System.out.println("*************GetMessageListToClient*****************");
		
		
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
			List<MessageBean> messageList = new ArrayList<MessageBean>();
			messageList = MessageDao.loadByReceiver(Integer.parseInt(userid));
			UserBean ubean = new UserBean();
			
			JSONObject jsonObject1 = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray jsonArray= new JSONArray();
			
			ActivitiesBean actbean = new ActivitiesBean();
			for(int i=messageList.size()-1;i>=0;i--){
				ubean = UserDao.loadById(messageList.get(i).getSender());
				jsonObject1.put("id", messageList.get(i).getId());
				jsonObject1.put("senderid", messageList.get(i).getSender());
				jsonObject1.put("senderName", ubean.getUsername());
				if(ubean.getPhoto()==null){
					jsonObject1.put("senderPhoto", "");
				}
				else jsonObject1.put("senderPhoto", ubean.getPhoto());
				jsonObject1.put("receiverid", messageList.get(i).getReceiver());
				jsonObject1.put("activitiesid", messageList.get(i).getActivitiesid());
				if(messageList.get(i).getType().equals("2")){
					actbean = ActivitiesDao.loadById(messageList.get(i).getActivitiesid());
					jsonObject1.put("activities_content", actbean.getContent());
				}
				jsonObject1.put("type", messageList.get(i).getType());
				jsonObject1.put("status", messageList.get(i).getStatus());
				jsonObject1.put("time", messageList.get(i).getTime());
				jsonArray.add(jsonObject1);
			}
			jsonObject2.put("message", jsonArray);
			out.write(jsonObject2.toString());
			System.out.println(jsonObject2.toString());
		}
		
		out.flush();
		out.close();
	}

}
