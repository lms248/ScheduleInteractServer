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
 * 修改密码
 * @author lims
 * @date 2015-04-09
 * */
@WebServlet(urlPatterns = { "/RCGLServer/ChangePWServlet" })
public class ChangePWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePWServlet() {
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
		
		System.out.println("*************ChangePWServlet*****************");
		
		/*获取客户端发送过来的数据*/
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		/*读取数据库数据*/
		UserBean ubean = new UserBean();
		ubean = UserDao.loadById(Integer.parseInt(userid));
		
		if(password.equals(ubean.getPassword())){
			
			ubean.setPassword(newPassword);
			UserDao.update(ubean);
			
			out.write(newPassword);//输出给客户端
			System.out.println("===="+time+" ChangePWServlet 成功修改密码 ====");
		}
		else {
			out.write("-1");
			System.out.println("===="+time+" ChangePWServlet 密码错误，修改密码操作失败====");
		}
		
		out.flush();
		out.close();
	}

}
