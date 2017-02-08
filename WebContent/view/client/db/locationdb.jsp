
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.rcgl.bean.LocationBean" %>
<%@ page language="java" import="com.rcgl.dao.LocationDao" %>

<%	int page_number = Integer.parseInt(request.getParameter("page_number")); 
	List<LocationBean> locationList = new ArrayList<LocationBean>();
	String tag = request.getParameter("tag");
	if(tag.equals("1")){
		LocationBean ltbean = new LocationBean();
		if(request.getParameter("userid")==""){
			locationList= LocationDao.loadAllLocation();
		}
		else {
			ltbean = LocationDao.loadByUserId(Integer.parseInt(request.getParameter("userid")));
			locationList.add(ltbean);
		}
	}
	else locationList= LocationDao.loadAllLocation();
	if(locationList.size()/20>=page_number||(locationList.size()/20==page_number-1&&locationList.size()%20>0)){
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
 			<center>经度</center>
 		</th>
 		<th scope="col">
 			<center>纬度</center>
 		</th>
 		<th scope="col">
 			<center>时间</center>
 		</th>
 		<th scope="col">
 			<center>操作</center>
 		</th>
 	</tr>

 <% for(int i=locationList.size()-1-(page_number-1)*20; i>=0 && i>locationList.size()-21-(page_number-1)*20; i--){ %>
	<tr>
  	  <td>
  	  	&emsp;<%=locationList.get(i).getId() %>&emsp;
  	  </td>
  	  <td>
	  	&emsp;<%=locationList.get(i).getUserid() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=locationList.get(i).getLongitude() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=locationList.get(i).getLatitude() %>&emsp;
	  </td>
	  <td>
	  	&emsp;<%=locationList.get(i).getTime() %>&emsp;
	  </td>
  	  <td>
	  	&emsp;
	  	<a href="#" onclick="readLocationDB(<%=locationList.get(i).getId()%>)">
	 	 	查看
	  	</a>
	    |
	    <a href="#" onclick="updateLocationDB(<%=locationList.get(i).getId()%>)">
	 	 	修改
	  	</a>
	    |
	    <a href="javascript:if(confirm('确实要删除该定位数据吗?'))" style="cursor: pointer;" onclick="deleteLocationDB(<%=locationList.get(i).getId() %>)">
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
