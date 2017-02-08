/**
 * 
 */
/*加载数据到编辑框*/
//locationShow  locationInfo
/*function readLocationDB(id){
	var locationShow=$("#locationShow");
	$(document).ready(function(){
		 $.post("/rcgl/servlet/GetLocationData",{locationid:id},
				 function(data){
			 var locationInfo=locationShow.find("#locationInfo");
			 locationInfo.empty();
			 locationInfo.text(data);
			 locationShow.modal("show");
		 });
	});	
}*/

/*function deleteLocationDB(id){
	if(confirm("删除是不可恢复的，你确认要删除吗？")){
		$(document).ready(function(){
			 $.post("/rcgl/servlet/DeleteLocationData",{locationid:id},
					 function(data){
				 		alert(data);
				 		getLocationDB(document.getElementById("page_number").innerHTML,0,0);
			 },"text");
			 
		});
	  }
}*/

/*读取数据在管理界面显示出来*/
function getLocationDB(page_number,tag){
	var userid = document.getElementById("location_search").value;
	var params = {page_number:page_number,tag:tag,userid:userid};
	$("#locationdb").load("/rcgl/view/client/db/locationdb.jsp",params);
	
}

function firstPage(){//首页
	getLocationDB(1,0);
	document.getElementById("page_number").innerHTML = "1";
}
function pageUp(){//上一页
	var page_number = document.getElementById("page_number").innerHTML;
	if(parseInt(page_number)>1){
		document.getElementById("page_number").innerHTML = parseInt(page_number)-1;
		getLocationDB(parseInt(page_number)-1,0);
	}	
}
function pageDown(){//下一页
	var page_number = document.getElementById("page_number").innerHTML;
	$(document).ready(function(){
		 $.post("/rcgl/servlet/GetDbCount",{dbtype:"locationdb"},
				 function(data){
			 		if(parseInt(page_number)+1>data){
			 			//alert("已经到达尾页！");
			 			$.myalert("已经到达尾页！");
			 		}
			 		else{
			 			getLocationDB(parseInt(page_number)+1,0);
			 			document.getElementById("page_number").innerHTML = parseInt(page_number)+1;
			 		}
		 },"text");
		 
		});
}
function lastPage(){//尾页
	$(document).ready(function(){
		 $.post("/rcgl/servlet/GetDbCount",{dbtype:"locationdb"},
				 function(data){
			 		getLocationDB(data,0);
			 		document.getElementById("page_number").innerHTML = data;
		 },"text");
		 
		});
}
function targetPage(){//指定目标页面
	var target_page = document.getElementById("target_page").value;
	var page_count = document.getElementById("page_count").innerHTML;
	
	if(parseInt(target_page)>=1&&parseInt(target_page)<=parseInt(page_count)){
		document.getElementById("page_number").innerHTML = target_page;
		getLocationDB(parseInt(target_page),0);
	}
	else{
		$.myalert("笨蛋！你输入的页面区间不对呀~");
	}
}