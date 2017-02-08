package common.dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.bean.ArticleBean;
import common.dao.ArticleDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetArticleData
 */
@WebServlet("/GetArticleData")
public class GetArticleData extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public GetArticleData() {
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
		
		String articleid = request.getParameter("articleid").toString();
		int ai = Integer.parseInt(articleid);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		ArticleBean abean = ArticleDao.loadById(ai);//读取数据库数据
		jsonObject.put("articleid", abean.getArticleid());
		jsonObject.put("title", abean.getTitle());
		jsonObject.put("Contents", abean.getContents());
		jsonObject.put("image", abean.getImage());
		jsonObject.put("audio", abean.getAudio());
		jsonObject.put("taskid", abean.getTaskid());
		jsonObject.put("mark", abean.getMark());
		jsonArray.add(jsonObject);
		JSONObject jsonObject_artilce = new JSONObject();
		jsonObject_artilce.put("article", jsonArray);
		
		out.write(jsonObject_artilce.toString());
		/*out.write(abean.getContents());
		out.write(jsonObject.getString("Contents"));*/
		out.flush();
		out.close();
	}

}
