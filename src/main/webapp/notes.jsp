<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

 <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">	
     <link rel="stylesheet" type="text/css" href="/css/buttons.css"> 
     <link rel="stylesheet" href="/css/index.css" type="text/css" />
     <link rel="stylesheet" href="/css/style.css" type="text/css" />
     <script type="text/javascript" src="/js/jquery2.2.2.min.js"></script>
     <script type="text/javascript" src="/js/jquery.easing.1.3.js"></script>
     <script type="text/javascript" src="/js/bootstrap.js"></script> 
      <script type="text/javascript" src="/js/notes.js"></script> 
 	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>游记——木木旅行</title>
<style>
	a,a:visited,a:link,a:hover{
 	color:#000000;
 	text-decoration: none;
 	text-align:center;
 	}

	 #notes{
	 margin:50px;
	 }
	 #notesD{
	 margin:70px;
	 }
	  #notesD img{
	   width:800px;
	 }
	  .notesbar0{
	 display:none;
	 }
	 .notesbar{
	 display:block;
	 border-top:1px solid grey;
	 width:92%;
	 height:250px;
	 margin:10px;
	 }
	  .notesbar:hover{
	  	cursor:pointer;
		background-color:#fcfcfc;
	 }
	 
	 .notesbar .noteImg{
	    width:300px;
	 	height:180px;
	 }
	 .notesbar .notediv{
	 position:relative;
	 width:65%;
	 height:200px;
	 bottom:180px;
	 left:300px;
	 font-size:16px;
	 }
	
	 
	 .notesbar .notesTitle{
	 font-weight: bold;
	 font-size:20px;
	 }
	  .notesbar .author{
	 color:black;
	 font-size:14px;
	 position:relative;
	 bottom:200px;
	 left:60%
	 }
	 .notesbar .author span{
	color:#ffaf60;	
	 }
	 #m_title{
	 position:relative;
	 left:70px;
	 bottom:30px;	
	 }
	 #m_title li{
	 font-size:15px;
	 margin-right:20px;
	 float:left;
	 border-left:1px solid grey;
	 }
	 #ampagination-bootstrap{
		margin-left:80px;
	 }
</style>
</head>
<body onload="ajax()">
<div id="header">
	<div id="h_logo" onclick="javascript:window.location.href='/index'"> <img class="img-responsive" src="pics/mumu_g.png" width="150px"> <img class="img-responsive" src="pics/木木旅行_g.png" width="250px"> </div>
	<div id="position">
	<form id="cityForm" action="/hotel" method="post">您的位置：
		<input type="text" placeholder="选择城市" id="place" name="city" value="${User.city}" readonly>
	</form>
		<div id="in_city" style="display: none"></div>
	</div>
	<div id="h_bar">
		<ul class="top_ul">
			<li><a onclick="toHotel()">酒店预订</a></li>
			<li><a href="/notes">周游记</a></li>
		</ul>
	</div>
	<div id="login"> <span><a href="userCenter">${User.uname}个人中心</a></span> <a id="loginBtn"  data-toggle="modal" data-target="#myModal">登录/注册</a> <a id="logOut" href="index/logout" style="display:none;font-style:italic;">退出登录</a> </div>
</div>

<!-- 模态框（Modal） -->
<div style="position:absolute;z-index:10002" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div  class="modal-dialog" style="height:200px;">
		<div  class="modal-content" style="top:200px;"> <img style="position:absolute;left:-1px;" src="pics/login1.png" height="100%" width="50%">
			<div style="display:inline;" class="modal-header">
				<button style="float:right" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 style="left:50%;position:absolute;" class="modal-title" id="myModalLabel">用户登录</h4>
			</div>
			<div style="display:inline;" class="modal-body">
				<form action="index/login" role="form" method="post" style="float:right" accept-charset="utf-8">
					<div class="form-group">
						<label for="name">用户名</label>
						<input type="text" class="form-control" name="name" id="name" placeholder="请输入用户名">
					</div>
					<div class="form-group">
						<label for="password">密码</label>
						<input type="password" class="form-control" name="password" id="password" placeholder="">
					</div>
					<button style="left:0" type="submit" class="button button-glow button-border button-rounded button-primary">登录</button>
				</form>
			</div>
			<div class="modal-footer"> 还没有账号？<a href="register.jsp">点此注册</a> </div>
		</div>
	</div>
</div>

