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
import com.rcgl.utils.UploadImageUtil;

/**
 * 上传头像
 * */
@WebServlet(urlPatterns = { "/RCGLServer/UpdatePhotoServlet" })
public class UpdatePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UpdatePhotoServlet() {
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
		
		System.out.println("====UpdatePhotoServlet上传图片文件====");
		
		String file = request.getParameter("file");
		String userid = request.getParameter("userid");
		
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");///设置日期格式
		String timepath = df.format(now);
		
		String filepath = request.getSession().getServletContext().getRealPath("/upload")+"/"+userid+"/photo";
		String filename = "headphoto"+userid+".png";
		if(file!=null){
			/*上传图片文件到服务端*/
			UploadImageUtil.uploadImage(file, filepath, filename);
			
			String photoUrl = userid+"/photo/headphoto"+userid+".png";
			
			UserBean ubean = new UserBean();
			ubean = UserDao.loadById(Integer.parseInt(userid));
			ubean.setPhoto(photoUrl);
			UserDao.update(ubean);
			
			out.write(photoUrl);
		}
	}
}
