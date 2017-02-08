package com.rcgl.servlet.client.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rcgl.bean.UserBean;
import com.rcgl.dao.UserDao;

/**
 * 用户注册
 * */
@WebServlet(urlPatterns = { "/RCGLServer/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
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
		
		System.out.println("*************RegisterServlet*****************");
		
		
		/*获取客户端发送过来的数据*/
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		/*将数据写入数据库*/
		UserBean ubean = new UserBean();
		ubean = UserDao.loadByUsername(username);
		try{
			System.out.println("ubean.getUsername()="+ubean.getUsername());
		}catch(NullPointerException e){
			ubean = new UserBean();
			ubean.setUsername(username);
			ubean.setPassword(password);
			ubean.setTime(time);
			
			if(UserDao.save(ubean)==-1){
				out.write("-1");
				System.out.println("===="+time+" RegisterServlet客户端注册数据失败！====");
				return;
			}
			else {
				UserBean ubean_out = new UserBean();
				ubean_out = UserDao.loadByUsername(username);
				System.out.println("ubean_out.getId()="+ubean_out.getId());
				out.write(ubean_out.getId()+"");
				System.out.println("===="+time+" RegisterServlet客户端注册数据成功！====");
				out.flush();
				out.close();
				return;
			}
		}
		
		out.write("0");
		System.out.println("===="+time+" RegisterServlet客户端注册注册失败，该用户已存在！====");	
		out.flush();
		out.close();
	}

}