<div id="main">
	<div id="m_title">
		<ul>
		<li><a onclick="window.location.href='/notes';">按时间排序</a></li>
		<li><a onclick="window.location.href='/notes?action=h';">按热度排序</a></li>
		<li><a onclick="ajaxEditor()">发布游记（请先登录）</a></li>
		</ul>
	</div>
	<div id="notesD" style="display:none"></div>
	<div id="notes" style="margin:30px">
		<div class="notesbar0">
			<div class="notesTitle" id="title0"></div>
			<img class="noteImg0" id="img0">
			<div class="notediv" id="notes0"></div>
			<div class="author" id="author0"></div>
		</div>
		<div class="notesbar0">
			<div class="notesTitle" id="title1"></div>
			<img class="noteImg0" id="img1">
			<div class="notediv" id="notes1"></div>
			<div class="author" id="author1"></div>
		</div>
		<div class="notesbar0">
			<div class="notesTitle" id="title2"></div>
			<img class="noteImg0" id="img2">
			<div class="notediv" id="notes2"></div>
			<div class="author" id="author2"></div>
		</div>
		<div class="notesbar0">
			<div class="notesTitle" id="title3"></div>
			<img class="noteImg0" id="img3">
			<div class="notediv" id="notes3"></div>
			<div class="author" id="author3"></div>
		</div>
		<div class="notesbar0">
			<div class="notesTitle" id="title4"></div>
			<img class="noteImg0" id="img4">
			<div class="notediv" id="notes4"></div>
			<div class="author" id="author4"></div>
		</div>
		<div class="notesbar0">
			<div class="notesTitle" id="title5"></div>
			<img class="noteImg0" id="img5">
			<div class="notediv" id="notes5"></div>
			<div class="author" id="author5"></div>
		</div>
		<div class="notesbar0">
			<div class="notesTitle" id="title6"></div>
			<img class="noteImg0" id="img6">
			<div class="notediv" id="notes6"></div>
			<div class="author" id="author6"></div>
		</div>
		<div class="notesbar0">
			<div class="notesTitle" id="title7"></div>
			<img class="noteImg0" id="img7">
			<div class="notediv" id="notes7"></div>
			<div class="author" id="author7"></div>
		</div>
		<div class="notesbar0">
			<div class="notesTitle" id="title8"></div>
			<img class="noteImg0" id="img8">
			<div class="notediv" id="notes8"></div>
			<div class="author" id="author8"></div>
		</div>
		<div class="notesbar0">
			<div class="notesTitle" id="title9"></div>
			<img class="noteImg0" id="img9">
			<div class="notediv" id="notes9"></div>
			<div class="author" id="author9"></div>
		</div>
		<section>
			<div class="ampagination-bootstrap"></div>
		</section>
	</div>
	<div id="float"> 
  	<table id="float_table">
		<tr style="opacity:0.9;filter:alpha(opacity=90);">
			<td class="float">
				<a  href="http://wpa.qq.com/msgrd?v=3&uin=809789268&site=qq&menu=yes" class="mimi" id="service"><img class="img-responsive" src="/pics/qq_b.jpg" width="60px" height="50px">
					联系我们</a>
			</td>
		</tr>
		
		<tr style="opacity:0.9;filter:alpha(opacity=90);">
			<td class="float" id="to_top"style="cursor:pointer;visibility: hidden;" onClick="toTop()">
				<img class="img-responsive" src="/pics/top.jpg" width="50px" height="100px">
					回到顶部 
			</td>
		</tr>
	</table>
	</div>
	
		
</div>
<div id="foot">
	<p> <a href="/index">网站导航</a>| <a href="/index">技术支持</a>| <a href="/index.jsp">联系我们</a>| <a href="">网站声明</a></p>
	<br>
	黑ICP备17005584号&nbsp;Copyright © Chang'an University 坚冰文化, All Rights Reserved </div>
<script  type="text/javascript" src="/js/cityTemplate.js"></script> 
<script type="text/javascript" src="/js/dist/am-pagination.js"></script>

<script type="text/javascript">


function toHotel(){
	$(cityForm).submit();
}


	var pager = window.amPagination('.ampagination-bootstrap',{
		page:${page},
		totals:${pageNum},
		pageSize:1,
		theme:'bootstrap',
	 });
	pager.onChangePage(function(e){
		window.location.href="/notes?page="+e.page+"&action=${action}";
	}); 

		
	$('.notesbar0').click(function(e) {
		id=$(this).attr("id");
		$.ajax( {
	        url: "notes/ajaxNotesD", //这里是静态页的地址
	        type: "POST", 
	        data:{"uri":id},
	        dataType: "html",
	        async : false,
	        success: function(data){
	        		$("#notesD").html(data);
	        		$("#notesD").attr("style","display:block");
	        		$("#notes").attr("style","display:none");
	        	}, error: function() {
	                alert("服务器无响应，请重新尝试");
	            }
			});
	   });
		

</script>
	<script type="text/javascript" src="/js/script.js"></script>
</body>
</html>