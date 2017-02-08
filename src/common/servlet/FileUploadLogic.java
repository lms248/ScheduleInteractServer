package common.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUploadLogic {
	public static String printUploadFiles(HttpServletRequest request,String uploadpath)
	{
		StringBuffer sb = new StringBuffer();
		File uploadDir = new File(uploadpath);
		if(!uploadDir.exists())
		{
			uploadDir.mkdirs();
		}
		MultipartRequest multi =null;
		try {
			multi = new MultipartRequest(request, uploadpath, 10*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
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
			
			//将上传的文件信息填入数据库
			
			
		}	
		
		return sb.toString();
	}

}
