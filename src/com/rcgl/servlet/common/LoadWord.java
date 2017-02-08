package com.rcgl.servlet.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * 加载word文档数据到编辑框
 * @author lims
 * */
@WebServlet(name="LoadWord",urlPatterns="/servlet/LoadWord")
public class LoadWord extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoadWord() {
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
		
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");///设置日期格式
		String time = df.format(now);
		
		String timefolder = time.replaceAll("/","");
		
		/*上传文件*/
		String uploadPath = this.getServletContext().getRealPath("/upload/cache/"+timefolder);
		StringBuffer sb = new StringBuffer();
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists())
		{
			uploadDir.mkdirs();
		}
		MultipartRequest multi =null;
		try {
			multi = new MultipartRequest(request, uploadPath, 10*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		sb.append("以下为请求页面全部参数:<br/>");
	
		@SuppressWarnings("rawtypes")
		Enumeration params = multi.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			String value = multi.getParameter(name);
			sb.append(name+"="+value+"<br/>");
		}
		sb.append("<br/>");
		sb.append("以下为全部上传文件：<br/>");
		@SuppressWarnings("rawtypes")
		Enumeration files = multi.getFileNames();
				
		while (files.hasMoreElements()) {
			String name = (String) files.nextElement();
			String filename = multi.getFilesystemName(name);
			String originalFilename = multi.getOriginalFileName(name);
			String type = multi.getContentType(name);
			File f = multi.getFile(name);
			sb.append("请求页面上传表单名："+name+"<br/>");
			sb.append("服务器端文件名称："+filename+"<br/>");
			sb.append("客户端文件名称："+originalFilename+"<br/>");
			sb.append("文件类型："+type+"<br/>");
            if(f!=null)
            {
    			sb.append("文件全名："+f.toString()+"<br/>");
    			sb.append("文件是否上传成功"+f.exists()+"<br/>");
    			sb.append("服务器端文件大小："+f.length()+"<br/>");
            }
			sb.append("<br/>");
			
			try {
				String contents = PoiWordToHtml.getWordAndStyle(uploadPath,filename);
				out.write(contents);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("====LoadWord加载word文档数据到编辑框====");
		
		out.flush();
		out.close();
	}

}
