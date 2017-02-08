package common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.CourseBean;
import common.dao.CourseDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class getCourseList
 */
@WebServlet("/GetCourseList")
public class GetCourseList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GetCourseList() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		
		String gradetype = request.getParameter("gradetype").toString();
		int gt = Integer.parseInt(gradetype);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<CourseBean> courseList = new ArrayList<CourseBean>();
		switch(gt){
		case 0: {//读取所有课程列表
			courseList= CourseDao.loadAllCourse();
			gradetype="所有课程";break;
		}
		case 1: {//读取学前教育课程列表
			courseList=CourseDao.loadByCourseType(1);
			gradetype="学前教育";break;
		}
		case 2: {//读取小学课程列表
			courseList=CourseDao.loadByCourseType(2);
			gradetype="小学课程";break;
		}
		case 3: {//读取初中课程列表
			courseList=CourseDao.loadByCourseType(3);
			gradetype="初中课程";break;
		}
		case 4: {//读取高中课程列表
			courseList=CourseDao.loadByCourseType(4);
			gradetype="高中课程";break;
		}
		case 5: {//读取学前教育课程列表
			courseList=CourseDao.loadByCourseType(5);
			gradetype="大学课程";break;
		}
		case 6: {//读取扩展课程列表
			courseList=CourseDao.loadByCourseType(6);
			gradetype="扩展课程";break;
		}
		default: gradetype="你查找的课程类型不存在";break;
		}
		
		/*
		 * 将List数据转化成JSONArray格式返回
		 * @data：2014.11.10
		 * @author：LiMusheng
		 **/
		for(int i=0; i<courseList.size(); i++){
			jsonObject.put("courseid", courseList.get(i).getCourseid());
			jsonObject.put("title", courseList.get(i).getTitle());
			jsonObject.put("image", courseList.get(i).getImage());
			jsonObject.put("articleid", courseList.get(i).getArticleid());
			jsonObject.put("gradetype", courseList.get(i).getGradetype());
			jsonObject.put("coursetype", courseList.get(i).getCoursetype());
			jsonObject.put("time", courseList.get(i).getTime());
			jsonArray.add(jsonObject);
			//打印测试
			System.out.println(courseList.get(i).getTitle());
		}
		JSONObject jsonObject_course = new JSONObject();
		JSONArray jsonArray_course = new JSONArray();
		jsonObject_course.put("course", jsonArray);
		jsonArray_course.add(jsonObject_course);
		
		//request.setAttribute("course", jsonArray_course.toString());
		
		out.write(jsonArray_course.toString());
		out.write(gradetype);
		out.flush();
		out.close();
	}

}
