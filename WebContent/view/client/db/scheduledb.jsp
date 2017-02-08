
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.rcgl.bean.ScheduleBean" %>
<%@ page language="java" import="com.rcgl.dao.ScheduleDao" %>

<%
	int page_number = Integer.parseInt(request.getParameter("page_number")); 
	List<ScheduleBean> scheduleList = new ArrayList<ScheduleBean>();
	String tag = request.getParameter("tag");
	if(tag.equals("1")){
		if(request.getParameter("content")==""){
	scheduleList= ScheduleDao.loadAllSchedule();
		}
		else scheduleList = ScheduleDao.loadByContent(request.getParameter("content"));
	}
	else scheduleList= ScheduleDao.loadAllSchedule();
	if(scheduleList.size()/20>=page_number||(scheduleList.size()/20==page_number-1&&scheduleList.size()%20>0)){
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
 			<center>日程内容</center>
 		</th>
 		<th scope="col">
 			<center>日程时间</center>
 		</th>
 		<th scope="col">
 			<center>是否公开</center>
 		</th>
 		<th scope="col">
 			<center>是否开启通知提醒</center>
 		</th>
 		<th scope="col">
 			<center>状态</center>
 		</th>
 		<th scope="col">
 			<center>时间</center>
 		</th>
 		<th scope="col">
 			<center>操作</center>
 		</th>
 	</tr>

 <% for(int i=scheduleList.size()-1-(page_number-1)*20; i>=0 && i>scheduleList.size()-21-(page_number-1)*20; i--){ %>
	<tr>
  	  <td>
  	  	&emsp;<%=scheduleList.get(i).getId() %>&emsp;
  	  </td>
  	  <td>
	  	&emsp;<%=scheduleList.get(i).getUserid()%>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=scheduleList.get(i).getContent() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=scheduleList.get(i).getDotime() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=scheduleList.get(i).getOpen() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=scheduleList.get(i).getAlarm() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=scheduleList.get(i).getStatus() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=scheduleList.get(i).getTime() %>&emsp;
	  </td>
  	  <td>
	  	&emsp;
	  	<a href="#" onclick="readScheduleDB(<%=scheduleList.get(i).getId()%>)">
	 	 	查看
	  	</a>
	    |
	    <a href="#" onclick="updateScheduleDB(<%=scheduleList.get(i).getId()%>)">
	 	 	修改
	  	</a>
	    |
	    <a href="javascript:if(confirm('确实要删除该日程吗?'))" style="cursor: pointer;" onclick="deleteScheduleDB(<%=scheduleList.get(i).getId() %>)">
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
