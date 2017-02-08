package common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.ArticleBean;
import common.bean.CourseBean;
import common.dao.ArticleDao;
import common.dao.CourseDao;

/**
 * Servlet implementation class EditorServlet
 */
@WebServlet("/EditorServlet")
public class EditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EditorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		String gradetype = request.getParameter("gradetype");
		int gt = Integer.parseInt(gradetype);
		String image = "../imgs/"+request.getParameter("image");
		String audio = "../audio/"+request.getParameter("audio");
		String contents = request.getParameter("contents");
		
		/*添加数据到数据表article*/
		ArticleBean abean = new ArticleBean();
		//abean.setArticleid(2);
		abean.setTitle(title);
		abean.setContents(contents);
		abean.setImage(image);
		abean.setAudio(audio);
		abean.setMark(0);
		abean.setTaskid(0);
		if(title!=null&&title!=""){
			ArticleDao.save(abean);
		}
		
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");///设置日期格式
		String time = df.format(now);
		/*添加数据到数据表course*/
		CourseBean cbean = new CourseBean();
		//cbean.setCourseid(15);
		cbean.setTitle(title);
		cbean.setImage(image);
		//cbean.setArticleid(2);
		cbean.setGradetype(gt);
		cbean.setCoursetype(0);
		cbean.setTime(time);
		if(title!=null&&title!=""){
			CourseDao.save(cbean);
		}
		
		out.print(title);
		out.print("<br>");
		out.print(gradetype);
		out.print("<br>");
		out.print(image);
		out.print("<br>");
		out.print(audio);
		out.print("<br>");
		out.print(contents);
		out.flush();
		out.close();
	}

}
