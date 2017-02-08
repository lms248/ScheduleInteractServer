
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.rcgl.bean.UserBean" %>
<%@ page language="java" import="com.rcgl.dao.UserDao" %>

<%	int page_number = Integer.parseInt(request.getParameter("page_number")); 
	List<UserBean> userList = new ArrayList<UserBean>();
	String tag = request.getParameter("tag");
	if(tag.equals("1")){
		if(request.getParameter("username")==""){
			userList= UserDao.loadAllUser();
		}
		else userList = UserDao.loadByUsernameLike(request.getParameter("username"));
	}
	else userList= UserDao.loadAllUser();
	if(userList.size()/20>=page_number||(userList.size()/20==page_number-1&&userList.size()%20>0)){
%>

<table border="1" align="left" class="table-striped table-bordered table-hover">
	<tr>
 		<th scope="col">
 			<center>&nbsp;ID&nbsp;</center>
 		</th>
 		<th scope="col">
 			<center>用户名</center>
 		</th>
 		<th scope="col">
 			<center>密码</center>
 		</th>
 		<th scope="col">
 			<center>头像</center>
 		</th>
 		<th scope="col">
 			<center>昵称</center>
 		</th>
 		<th scope="col">
 			<center>签名</center>
 		</th>
 		<th scope="col">
 			<center>手机号</center>
 		</th>
 		<th scope="col">
 			<center>邮件</center>
 		</th>
 		<th scope="col">
 			<center>朋友</center>
 		</th>
 		<th scope="col">
 			<center>时间</center>
 		</th>
 		<th scope="col">
 			<center>操作</center>
 		</th>
 	</tr>

 <% for(int i=userList.size()-1-(page_number-1)*20; i>=0 && i>userList.size()-21-(page_number-1)*20; i--){ %>
	<tr>
  	  <td>
  	  	&emsp;<%=userList.get(i).getId() %>&emsp;
  	  </td>
  	  <td>
	  	&emsp;<%=userList.get(i).getUsername() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=userList.get(i).getPassword() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=userList.get(i).getPhoto() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=userList.get(i).getNickname() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=userList.get(i).getSignature() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=userList.get(i).getPhone() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=userList.get(i).getEmail() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=userList.get(i).getFriend() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=userList.get(i).getTime() %>&emsp;
	  </td>
  	  <td>
	  	&emsp;
	  	<a href="#" onclick="readUserDB(<%=userList.get(i).getId()%>)">
	 	 	查看
	  	</a>
	    |
	    <a href="#" onclick="updateUserDB(<%=userList.get(i).getId()%>)">
	 	 	修改
	  	</a>
	    |
	    <a href="javascript:if(confirm('确实要删除该用户吗?'))" style="cursor: pointer;" onclick="deleteUserDB(<%=userList.get(i).getId() %>)">
	 		 删除
	  	</a>
	  	&emsp;
	  </td>	  
  	  <tr>
  <%} %>	  
</table>

<%}
	else out.print("end");	
%>
