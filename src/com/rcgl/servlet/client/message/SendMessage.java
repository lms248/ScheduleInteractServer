package com.rcgl.servlet.client.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcgl.bean.MessageBean;
import com.rcgl.dao.MessageDao;

/**
 * 消息管理，类型标记type："1"为请求添加好友消息，"2"为活动分享。
 * */
@WebServlet(urlPatterns = { "/RCGLServer/MessageServlet" })
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendMessage() {
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
		
		System.out.println("*************MessageServlet*****************");
		
		
		/*获取客户端发送过来的数据*/
		String userid = request.getParameter("uid");
		String friendid = request.getParameter("fid");
		String type = request.getParameter("type");
		System.out.println("userid="+userid);
		System.out.println("friendid="+friendid);
		System.out.println("type="+type);
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		/*读取数据库数据*/
		if(!userid.equals("0")&!friendid.equals("0")){
			MessageBean mbean = new MessageBean();
			int uid = Integer.parseInt(userid);
			mbean = MessageDao.loadBySenderId(uid);
			if(type.equals("1")){//添加好友请求
				int fid = Integer.parseInt(friendid);
				try{
					if(mbean!=null&&fid==mbean.getReceiver()&&type.equals("1")){
						System.out.println("您已经发送添加好友请求！");
						out.write("0");
						out.flush();
						out.close();
						return;
					}
				}catch(Exception e){
					
				}
				if(uid==fid){
					System.out.println("不能自己给自己发消息！");
					out.print("-1");
				}
				else {
					if(mbean==null)mbean = new MessageBean();
					mbean.setSender(uid);
					mbean.setReceiver(fid);
					mbean.setType("1");
					mbean.setStatus(0);
					mbean.setTime(time);
					
					/*将bean数据存入数据库*/
					MessageDao.save(mbean);
					out.print("1");
				}
			}
			else if(type.equals("2")){//活动分享
				String activitiesid = request.getParameter("activitiesid");
				if(activitiesid==null||activitiesid.equals("")){
					activitiesid = "0";
					out.print("-1");
					return;
				}
				if(!friendid.contains(",")){
					int fid = Integer.parseInt(friendid);
					mbean.setSender(uid);
					mbean.setReceiver(fid);
					mbean.setType(type);
					mbean.setActivitiesid(Integer.parseInt(activitiesid));
					mbean.setStatus(0);
					mbean.setTime(time);
					
					/*将bean数据存入数据库*/
					MessageDao.save(mbean);
					out.print("1");
				}
				else {
					String[] fid_str = friendid.split(",");
					for(int i=0; i<fid_str.length; i++){
						mbean = new MessageBean();
						mbean.setSender(uid);
						mbean.setReceiver(Integer.parseInt(fid_str[i]));
						mbean.setType("2");
						mbean.setActivitiesid(Integer.parseInt(activitiesid));
						mbean.setStatus(0);
						mbean.setTime(time);
						
						/*将bean数据存入数据库*/
						MessageDao.save(mbean);
					}
					out.print("1");
				}
				
			}
		}
		
		else {
			out.print("-1");
		}
		out.flush();
		out.close();
	}

}
