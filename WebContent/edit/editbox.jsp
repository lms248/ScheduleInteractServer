	<!--  -->
    <link href="edit/external/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="edit/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="edit/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="edit/css/font-awesome.css" rel="stylesheet">
	<link href="edit/css/index.css" rel="stylesheet">
    <!--  -->
	<script src="edit/external/jquery.hotkeys.js"></script>
	<script src="edit/js/bootstrap.min.js"></script>
    <script src="edit/external/google-code-prettify/prettify.js"></script>
    <script src="edit/js/bootstrap-wysiwyg.js"></script>
    
<div class="container">
  <div class="hero-unit">
  <div class="pull-right">
	<div class="fb-like" data-href="http://facebook.com/mindmupapp" data-send="false" data-layout="button_count" data-width="100" data-show-faces="false"></div>
  </div>
  
  	<!-- <div align="center">
	<h1><font face="华文行楷">趣国学</font> <br/> <font size="7" face="华文隶书"><small>☞ 后台资料编辑框 ☜</small></font></h1>
	</div>
	<hr/> -->
  <form action="../servlet/EditorServlet" method="post">
	
	<!-- <h1><font size="7" face="华文隶书"><small>☞ 后台资料编辑框 ☜</small></font></h1> -->
	<font size="5" face="华文隶书">☞ 资料编辑 ☜</font>
	<select name="gradetype" id="gradetype" style="width:110px;margin-top: 10px;" class="selectpicker">
  		<option value="1" selected="selected">学前教育</option>
    	<option value="2">小学课程</option>
    	<option value="3">初中课程</option>
    	<option value="4">高中课程</option>
    	<option value="5">大学课程</option>
    	<option value="6">扩展课程</option>
  		</select>
  		&nbsp;&nbsp;
  		<select name="contenttype" id="contenttype" style="width:80px;margin-top: 10px;" >
  		<option value="1" selected="selected">文章</option>
    	<option value="2">作业</option>
  		</select>
  		&nbsp;&nbsp;
    	标题<input type="text" name="title" style="height: 30px;margin-top: 10px;"  placeholder="请输入标题…" >&nbsp;&nbsp;&nbsp;
  		
	<hr/>
	
	<div id="alerts"></div>
    <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
      <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" title="Font"><i class="icon-font"></i><b class="caret"></b></a>
          <ul class="dropdown-menu">
          </ul>
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
      
      <!-- <div class="btn-group">
        <a class="btn" title="Insert picture (or just drag & drop)" id="pictureBtn"><i class="icon-picture"></i></a>
        <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
      </div> -->
      <div class="btn-group">
        <a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="icon-undo"></i></a>
        <a class="btn" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
      </div>
      <input type="text" data-edit="inserttext" id="voiceBtn" x-webkit-speech="">
      <div>
      	<!-- <input name="textarea" type="text" id="textarea" value="" style="visibility: hidden;width: 10px;"/>
      	 --><input type="reset" value="  重  置  " style="margin: 2px 5px 0px 20px;" onclick="document.getElementById('editor').innerHTML=''">
      	<input type="submit" value="  提  交   " onclick="document.getElementById('editbox').value=$('#editor').html()">
      </div>
      
    </div>
	<div>
	<div id="editor"></div>
    <div align="left" style="margin-top: 5px;">  
  		图片<input type="file" name="image" style="width: 300px;" title="请选择图片">&nbsp;
  		音频<input type="file" name="audio" style="width: 300px;" title="请选择音频文件"><br>
    	<textarea id="editbox" name="contents" style="visibility:hidden;width: 0;height: 0;"></textarea> 	
    </div>
    </div>	    
  </form>
    
    
    <font size="5" face="华文隶书">☞ 数据管理 ☜</font>
    <select name="gradetype" id="gradetype" style="width:110px;margin-top: 10px;" class="selectpicker">
  		<option value="1" selected="selected">学前教育</option>
    	<option value="2">小学课程</option>
    	<option value="3">初中课程</option>
    	<option value="4">高中课程</option>
    	<option value="5">大学课程</option>
    	<option value="6">扩展课程</option>
  	</select>
  		&nbsp;&nbsp;
  	<select name="contenttype" id="contenttype" style="width:80px;margin-top: 10px;" >
  		<option value="1" selected="selected">文章</option>
    	<option value="2">作业</option>
  	</select>
  	 <input name="s" type="text" id="s" size="25" value="" style="height: 30px;margin-top: 10px;"  placeholder="请输入标题查询…"  />
  	 <input type="submit" style="cursor: pointer;" value="查询数据" name="search" />
    <hr>
<div style="height: 400px;">
	<table border="1" align="left" class="table-striped table-bordered table-hover">
  <tr>
    <th scope="col"><center>列1</center></th>
    <th scope="col"><center>列2</center></th>
    <th scope="col"><center>列3</center></th>
    <th scope="col"><center>列4</center></th>
    <th scope="col"><center>列5</center></th>
    <th scope="col"><center>列6</center></th>
    <th scope="col"><center>列7</center></th>
    <th scope="col"><center>列8</center></th>
    <th scope="col"><center>操作</center></th>
  </tr>
 
  <%for(int j=0; j<5; j++){
	  out.print("<tr>");
  	  for(int i=0; i<8; i++){
  	  out.print("<td>");
  	  out.print(""+i+i+i+i+i+i+i+i+i+i);
  	  out.print("</td>");
  	  }
  	  out.print("<td>");
	  /* out.print("&emsp;"); */
	  out.print("<a href=\"#\">");
	  out.print("查看");
	  out.print("</a>");
	  out.print(" |");
	  out.print("<a href=\"#\">");
	  out.print("修改");
	  out.print("</a>");
	  out.print(" |");
	  out.print("<a href=\"#\">");
	  out.print("删除");
	  out.print("</a>");
	  /* out.print("&emsp;"); */
	  out.print("</td>");
  	  out.print("<tr>");
  }%>

</table>
</div>
    
  </div>
</div>



<script>
$(function(){
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
