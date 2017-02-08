
    <link href="/rcgl/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
	<link href="/rcgl/styles/page.css" rel="stylesheet">
	<script src="/rcgl/js/client/schedule.js"></script>
	
<div class="hero-unit" style="width: 2000px;" >
	<div class="page-header">
  		<h6 class="title-font">日程 <small style="title-font">管理</small></h6>
	</div>
  	<table>
  	<tr>
  	<td>
	<div id="search">
		<input id="schedule_search" type="text" size="25" value="" style="height: 30px;margin-top: 10px;"  placeholder="请输入日程内容查询…"  />
  	 	<input type="submit" class="btn" onclick="getScheduleDB(1,1)" style="cursor: pointer;" value="查询日程" name="search" />
	</div>
	</td>
  	<td>
	<div class="black"> 
     	<!-- <span class="disabled">上一页 </span> -->
     	<a onclick="firstPage()" style="cursor: pointer;">首页</a>
  	 	<a onclick="pageUp()" style="cursor: pointer;">上一页</a>
    	<span id="page_number" class="current">1</span>
    	<a onclick="pageDown()" style="cursor: pointer;">下一页</a>
    	<a onclick="lastPage()" style="cursor: pointer;">尾页</a>
    	第<input id="target_page" type="text" style="margin-top: 5px;width: 30px;height: 25px;">页
    	<input type="button" style="width: 40px;height: 25px;" value="GO" onclick="targetPage()"> 
    	&nbsp;&nbsp;〖共&nbsp;<span id="page_count">0</span>&nbsp;页〗&nbsp;&nbsp;
    	<!-- <input type="button" value="刷新数据" onclick="getScheduleDB(1,0)" > -->
	</div>
	</td>
	</tr>
	</table>	
	<div id="scheduledb" style="height: 650px;"></div>
	
</div>


<div id="scheduleShow" class="modal fade" style="background-color: inherit;border: 0px solid rgba(0, 0, 0, 0.3); box-shadow: 0px 0px 0px 0px;width: 600px;">
  <div class="modal-dialog" id="scheduleShow">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">日程信息</h4>
      </div>
      <div class="modal-body">
        <table class="table table-bordered table-hover">
		  <tr>
		    <td><span id="scheduleInfo"></span></td>
		  </tr>
		</table>
      </div>
    </div>
  </div>
</div>


<script>
getScheduleDB(1,0);
//获取共有几页
$.post("/rcgl/servlet/GetDbCount",{dbtype:"scheduledb"},
	function(data){
		document.getElementById("page_count").innerHTML = data;
},"text");


</script>
