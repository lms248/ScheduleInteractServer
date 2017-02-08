
    <link href="/rcgl/edit/external/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="/rcgl/edit/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="/rcgl/edit/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="/rcgl/edit/css/font-awesome.css" rel="stylesheet">
	<link href="/rcgl/edit/css/index.css" rel="stylesheet">
	<link href="/rcgl/styles/page.css" rel="stylesheet">
    <!--  -->
	<script src="/rcgl/edit/external/jquery.hotkeys.js"></script>
	<script src="/rcgl/edit/js/bootstrap.min.js"></script>
    <script src="/rcgl/edit/external/google-code-prettify/prettify.js"></script>
    <script src="/rcgl/edit/js/bootstrap-wysiwyg.js"></script>
    <script src="/rcgl/edit/js/jquery.form.js"></script>
   	
   	<!-- ajax配置 -->
   	<script src="/rcgl/js/client/announcement.js"></script>
   	
<div class="container">
  <div class="hero-unit" style="width: 1130px;">
    <div class="pull-right">
	  <div class="fb-like" data-href="http://facebook.com/mindmupapp" data-send="false" data-layout="button_count" data-width="100" data-show-faces="false"></div>
    </div>
	<font size="5" face="华文隶书">☞ 公告编辑 ☜</font>
	
	<hr/>
	
	<div id="">
		<textarea id="minEditor" style="width: 600px;height: 80px;" placeholder="公告简述……"></textarea>
	</div>
	
	<div id="alerts"></div>
    <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
      <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i class="icon-font"></i><b class="caret"></b></a>
        <ul class="dropdown-menu"></ul>
      </div>
      <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="icon-text-height"></i>&nbsp;<b class="caret"></b></a>
          <ul class="dropdown-menu">
          <li><a data-edit="fontSize 5"><font size="5">一号</font></a></li>
          <li><a data-edit="fontSize 5"><font size="4">二号</font></a></li>
          <li><a data-edit="fontSize 3"><font size="3">三号</font></a></li>
          <li><a data-edit="fontSize 3"><font size="2">四号</font></a></li>
          <li><a data-edit="fontSize 1"><font size="1">五号</font></a></li>
          </ul>
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="icon-bold"></i></a>
        <a class="btn" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a>
        <a class="btn" data-edit="strikethrough" title="Strikethrough"><i class="icon-strikethrough"></i></a>
        <a class="btn" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="icon-underline"></i></a>
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="insertunorderedlist" title="Bullet list"><i class="icon-list-ul"></i></a>
        <a class="btn" data-edit="insertorderedlist" title="Number list"><i class="icon-list-ol"></i></a>
        <a class="btn" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="icon-indent-left"></i></a>
        <a class="btn" data-edit="indent" title="Indent (Tab)"><i class="icon-indent-right"></i></a>
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>
        <a class="btn" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="icon-align-center"></i></a>
        <a class="btn" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="icon-align-right"></i></a>
        <a class="btn" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="icon-align-justify"></i></a>
      </div>
      <div class="btn-group">
		  <a class="btn dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="icon-link"></i></a>
		    <div class="dropdown-menu input-append">
			    <input class="span2" placeholder="URL" type="text" data-edit="createLink"/>
			    <button class="btn" type="button">Add</button>
        </div>
        <a class="btn" data-edit="unlink" title="Remove Hyperlink"><i class="icon-cut"></i></a>

      </div>
      
      <div class="btn-group">
        <a class="btn" title="Insert picture (or just drag & drop)" id="pictureBtn"><i class="icon-picture"></i></a>
        <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
      </div>
      <div class="btn-group">
        <a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a>
        <a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
      </div>
      <input type="text" data-edit="inserttext" id="voiceBtn" x-webkit-speech="">
      
      <div id="add_db">
      	<input id="reset" class="btn" type="reset" value="  重  置  " style="margin: 2px 5px 0px 20px;"
      	 	onclick="
      	 		document.getElementById('minEditor').value='';
      			document.getElementById('editor').innerHTML='公告详细内容……';
      	 		document.getElementById('word_file').value='';
      		document.getElementById('submit').value='  发  布  '" 
      	/>
      	<input id="submit" class="btn" type="submit" value="  发  布  " onclick="addAnnouncementDB(0)">
      </div>
    </div>
	<div>
	
	<div id="editor">公告详细内容……</div>
	
	<div id="word_file_div">
	    <form id="LoadWordForm" enctype="multipart/form-data" method="post" action="/rcgl/servlet/LoadWord">
			<input type="file" id="word_file" name="word_file" style="width: 300px;height: 30px;margin-top: 5px">
			<input type="button" id="loadWord" class="btn" name="loadWord" value="  导入Word文档  ">（只能导入word文档，请使用兼容格式.doc）<!--  onclick="loadWordToEdit()" -->
		</form>
	</div>
    </div>	    
    
    <hr>
    <font size="5" face="华文隶书">☞ 公告管理 ☜</font>
    <table  border="0">
  	<tr>
  	<!-- <td>
    	<input id="title_search" type="text" size="25" value="" style="height: 30px;margin-top: 10px;"  placeholder="……"  />
  	 	<input type="submit" onclick="" style="cursor: pointer;" value="查询数据" name="search" />
  	</td> -->
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
			</div>
  		</td>
  	</tr>
	</table>
  	 
	<div id="db" style="height: 400px;"></div>
    
  </div>
</div>

<script>
getAnnouncementDB();
//获取共有几页
$.post("/rcgl/servlet/GetDbCount",{dbtype:"announcementdb"},
	function(data){
		document.getElementById("page_count").innerHTML = data;
},"text");


/*读取数据在管理界面显示出来*/
function getAnnouncementDB(page_number){
	var page_number = document.getElementById("page_number").innerHTML;
	var params = {page_number:page_number};
	$("#db").load("/rcgl/view/client/db/announcementdb.jsp",params);
}


</script>




<script>
$(function(){
	
	/* 异步上传加载的word文档 */
	var options={
            url:"/rcgl/servlet/LoadWord",
            type:"post",
            success:function(data){
                document.getElementById("editor").innerHTML=data;
            }
        };
        $("#LoadWordForm").submit(function() {
            $(this).ajaxSubmit(options);
                return false;
        });
              
    $("#loadWord").click(function(){
        $("#LoadWordForm").submit();
    });         
    
	
    function initToolbarBootstrapBindings() {
      var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
            'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
            'Times New Roman', 'Verdana'],
            fontTarget = $('[title=Font]').siblings('.dropdown-menu');
      $.each(fonts, function (idx, fontName) {
          fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
      });
      $('a[title]').tooltip({container:'body'});
    	$('.dropdown-menu input').click(function() {return false;})
		    .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
        .keydown('esc', function () {this.value='';$(this).change();});

      $('[data-role=magic-overlay]').each(function () { 
        var overlay = $(this), target = $(overlay.data('target')); 
        overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
      });
      if ("onwebkitspeechchange"  in document.createElement("input")) {
        var editorOffset = $('#editor').offset();
        $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
      } else {
        $('#voiceBtn').hide();
      }
	};
	function showErrorAlert (reason, detail) {
		var msg='';
		if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
		else {
			console.log("error uploading file", reason, detail);
		}
		$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
		 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
	};
    initToolbarBootstrapBindings();  
	$('#editor').wysiwyg({ fileUploadError: showErrorAlert} );
    window.prettyPrint && prettyPrint();
  });
</script>
