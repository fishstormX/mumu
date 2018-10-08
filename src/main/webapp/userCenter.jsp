<%@ page language="java" import="java.util.*" import="java.util.*,java.io.*,java.net.*,java.lang.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>木木旅行——用户中心</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/buttons.css">
<link rel="stylesheet" href="css/index.css" type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="js/jquery2.2.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/sidebar-menu.css">
<style type="text/css">
.main-sidebar {
	width: 230px;
}
#detail{
	position:absolute;
	left:260px;
	top:160px;
	width:600px;
}
#sidebar_s{
	border-right:1.5px #f0f0f0 solid;
}
</style>
</head>
<body>
<div id="header" >
	<div onclick="javascript:window.location.href='/index'" id="h_logo" > <img class="img-responsive" src="pics/mumu_g.png" width="150px"> <img class="img-responsive" src="pics/木木旅行_g.png" width="250px"> </div>
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
	<div id="login"> <span  style="font:18px bold cursive;">${User.uname}</span>&nbsp;个人中心  </div>
	<script>
		var user='${User.uname}';
		if(user!=''){
	  	$("#loginBtn").hide();
	  	$("#logOut").show();
		}
		</script> 
</div>
<div id="sidebar">
	<aside class="main-sidebar">
		<section  class="sidebar" id="sidebar_s">
			<ul class="sidebar-menu">
				<li class="treeview"> <a href=""> <i class="icon-user"></i> <span>个人资料</span> <i class="fa fa-angle-left pull-right"></i> </a>
					<ul class="treeview-menu">
						<li><a id="l11" onclick="ajax(this)"><i class="fa fa-circle-o"></i> 资料查看</a></li>
						<li><a id="l12" onclick="ajax(this)"><i class="fa fa-circle-o"></i> 资料修改</a></li>
					</ul>
				</li>
				<li> <a id="l21" onclick="ajax(this)"> <i class="icon-comments-alt"></i> <span>站内信</span> <span class="label label-primary pull-right">0</span> </a>
					<ul class="treeview-menu">
					</ul>
				</li>
				<li> <a id="l31" onclick="ajax(this)"> <i class="icon-lock"></i> <span>绑定/密码修改</span> <small class="label pull-right label-info">new</small> </a>
					<ul class="treeview-menu">
					</ul>
				</li>
				<li class="treeview"> <a href=""> <i class="icon-file-alt"></i> <span>我的收藏</span> <i class="fa fa-angle-left pull-right"></i> </a>
					<ul class="treeview-menu">
						<li><a id="l41" onclick="ajax(this)"> 酒店</a></li>
					</ul>
				</li>
				<li> <a id="l61" onclick="ajax(this)"> <i class="icon-envelope"></i> <span>我有建议</span> </a>
					<ul class="treeview-menu">
					</ul>
				</li>
				<li> <a id="l71" onclick="logout()"> <i class="icon-remove-circle"></i> <span>退出登录</span> </a>
					<ul class="treeview-menu">
					</ul>
				</li>
			</ul>
		</section>
	</aside>
	<script src="./js/sidebar-menu.js"></script> 
	<script>
	$.sidebarMenu($('.sidebar-menu'))
	</script> 
</div>
<div id="detail"></div>
<script>
function toHotel(){
	$(cityForm).submit();
}

function logout(){
	window.location.href="/index/logout";
}

	function ajax(obj){
		var checkElement =$(obj).attr("id");	   
		
	    $.ajax( {
	        url: "userCenter/getDetail", //这里是静态页的地址
	        data:{"name":"${User.uname}","action":checkElement},
	        type: "POST", //静态页用get方法，否则服务器会抛出405错误
	        dataType:"html",
	        success: function(data){
	            $("#detail").html(data);
	        }
	});
	}
</script> 
<script  type="text/javascript" src="/js/cityTemplate.js"></script> 
<div id="foot" style="top:150px;position:relative">
	<p> <a href="">网站导航</a>| <a href="">技术支持</a>| <a href="">联系我们</a>|  <a href="">网站声明</a></p>
	<br>
	黑ICP备17005584号&nbsp;Copyright © Chang'an University 坚冰文化, All Rights Reserved </div>
</body>
</html>