package common.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {

	public FileUploadServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uploadPath = this.getServletContext().getRealPath("/upload");
        String output = FileUploadLogic.printUploadFiles(request, uploadPath);
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(output);
 		 
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
