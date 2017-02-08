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
 * 用户个人资料管理
 * @author lims
 * @date 2015-03-31
 * */
@WebServlet(urlPatterns = { "/RCGLServer/UserInfoManage" })
public class UserInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInfoManage() {
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
		
		System.out.println("*************UserInfoManage*****************");
		
		
		/*获取客户端发送过来的数据*/
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String signature = request.getParameter("signature");
		String userPhone = request.getParameter("phone");
		String userEmail = request.getParameter("email");
		
		System.out.println("username="+username);
		System.out.println("nickname="+nickname);
		System.out.println("signature="+signature);
		System.out.println("userPhone="+userPhone);
		System.out.println("userEmail="+userEmail);
		
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		UserBean ubean = new UserBean();
		ubean = UserDao.loadByUsername(username);
		ubean.setNickname(nickname);
		ubean.setSignature(signature);
		ubean.setPhone(userPhone);
		ubean.setEmail(userEmail);
		/*将数据写入数据库*/
		UserDao.update(ubean);
		
		out.write("1");
		
		System.out.println("===="+time+" 修改用户个人资料表完成！====");	
		out.flush();
		out.close();
	}

}
