<%@ page language="java" import="java.util.*" import="java.util.*,java.io.*,java.net.*,java.lang.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
 <head>
 <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="css/buttons.css">
 <link rel="stylesheet" href="css/index.css" type="text/css" />
 <link rel="stylesheet" href="css/style.css" type="text/css" />
 <script type="text/javascript" src="js/jquery2.2.2.min.js"></script>
 <script type="text/javascript" src="js/bootstrap.js"></script>
 <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
 <script> 
 $(document).ready(function(){ 
 $('#myCarousel').carousel({interval:3000});//每隔3秒自动轮播 
 }); 
 </script>
 <title>木木旅行社</title>
 </head>
 <body onload="a()">
<div id="header">
	<div onclick="javascript:window.location.href='/index'" id="h_logo" > <img class="img-responsive" src="pics/mumu_g.png" width="150px">
	<img  class="img-responsive" src="pics/木木旅行_g.png" width="250px">
	</div>
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
<div style="position:absolute;" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div  class="modal-dialog" style="height:200px;">
		<div  class="modal-content" style="top:200px;"> <img style="position:absolute;left:-1px;" src="/pics/login1.png" height="100%" width="50%">
			<div style="display:inline;" class="modal-header">
				<button style="float:right" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 style="left:50%;position:absolute;" class="modal-title" id="myModalLabel">用户登录</h4>
			</div>
			<div style="display:inline;" class="modal-body">
				<form action="/index/login" role="form" method="post" style="float:right" accept-charset="utf-8">
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

<!--  
<div id="float"> 
  	<table id="float_table">
		<tr style="opacity:0.9;filter:alpha(opacity=90);">
			<td class="float">
					联系我们</a>
			</td>
		</tr>
		
		<tr style="opacity:0.9;filter:alpha(opacity=90);">
			<td class="float" id="to_top"style="cursor:pointer;visibility: hidden;" onClick="toTop()">
				<img class="img-responsive" src="pics/top.jpg" width="50px" height="40px"><br>
					回到顶部 
			</td>
		</tr>
	</table>
</div>
-->
<div id="myCarousel" class="carousel slide"> 
	<!-- 轮播（Carousel）指标 -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
		<li data-target="#myCarousel" data-slide-to="3"></li>
		<li data-target="#myCarousel" data-slide-to="4"></li>
	</ol>
	<!-- 轮播（Carousel）项目 -->
	<div class="carousel-inner">
		<div class="item active"> <img src="/pics/Carousel/material1.jpg" alt="First slide"> </div>
		<div class="item"> <img src="/pics/Carousel/material2.jpg" alt="Second slide"> </div>
		<div class="item"> <img src="/pics/Carousel/material3.jpg" alt="Third slide"> </div>
		<div class="item"> <img src="/pics/Carousel/material4.jpg" alt="Forth slide"> </div>
		<div class="item"> <img src="/pics/Carousel/material5.jpg" alt="Fifth slide"> </div>
	</div>
	<!-- 轮播（Carousel）导航 --> 
	<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span> </a> <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span> </a> </div>
<div>
	<div style="position:absolute;right:30%;bottom:40%;z-index:100">
		<div class="search_box"> <span class="left l_bg"></span> <span class="right r_bg"></span>
			<div class="search">
				<form name=search_form onSubmit="return bottomForm(this);" target="_blank" method=post>
					<div id="pt1" class="select"> <a id="s0">全站搜索</a>
						<div style="display:none;" id="pt2" class="part">
							<p> <a id="s1">酒店</a>  </p>
						</div>
					</div>
					<input id="catid" name="catid" type="hidden" value="7">
					<input id="q" class="enter" name="infos" onFocus="if(this.value=='搜索内容...'){this.value='';}else{this.select();}this.style.color='black';"  value="搜索内容...">
					<input class="sb" name="Input" type="submit" value="">
				</form>
			</div>
		</div>
	</div>
</div>

<div id="foot">
	<p> <a href="/index">网站导航</a>| <a href="/index">技术支持</a>| <a href="">联系我们</a>|<a href="">网站声明</a></p>
	<br>
	黑ICP备17005584号&nbsp;Copyright © Chang'an University 坚冰文化, All Rights Reserved </div>
<script type="text/javascript" src="js/common.js"></script> 
<script  type="text/javascript" src="js/cityTemplate.js"></script>
<script>
function toHotel(){
	$(cityForm).submit();
}

	function a(){
		var user='${User.uname}';
		var message="${message}";
		if(user!=''){
	  	$("#loginBtn").hide();
	  	$("#logOut").show();
		}
		else if(message=="false"){
			alert("用户名不存在或密码错误！请重新尝试");
			//$('#myModal').modal('show');
		}
	}
	</script> 
</body>
</html>