<%@ page language="java" import="java.util.*" import="java.util.*,java.io.*,java.net.*,java.lang.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
 <head>
 <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="css/buttons.css">
 <link rel="stylesheet" href="css/index.css" type="text/css" />
 <link rel="stylesheet" href="css/style.css" type="text/css" />
 <link rel="stylesheet" href="/css/icons/iconfont.css" type="text/css" />
 <script type="text/javascript" src="js/jquery2.2.2.min.js"></script>
 <script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
 <script type="text/javascript" src="js/bootstrap.js"></script>
 <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="/js/dist/am-pagination.js"></script>
 
 <style>
 a,a:visited,a:link,a:hover{
 	color:#000000;
 	text-decoration: none;
 	text-align:center;
 	}
 #pages{
 	margin-left:150px;
 }
 .Himage{
  		width:200px;
	 	height:200px;
 }
 .Hname{
  		font-size: 20px;
 }
  .hotel_bar{
  		border-bottom:solid 1px grey;
  		width:92%;
  		height:240px;
  		margin-left:30px;
  		margin-top:10px;
  		margin-bottom:10px;
  }
   .hotel_bar .grade{
  		float:right;
  		font-size:16px;
  		}
   .hotel_bar .services{
  		color:#2894ff;
  		width:140px;
  		height:200px;
  		}
  	.hotel_bar .other{
  		margin-left:210px;
  		position:relative;
  		bottom:200px;
  		}
  		
  .hotelDetail{
  		height:200px;
  		position:relative;
  		width:80%;
  		margin-left:202px;
  		bottom:200px;
  }
  #main{
  		margin-left:30px;
  }
 </style>
 
 
 
 <title>木木旅行社——酒店</title>
 </head>
<body onload="ajaxHotel()">
<div id="header">
	<div onclick="javascript:window.location.href='/index'" id="h_logo" > <img class="img-responsive" src="pics/mumu_g.png" width="150px">
	<img  class="img-responsive" src="pics/木木旅行_g.png" width="250px">
	</div>
	<div id="position">
	<form id="cityForm" action="/hotel" method="post">您的位置：
		<input onblur="setTimeout('getChange()',100)" type="text" placeholder="选择城市" id="place" name="city" value="${city}" readonly>
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
	
	<!-- 模态框（Modal） -->
<div style="position:absolute;" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
	
</div>
<div id="main">		&nbsp;&nbsp;&nbsp;&nbsp;酒店列表><a href="/hotel?page=1&city=${city}&keyword=">${city}酒店</a>>${keyword}
		<div id="loading">
			<img src="/pics/hotel/loading.gif"><img src="/pics/hotel/loading_font.png">
		</div>
		<div id="hotel_list">
			
		</div>
		<section>
			<div id="pages" class="ampagination-bootstrap"></div>
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
<div id="foot">
	<p> <a href="dh/index.jsp">网站导航</a>| <a href="dh/index.jsp">技术支持</a>| <a href="">网站声明</a></p>
	<br>
	黑ICP备17005584号&nbsp;Copyright © Chang'an University 坚冰文化, All Rights Reserved </div>
<script  type="text/javascript" src="js/cityTemplate.js"></script>
<script type="text/javascript" src="/js/script.js"></script>
<script>
function toHotel(){
	$(cityForm).submit();
}

var pt=0;
function ajaxHotel() {
	var user='${User.uname}';
	if(user!=''){
	  	$("#loginBtn").hide();
	  	$("#logOut").show();
		}
	
	
	id=$(this).attr("id");
	var temp="";
	$.ajax( {
        url: "/hotel/getDetail", //这里是静态页的地址
        type: "POST", 
        data:{"city":"${city}","page":"${page}","keyword":"${keyword}"},
        dataType: "json",
        async : false,
        success: function(data){
        	$("#loading").hide();
        	for(i=0;i<25;i++){
        		if(data[i].hname==undefined)
        			break;
        		data[i].tip2=data[i].tip2.replace(eval("/"+" "+"/g"),"</span>&nbsp;<span class='label label-success'>");
        		
        		temp="<div class='hotel_bar' id='"+data[i].ctripid+"'><div class='Himage'>"+
				data[i].img+"</div><div class='hotelDetail'><span class='Hname'>"+
				data[i].hname+"</span><span class='grade'>评分：<span style='color:orange;font-size:18px;'>"+
				data[i].grade+"</span></span><br><small style='font-size:14px' class='label pull-right label-info'>"+
				data[i].tip1+"</small><br><div class='services'>"+		
				data[i].services+"</div><div class='other'><span>"+
				data[i].introduce+"</span><br><span><strong>地址：</strong>"+
				data[i].address+"</span><br><span><span class='label label-success'>"+
				data[i].tip2+"</span></span><br><span>最低价格：约<span style='color:orange;font-size:16px;'>"+
				data[i].lprice+"</span>元</span><br>"+
				"<button class='button button-tiny button-glow button-circle button-action button-primary' style='font-size:16px'>+</button><span class='markd' style='font-size:16px'>收藏酒店</span>"+
				"<button class='button  button-primary button-rounded button-small detail' style='position:absolute;right:0;font-size:16px'>详细信息</button></div></div></div>";
        		temp=temp.replace("公共区域免费WIFI","<i class='icon iconfont icon-icon_wifi_fill'></i>公共区域免费wifi&nbsp;<br>");
        		temp=temp.replace("公共区域WIFI","<i class='icon iconfont icon-icon_wifiwarning_fill'></i>公共区域wifi&nbsp;<br>");
        		temp=temp.replace("停车场","<i class='icon iconfont icon-parking1'></i>停车场&nbsp;<br>");
        		temp=temp.replace("会议室","<i class='icon iconfont icon-huiyishi1'></i>会议室&nbsp;<br>");
        		temp=temp.replace("餐厅","<i class='icon iconfont icon-canting'></i>餐厅&nbsp;<br>");
        		temp=temp.replace("游泳池","<i class='icon iconfont icon-youyongchi'></i>游泳池&nbsp;<br>");
        		temp=temp.replace("健身房","<i class='icon iconfont icon-jianshenfang'></i>健身房&nbsp;<br>");
        		temp=temp.replace("穿梭机场班车","<i class='icon iconfont icon-chuansuojichangbanche'></i>穿梭机场班车&nbsp;<br>");
        		temp=temp.replace("接机服务","<i class='icon iconfont icon-jiejifuwu'></i>接机服务&nbsp;<br>");
				$("#hotel_list").append(temp);
				
				
        		}
        	$.getScript("/js/hotel.js");  //加载js文件
        	pager = window.amPagination('.ampagination-bootstrap',{
        		page:${page},
        		totals:data[26].grade,
        		pageSize:1,
        		theme:'bootstrap',
        	 });
        	pager.onChangePage(function(e){
        		if(pt==1){
        		city=$("#place").val();
        		window.location.href="/hotel?page="+e.page+"&city="+city+"&keyword=${keyword}";
        			
        		}pt=1;
        		}); 
        		
        		
        	}, error: function() {
                alert("服务器无响应，请稍后再重新访问");
            }
		});
   }
   

var pager = window.amPagination('.ampagination-bootstrap',{
	page:${page},
	totals:10,
	pageSize:1,
	theme:'bootstrap',
 });




function getChange(){ 
	city=$("#place").val();
	if(city!="${city}")
	window.location.href="/hotel?page=1&city="+city+"&keyword=${keyword}";
}


</script>

</body>
</html>
