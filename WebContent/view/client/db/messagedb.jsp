
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.rcgl.bean.MessageBean" %>
<%@ page language="java" import="com.rcgl.dao.MessageDao" %>

<%	int page_number = Integer.parseInt(request.getParameter("page_number")); 
	List<MessageBean> messageList = new ArrayList<MessageBean>();
	String tag = request.getParameter("tag");
	if(tag.equals("1")){
		if(request.getParameter("receiverid")==""){
			 messageList= MessageDao.loadAllMessage();
		}
		else messageList = MessageDao.loadByReceiver(Integer.parseInt(request.getParameter("receiverid")));
	}
	else messageList= MessageDao.loadAllMessage();
	if(messageList.size()/20>=page_number||(messageList.size()/20==page_number-1&&messageList.size()%20>0)){
%>

<table border="1" align="left" class="table-striped table-bordered table-hover">
	<tr>
 		<th scope="col">
 			<center>&nbsp;ID&nbsp;</center>
 		</th>
 		<th scope="col">
 			<center>发送者ID</center>
 		</th>
 		<th scope="col">
 			<center>接收者ID</center>
 		</th>
 		<th scope="col">
 			<center>活动ID</center>
 		</th>
 		<th scope="col">
 			<center>类型</center>
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

 <% for(int i=messageList.size()-1-(page_number-1)*20; i>=0 && i>messageList.size()-21-(page_number-1)*20; i--){ %>
	<tr>
  	  <td>
  	  	&emsp;<%=messageList.get(i).getId() %>&emsp;
  	  </td>
  	  <td>
	  	&emsp;<%=messageList.get(i).getSender() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=messageList.get(i).getReceiver() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=messageList.get(i).getActivitiesid() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=messageList.get(i).getType() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=messageList.get(i).getStatus() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=messageList.get(i).getTime() %>&emsp;
	  </td>
  	  <td>
	  	&emsp;
	  	<a href="#" onclick="readMessageDB(<%=messageList.get(i).getId()%>)">
	 	 	查看
	  	</a>
	    |
	    <a href="#" onclick="updateMessageDB(<%=messageList.get(i).getId()%>)">
	 	 	修改
	  	</a>
	    |
	    <a href="javascript:if(confirm('确实要删除该消息吗?'))" style="cursor: pointer;" onclick="deleteMessageDB(<%=messageList.get(i).getId() %>)">
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
