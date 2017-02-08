
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.rcgl.bean.ActivitiesBean" %>
<%@ page language="java" import="com.rcgl.dao.ActivitiesDao" %>

<%	int page_number = Integer.parseInt(request.getParameter("page_number")); 
	List<ActivitiesBean> activitiesList = new ArrayList<ActivitiesBean>();
	String tag = request.getParameter("tag");
	if(tag.equals("1")){
		if(request.getParameter("content")==""){
			activitiesList= ActivitiesDao.loadAllActivities();
		}
		else activitiesList = ActivitiesDao.loadByContent(request.getParameter("content"));
	}
	else activitiesList= ActivitiesDao.loadAllActivities();
	if(activitiesList.size()/20>=page_number||(activitiesList.size()/20==page_number-1&&activitiesList.size()%20>0)){
%>

<table border="1" align="left" class="table-striped table-bordered table-hover">
	<tr>
 		<th scope="col">
 			<center>&nbsp;ID&nbsp;</center>
 		</th>
 		<th scope="col">
 			<center>用户ID</center>
 		</th>
 		<th scope="col">
 			<center>参与者</center>
 		</th>
 		<th scope="col">
 			<center>内容</center>
 		</th>
 		<th scope="col">
 			<center>图片</center>
 		</th>
 		<th scope="col">
 			<center>活动时间</center>
 		</th>
 		<th scope="col">
 			<center>人数限制</center>
 		</th>
 		<th scope="col">
 			<center>已报人数</center>
 		</th>
 		<th scope="col">
 			<center>分类</center>
 		</th>
 		<th scope="col">
 			<center>收藏</center>
 		</th>
 		<th scope="col">
 			<center>标记</center>
 		</th>
 		<th scope="col">
 			<center>时间</center>
 		</th>
 		<th scope="col">
 			<center>操作</center>
 		</th>
 	</tr>

 <% for(int i=activitiesList.size()-1-(page_number-1)*20; i>=0 && i>activitiesList.size()-21-(page_number-1)*20; i--){ %>
	<tr>
  	  <td>
  	  	&emsp;<%=activitiesList.get(i).getId() %>&emsp;
  	  </td>
  	  <td>
	  	&emsp;<%=activitiesList.get(i).getUserid() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getParticipant() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getContent() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getImage() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getDotime() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getMax_number_people() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getAlready_number_people() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getSort() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getCollector() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getMark() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=activitiesList.get(i).getTime() %>&emsp;
	  </td>
  	  <td>
	  	&emsp;
	  	<a href="#" onclick="readActivitiesDB(<%=activitiesList.get(i).getId()%>)">
	 	 	查看
	  	</a>
	    |
	    <a href="#" onclick="updateActivitiesDB(<%=activitiesList.get(i).getId()%>)">
	 	 	修改
	  	</a>
	    |
	    <a href="javascript:if(confirm('确实要删除该活动吗?'))" style="cursor: pointer;" onclick="deleteActivitiesDB(<%=activitiesList.get(i).getId() %>)">
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
