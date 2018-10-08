<%@ page language="java" import="java.util.*" import="java.util.*,java.io.*,java.net.*,java.lang.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
 <head>
 <link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="/css/buttons.css">
 <link rel="stylesheet" href="/css/index.css" type="text/css" />
 <link rel="stylesheet" href="/css/style.css" type="text/css" />
 <link rel="stylesheet" href="/css/icons/iconfont.css" type="text/css" />
 <script type="text/javascript" src="/js/jquery2.2.2.min.js"></script>
 <script type="text/javascript" src="/js/jquery.easing.1.3.js"></script>
 <script type="text/javascript" src="/js/bootstrap.js"></script>
 <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="/js/dist/am-pagination.js"></script>
 
 <style>
 	a,a:visited,a:link,a:hover{
 	color:#000000;
 	text-decoration: none !important;
 	text-align:center;
 	}
 
	#m_img #mimg{
	border:0.5px solid grey;
	padding:155px 217px 155px 217px;
	display:block;
	
	}
	.m_bar #mimgAfter{
	border:1px solid;
	margin-right:auto;
	position:relative;
	margin:187px 248px 187px 248px;
	left:248px;
	}
  .i_lable{
  		font-size:18px; 
  }	
	
  #main{
  		margin-left:30px;
  }
  .m_bar{
  		padding-top:5px;
  		padding-bottom:5px;
  		border-bottom:dotted 1px grey;
  		display:none;
  }
  .m_bar span{
  		color:grey;
  		
  }
  .m_bar .text{
  		margin-left:70px;
  		width:80%;
  }
  #easy{
  		width:100%;
  		height:400px;
  }
  #easy_t{
  		width:52%;
  		height:400px;
  }
  .grade{
  		font-size:18px;
  }
  
   #roomtable{
   border:0;
   }
  
  #roomtable td{
  	text-align:center;
  	padding:15px;
  }
   #roomtable .rname{
  	top:56px;
  	position:relative;
  }
  
  #roomtable .rname{
  	top:56px;
  	position:relative;
  }
  
   #roomtable #title td{
  	border-right:1px solid grey;
  }
  	.orangePrice{
  	color:orange;
  	font-size:16px
  	}
 </style>
 
 
 
 <title>${hotel.hname}-木木</title>
 </head>
<body onload="ajaxAll()">
<div id="header">
	<div onclick="javascript:window.location.href='/index'" id="h_logo" > <img class="img-responsive" src="/pics/mumu_g.png" width="150px">
	<img  class="img-responsive" src="/pics/木木旅行_g.png" width="250px">
	</div>
	<div id="position">
	<form id="cityForm" action="/hotel" method="post">您的位置：
		<input type="text" placeholder="选择城市" id="place" name="city" value="${city}" readonly>
	</form>
		<div id="in_city" style="display: none"></div>
	</div>
	<div id="h_bar">
		<ul class="top_ul">
			<li><a onclick="toHotel()">酒店预订</a></li>
			<li><a href="/notes">周游记</a></li>
		</ul>
	</div>
	<div id="login"> <span><a href="/userCenter">${User.uname}个人中心</a></span> <a id="loginBtn"  data-toggle="modal" data-target="#myModal">登录/注册</a> <a id="logOut" href="index/logout" style="display:none;font-style:italic;">退出登录</a> </div>
	
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
	
</div>
<div id="main">		&nbsp;&nbsp;&nbsp;&nbsp;酒店列表><a href="/hotel?page=1&city=${city}&keyword=">${city}酒店</a>>${hotel.hname}<br>
	<div id="easy">
		<div id="easy_t"><h2>${hotel.hname}</h2>
			${hotel.address}<br>
			${hotel.introduce}<br>
			${hotel.tip1}<br>
		
		<span class="grade">携程评分：<span style="color:orange">${hotel.grade}</span></span><br><br>
		<span class="grade">途牛评分：<span id="Tmark" style="color:orange"><img src='/pics/hotel/loading3.gif'></span></span><br><br>
		<span class="grade">艺龙网评分: <span id="Emark" style="color:orange"><img src='/pics/hotel/loading3.gif'></span></span>
		</div>
	
		<div id="m_img" style="position:absolute;bottom:100px;left:55%;">
			<img id="mimg"  src="/pics/hotel/loading1.gif">
			<span id="lt1" style="position:relative;bottom:160px;margin-left:175px;font-size:17px"><strong>稍安勿躁，图片加载中. . .</strong></span>
		</div>
	</div>	
		<div id="imgs">
		</div>
		
		<div id="roomlist">
			<span style="color:blue;font-size:18px">房型列表</span><br>
				
			<table id="roomtable">
			<tr id="title">
					<td class="Roomtype">房型</td>
					<td class="Roomsize">房间大小</td>
					<td class="Rbreakfast">早餐说明</td>
					<td class="Rperson">可容纳人数</td>
					<td class="Rbed">床位</td>
					<td class="RCprice">携程网</td>
					<td class="RY">艺龙网</td>
					<td class="RT">途牛网</td>
			</tr>
			</table>
			<div id="loading2"><img src="/pics/hotel/loading1.gif">请稍等，正在努力加载中</div>
		</div>
		
		<div id="message">
			
			<span style="color:blue;font-size:18px">酒店信息</span><br>
				<div id="loading1"><img src="/pics/hotel/loading1.gif">请稍等，正在努力加载中</div>
			<div class="m_bar"><span>酒店介绍</span><div id="intro" class="text"></div></div>
			<div class="m_bar"><span>联系方式</span><div id="tele" class="text"></div></div>
			<div class="m_bar"><span>入住/离店</span><div id="IOtime" class="text"></div></div>
			<div class="m_bar"><span>早餐提供</span><div id="breakfast" class="text"></div></div>
			<div class="m_bar"><span>支付方式</span><div id="payment" class="text"></div></div>
			<div class="m_bar"><span>酒店设施</span><div id="facilities" class="text"></div></div>
			<div class="m_bar"><span>周边</span><div id="around" class="text"></div></div>
		</div>
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

