package common.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.CourseBean;
import common.config.Config;
import common.dao.CourseDao;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		/*out.write("**********"+Config.DB_CONFIG+"**********");
		out.write("<br>");
		out.write("**********"+CourseDao.loadById(2)+"**********");
		out.write("<br>");
		CourseBean cbean = CourseDao.loadById(2);
		out.write("**********"+cbean.getArticleid()+"**********");
		out.write("<br>");
		out.write("**********"+cbean.getTitle()+"**********");
		out.write("<br>");
		out.write("**********"+cbean.getGradetype()+"**********");*/
		
		String editor = request.getParameter("editor");
		out.write(editor);
		System.out.println("************"+editor);
		out.flush();
		out.close();
	}

}
