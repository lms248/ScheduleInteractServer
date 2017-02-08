package com.rcgl.servlet.client.friend;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.rcgl.bean.MessageBean;
import com.rcgl.bean.UserBean;
import com.rcgl.dao.MessageDao;
import com.rcgl.dao.UserDao;

/**
 * 添加好友
 * */
@WebServlet(urlPatterns = { "/RCGLServer/AddFriend" })
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddFriend() {
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
		
		System.out.println("*************AddFriend*****************");
		
		
		/*获取客户端发送过来的数据*/
		String messageid = request.getParameter("messageid");
		String userid = request.getParameter("userid");
		String friendid = request.getParameter("friendid");
		System.out.println("userid="+userid);
		System.out.println("friendid="+friendid);
		Date now = new Date(); //new Date()为获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");///设置日期格式
		String time = df.format(now);
		
		/*读取数据库数据*/
		if(!userid.equals("0")&!friendid.equals("0")){
			UserBean ubean = new UserBean();
			ubean = UserDao.loadById(Integer.parseInt(userid));
			int uid = Integer.parseInt(userid);
			int fid = Integer.parseInt(friendid);
			String friend = null;
			System.out.println("ubean.getFriend()="+ubean.getFriend());
			String f1 = ubean.getFriend();
			if(f1==null){
				f1="";
			}
			String friends[] = f1.split(",");
			
			
			if(ubean.getId()==fid){
				System.out.println("不能添加自己为好友");
				return;
			}
			else {
				if(friends!=null&&!friends[0].equals("")){
					for(int i=0;i<friends.length;i++){
						if(friends[i].equals(friendid)){
							System.out.println("这位早就是你的好友了");
							return;
						}
					}
				}
				ubean = UserDao.loadById(uid);
				String f2 = ubean.getFriend();
				if(f2==null){
					f2="";
				}
				if(ubean.getFriend()!=null&&!f2.equals("")){
					friend = ubean.getFriend()+","+fid;
				}
				else{
					friend = fid+"";
				}
				
				ubean.setId(uid);
				ubean.setFriend(friend);
				/*将bean数据存入数据库*/
				UserDao.update(ubean);
				
				UserBean fbean = new UserBean();
				fbean = UserDao.loadById(fid);
				String f3 = fbean.getFriend();
				if(f3==null){
					f3="";
				}
				if(fbean.getFriend()!=null&&!f3.equals("")){
					friend = fbean.getFriend()+","+uid;
				}
				else{
					friend = uid+"";
				}
				
				fbean.setId(fid);
				fbean.setFriend(friend);
				/*将bean数据存入数据库*/
				UserDao.update(fbean);
				
				MessageBean mbean = new MessageBean();
				System.out.println("messageid="+messageid);
				mbean = MessageDao.loadById(Integer.parseInt(messageid));
				mbean.setId(Integer.parseInt(messageid));
				mbean.setStatus(1);
				/*将bean数据存入数据库*/
				MessageDao.update(mbean);
				
				//获取用户好友列表到手机客户端
				out.write(getFriend(Integer.parseInt(userid)));
				System.out.println(getFriend(Integer.parseInt(userid)));
				
				//out.print("1");
			}
				
			
			
		}
		else {
			out.print("-1");
		}
		out.flush();
		out.close();
	}
	
	/** 获取用户好友 */
	private String getFriend(int userid){
		UserBean ubean = new UserBean();
		ubean = UserDao.loadById(userid);
		
		List<UserBean> userList = new ArrayList<UserBean>();
		UserBean fbean = new UserBean();
		
		String f1 = ubean.getFriend();
		if(f1==null){
			f1="";
		}
		String friend[] = f1.split(",");
		for(int i=0;i<friend.length;i++){
			if(!friend[i].equals("")){
				fbean = UserDao.loadById(Integer.parseInt(friend[i]));
				userList.add(fbean);
			}
		}
		
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		JSONArray jsonArray= new JSONArray();
		
		for(int i=userList.size()-1;i>=0;i--){
			jsonObject1.put("id", userList.get(i).getId());
			jsonObject1.put("username", userList.get(i).getUsername());
			//jsonObject1.put("password", userList.get(i).getPassword());
			jsonObject1.put("time", userList.get(i).getTime());
			jsonArray.add(jsonObject1);
		}
		jsonObject2.put("user", jsonArray);
		return jsonObject2.toString();
	}
}
