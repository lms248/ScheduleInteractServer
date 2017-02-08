package common.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.CourseBean;
import common.dao.CourseDao;

/**
 * Servlet implementation class AddCourseData
 */
@WebServlet("/AddCourseData")
public class AddCourseData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCourseData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		/*获取需要添加到数据库的数据*/
		String courseid = request.getParameter("courseid").toString();
		String title = request.getParameter("title").toString();
		String image = "../imgs/"+request.getParameter("image").toString();
		String articleid = request.getParameter("articleid").toString();
		String gradetype = request.getParameter("gradetype").toString();
		String coursetype = "";
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");///设置日期格式
		String time = df.format(now);
		System.out.println("******************"+time);
		/*将获取到的数据形成bean形式*/
		CourseBean cbean = new CourseBean();
		cbean.setCourseid(Integer.parseInt(courseid));
		cbean.setTitle(title);
		cbean.setImage(image);
		cbean.setArticleid(Integer.parseInt(articleid));
		cbean.setGradetype(Integer.parseInt(gradetype));
		cbean.setCoursetype(Integer.parseInt(coursetype));
		cbean.setTime(time);
		
		/*将bean数据存入数据库*/
		CourseDao.save(cbean);
		out.print(CourseDao.save(cbean));
		
		out.flush();
		out.close();
	}

}
