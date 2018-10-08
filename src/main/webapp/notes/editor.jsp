<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/buttons.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>游记编辑</title>
 <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
#all{
width:80%;
padding-left:30px;
padding-top:20px;
}
#subBtn{
margin-top:8px;
float:right;
}
</style>
</head>
<body>
<div id="all" >
<form action="/notes/upload" id="note" method="POST">
	<textarea hidden="hidden" id="text1" style="width:100%; height:200px;" name="text"></textarea>
	<div class="input-group">
            <span class="input-group-addon">标题</span>
            <input type="text" class="form-control" name="title">
    </div>
</form>

   <div id="div1">
    <p>${text}</p>
</div>
 <a id="subBtn" onClick="$('form').submit()" class="button button-raised button-primary">提交</a>
</div>

    <script src="/js/jquery2.2.2.min.js"></script>
    <script type="text/javascript" src="/notes/wangEditor.js"></script>
    <script type="text/javascript">

        var E = window.wangEditor
        var editor = new E('#div1')
        var $text1 = $('#text1')
        
        editor.customConfig.uploadImgServer = '/notes/picUpload'	
        editor.customConfig.uploadFileName = 'uploadPic'
        //服务器
        editor.customConfig.onchange = function (html) {
            // 监控变化，同步更新到 textarea
            $text1.val(html)
        }
        // 初始化 textarea 的值
   		 // 将图片大小限制为 3M
   		 editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024;
   		 // 限制一次最多上传 10 张图片
   		 editor.customConfig.uploadImgMaxLength = 10;
   		 editor.create();
   		 </script>
</body>
</html>