<div>
</div>
<script  type="text/javascript" src="/js/cityTemplate.js"></script>
<script>
var RoomList=new Array();
function toHotel(){
	$(cityForm).submit();
}
function ajaxAll(){
	var user='${User.uname}';
	if(user!=''){
	  	$("#loginBtn").hide();
	  	$("#logOut").show();
		}
	ajaxHotelC();
	ajaxRoomC();
}

function ajaxRoomT() {
	$.ajax( {
        url: "/hotel/RoomAjaxT", //这里是静态页的地址
        type: "POST", 
        data:{"name":"${hotel.hname}","data":"${data}","city":"${city}","list":JSON.stringify(RoomList)},
        dataType: "json",
        async : true,
        success: function(data){
        	
        		for(var i=0;i<data.length-1;i++){
        			if(data[i].length>300){
        				$("#troom"+i).html("没有数据");
        				continue;
        			}
        			$("#troom"+i).html(data[i]);
        		}	
        		if(data[data.length-1].length>300){
        			$("#Tmark").text("没有数据");	 
    			}else
        		$("#Tmark").text(data[data.length-1]);	 
        		  		
        }, 
        error: function() {
        	
                alert("无此酒店(途牛)");
            }
		});
   }

function ajaxRoomY() {
	$.ajax( {
        url: "/hotel/RoomAjaxY", //这里是静态页的地址
        type: "POST", 
        data:{"name":"${hotel.hname}","data":"${data}","city":"${city}","list":JSON.stringify(RoomList)},
        dataType: "json",
        async : true,
        success: function(data){
        		for(var i=0;i<data.length-1;i++){
        			$("#yroom"+i).html(data[i]);
        		}	$("#Emark").text(data[data.length-1]);	   		
        }, 
        error: function() {
        	
                alert("无此酒店（艺龙）");
            }
		});
   }


function ajaxRoomC() {
	id=$(this).attr("id");
	var temp="";
	$.ajax( {
        url: "/hotel/RoomAjaxC", //这里是静态页的地址
        type: "POST", 
        data:{"cid":"${cid}","data":"${data}"},
        dataType: "json",
        async : false,
        success: function(data){
        	$("#loading2").hide();
        	$("#mimg").hide();
        		for(var i=0;i<data.length;i++){
        			RoomList[i]=data[i].name;
        			
        			$("#roomtable").append("<tr><td><img style='float:left' src='"+data[i].img+"'><span class='rname'>"+data[i].name+
        							"</span></td><td>"+data[i].size+
        							"</td><td>"+data[i].breakfast+
        							"</td><td>"+data[i].person+
        							"人</td><td>"+data[i].bed+
        							"</td><td>"+data[i].cName+"<br><span class='orangePrice'>"+data[i].cprice+
        							"<span>¥</td><td id='yroom"+i+"'>"+
        							"<img src='/pics/hotel/loading3.gif'></td><td id='troom"+i+"'>"+
        							"<img src='/pics/hotel/loading3.gif'></td></tr>");
        		}		
        		ajaxRoomY();    		
        		ajaxRoomT();
        }, 
        error: function() {
                alert("服务器无响应，请稍后再重新访问");
            }
		});
   }

function ajaxHotelC() {
	id=$(this).attr("id");
	var temp="";
	$.ajax( {
        url: "/hotel/hotelDetailAjaxC", //这里是静态页的地址
        type: "POST", 
        data:{"cid":"${cid}"},
        dataType: "json",
        async : false,
        success: function(data){
        		$("#loading1").hide();
        		$("#mimg").attr("src","http://"+data[0]);
        		$("#mimg").attr("style","width:495px;height:371px");
        		$("#mimg").attr("id","");
        		$("#lt1").hide();
        		
        		$(".m_bar").show();
        		$("#tele").append(data[1]);
        		$("#intro").append(data[2].replace(/<i class="i_label/g,"&nbsp;&nbsp;&nbsp;&nbsp;<i class=\"i_label label label-success"));
        		$("#IOtime").append(data[3]);
        		$("#breakfast").append(data[4]);
        		$("#payment").append(data[5]);
        		$("#facilities").append(data[6]);
        	}, 
        error: function() {
                alert("服务器无响应，请稍后再重新访问");
            }
		});
   }
</script>
 <script type="text/javascript" src="/js/script.js"></script>
 <div id="foot">
	<p> <a href="">网站导航</a>| <a href="">技术支持</a>| <a href="">联系我们</a>| <a href="">网站声明</a></p>
	<br>
	黑ICP备17005584号&nbsp;Copyright © Chang'an University 坚冰文化, All Rights Reserved </div>
</body>
</html>
