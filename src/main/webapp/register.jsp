<%@ page language="java" import="java.util.*" import="java.util.*,java.io.*,java.net.*,java.lang.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>木木旅行——注册</title>
<head>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.css">
	<link rel="stylesheet" href="css/index.css" type="text/css" />
	<link rel="stylesheet" href="css/style.css" type="text/css" />
	<script type="text/javascript" src="js/jquery2.2.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<style>
	.form-control {
		width: auto;
		display: inline;
	}
	.warning {
		display: none;
		color: red;
		font-weight: 500;
	}
	</style>
</head>

<body onload="hw_init()">
<form  action="register/s" role="form" method="post" accept-charset="utf-8" style="margin-left:30px;margin-top:20px;">
	<div class="form-group">
		<label for="name">用户名</label>
		<input oninput="ajaxRequest()" type="text" class="form-control" name="uname" id="name" placeholder="请输入用户名">
		<span id="checkName" class="warning"><img src="pics/x.png">用户名已存在</span> <span id="checkName2" class="warning"><img src="pics/x.png">用户名不能为空</span> <span id="checkName3" class="warning"><img src="pics/x.png">用户名过长</span> </div>
	<div class="form-group">
		<label for="password">密码</label>
		<input oninput="ajaxRequest()" type="password" class="form-control" name="password" id="password" placeholder="">
		<span id="checkPass2" class="warning"><img src="pics/x.png">密码不能为空</span> <span id="checkPass3" class="warning"><img src="pics/x.png">密码过长</span> </div>
	<div class="form-group">
		<label for="passworda">确认密码</label>
		<input oninput="ajaxRequest()" type="password" class="form-control" name="passworda" id="passworda" placeholder="">
		<span id="checkPass"  class="warning"><img src="pics/x.png">两次输入密码不一致</span> </div>
	<div class="form-group">
		<label for="tele">手机号</label>
		<input oninput="ajaxRequest()" type="text" class="form-control" name="tele" id="tele" placeholder="请输入手机号码">
		<span id="checkTele"  class="warning"><img src="pics/x.png">请校验手机号码格式</span> <span id="checkTele2" class="warning"><img src="pics/x.png">手机号不能为空</span> </div>
	<div  class="form-group">
		<label for="gender">性别</label>
		<input style="width:15px;" id="man" value="男" type="radio" checked="checked" name="ge" />
		男
		<input style="width:15px;" value="女" id="woman" type="radio"  name="ge"/>
		女 </div>
	<div class="form-group">
		<label for="city">常驻城市</label>
		<select  name="hw_1"  onChange = "hw_select()">
		</select>
		<select name="hw_2"  onChange = "hw_select()">
		</select>
		<input class="input" size="15" disabled="disabled"    type="text" name="hw">
	</div>
	<button onclick="alert('注册成功！');" id="registerBtn" disabled="disabled" style="left:0" type="submit" class="button button-glow button-border button-rounded button-primary">注册</button>
</form><br><br>
<a style="margin:20px;font-size:16px;" href="/index">返回主页</a>
<script type="text/javascript" src="js/cityTemplate.js"></script>

</body>
</html>