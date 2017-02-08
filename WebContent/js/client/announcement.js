/**
 * 公告JS
 */
/*提交按钮显示的值*/
var tianjia = "  发  布  ";
var chakan = "  查  看  ";
var xiugai = "  修  改  ";
var shanchu = "  删  除  ";

var anid = 0;
/*添加或修改公告数据*/
function addAnnouncementDB(id){
	var briefInfo = document.getElementById('minEditor').value;
	var contents = $('#editor').html();
	
	var params = {briefInfo:briefInfo,contents:contents};
	//添加数据
	if(document.getElementById("submit").value==tianjia){
		if(briefInfo==""){
			alert("请输入公告简述！！！");
		}
		else if(contents==""){
			alert("请输入公告详细内容！！！");
		}
		else {
			if(confirm("你确认要添加吗？")){
				$.post("/rcgl/servlet/AddAnnouncementData",params,function(data){
					alert("添加数据成功！！！");
					getAnnouncementDB(1);
	 				document.getElementById("page_number").innerHTML = "1";
				},"text")
			}
	 	}
	}
	//修改数据
	else if(document.getElementById("submit").value==xiugai){
		var Params2 = {briefInfo:briefInfo,contents:contents,id:anid};
		
		if(briefInfo==""){
			alert("请输入公告简述！！！");
		}
		else if(contents==""){
			alert("请输入公告详细内容！！！");
		}
		else {
			if(confirm("你确认要修改吗？")){
				$.post("/rcgl/servlet/UpdateAnnouncementData",Params2,function(data){
						alert("修改数据成功！！！");
						getAnnouncementDB(document.getElementById("page_number").innerHTML);
				});
			}
	 	}
	}		
}



/*读取公告数据在管理界面显示出来*/
function loadDB(id){
	$(document).ready(function(){
		 $.post("/rcgl/servlet/GetAnnouncementDataToEdit",{id:id},
				 function(data){
			 		document.getElementById("minEditor").value=data.briefinfo;
			 		document.getElementById("editor").innerHTML=data.contents;
		 },"json");
		
		});	
}

function readAnnouncementDB(id){
	loadDB(id);
	document.getElementById("submit").value=chakan;
}

function updateAnnouncementDB(id){
	loadDB(id);
	anid = id;
	document.getElementById("submit").value=xiugai;
}

function deleteAnnouncementDB(id){
	if(confirm("删除是不可恢复的，你确认要删除吗？")){
		$(document).ready(function(){
			 $.post("/rcgl/servlet/DeleteAnnouncementData",{id:id},
					 function(data){
				 		alert(data);
				 		getAnnouncementDB(document.getElementById("page_number").innerHTML);
			 },"text");
			 
		});
	  }
}

function firstPage(){//首页
	getAnnouncementDB(1);
	document.getElementById("page_number").innerHTML = "1";
}
function pageUp(){//上一页
	var page_number = document.getElementById("page_number").innerHTML;
	if(parseInt(page_number)>1){
		document.getElementById("page_number").innerHTML = parseInt(page_number)-1;
		getAnnouncementDB(parseInt(page_number)-1);
	}	
}
function pageDown(){//下一页
	var page_number = document.getElementById("page_number").innerHTML;
	$(document).ready(function(){
		 $.post("/rcgl/servlet/GetDbCount",{dbtype:"announcementdb"},
				 function(data){
			 		if(parseInt(page_number)+1>data){
			 			//alert("已经到达尾页！");
			 			$.myalert("已经到达尾页！");
			 		}
			 		else{
			 			getAnnouncementDB(parseInt(page_number)+1);
			 			document.getElementById("page_number").innerHTML = parseInt(page_number)+1;
			 		}
		 },"text");
		 
		});
}
function lastPage(){//尾页
	$(document).ready(function(){
		 $.post("/rcgl/servlet/GetDbCount",{dbtype:"announcementdb"},
				 function(data){
			 		getAnnouncementDB(data);
			 		document.getElementById("page_number").innerHTML = data;
		 },"text");
		 
		});
}
function targetPage(){//指定目标页面
	var target_page = document.getElementById("target_page").value;
	var page_count = document.getElementById("page_count").innerHTML;
	if(parseInt(target_page)>=1&&parseInt(target_page)<=parseInt(page_count)){
		getAnnouncementDB(parseInt(target_page));
		document.getElementById("page_number").innerHTML = target_page;
	}
	else{
		$.myalert("笨蛋！你输入的页面区间不对呀~");
	}
}

