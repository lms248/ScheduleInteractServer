<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.rcgl.bean.AnnouncementBean" %>
<%@ page language="java" import="com.rcgl.dao.AnnouncementDao" %>

<%	int page_number = Integer.parseInt(request.getParameter("page_number")); 
	List<AnnouncementBean> list = new ArrayList<AnnouncementBean>();
	list= AnnouncementDao.loadAllAnnouncement();
	if(list.size()/10>=page_number||(list.size()/10==page_number-1&&list.size()%10>0)){
%>

<table border="1" align="left" class="table-striped table-bordered table-hover">
	<tr>
 		<th scope="col">
 			<center>&nbsp;ID&nbsp;</center>
 		</th>
 		<th scope="col">
 			<center>公告简述</center>
 		</th>
 		<th scope="col">
 			<center>公告详细</center>
 		</th>
 		<th scope="col">
 			<center>时间</center>
 		</th>
 		<th scope="col">
 			<center>操作</center>
 		</th>
 	</tr>

 <% for(int i=list.size()-1-(page_number-1)*10; i>=0 && i>list.size()-11-(page_number-1)*10; i--){ %>
	<tr>
  	  <td>
  	  	&emsp;<%=list.get(i).getId() %>&emsp;
  	  </td>
  	  <%
	  	String briefinfo = list.get(i).getBriefinfo();
	  	if(briefinfo.length()>20){
	  		briefinfo = briefinfo.substring(0, 20)+"***";
	  	}
	  %>
  	  <td>
	  	&emsp;<%=briefinfo %>&emsp;
	  </td>
	  <td>
	  	&emsp;****&emsp;
	  </td>
	  <td>
	  	&emsp;<%=list.get(i).getTime() %>&emsp;
	  </td>
  	  <td>
	  	&emsp;
	  	<a href="#" onclick="readAnnouncementDB(<%=list.get(i).getId()%>)">
	 	 	查看
	  	</a>
	  	|
	  	<a style="cursor: pointer;" onclick="updateAnnouncementDB(<%=list.get(i).getId() %>)">修改</a>
	    |
	    <a href="javascript:if(confirm('确实要删除该内容吗?'))" style="cursor: pointer;" onclick="deleteAnnouncementDB(<%=list.get(i).getId() %>)">
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
