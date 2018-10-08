<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="css/buttons.css">
 <link rel="stylesheet" href="css/index.css" type="text/css" />
 <link rel="stylesheet" href="css/style.css" type="text/css" />
 <script type="text/javascript" src="js/jquery2.2.2.min.js"></script>
 <script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
 <script type="text/javascript" src="js/bootstrap.js"></script>
 <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>登录</title>
</head>
<body>
		<div style="position:absolute;" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div  class="modal-dialog" style="height:200px;">
		<div  class="modal-content" style="top:200px;"> <img style="position:absolute;left:-1px;" src="pics/login1.png" height="100%" width="50%">
			<div style="display:inline;" class="modal-header">
				<button style="float:right" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 style="left:50%;position:absolute;" class="modal-title" id="myModalLabel">用户登录</h4>
			</div>
			<div style="display:inline;" class="modal-body">
				<form action="/notes/upload" role="form" method="post" style="float:right" accept-charset="utf-8">
					<div class="form-group">
						<label for="name">用户名</label>
						<input type="text" class="form-control" name="name" id="name" placeholder="请输入用户名">
					</div>
					<div class="form-group">
						<label for="password">密码</label>
						<input type="password" class="form-control" name="password" id="password" placeholder="">
					</div>
					<div class="form-group">
						<textarea  name="text" placeholder="" hidden="hidden">${text}</textarea>
					</div>
					<button style="left:0" type="submit" class="button button-glow button-border button-rounded button-primary">登录</button>
				</form>
			</div>
			<div class="modal-footer"> 还没有账号？<a href="register.jsp">点此注册</a> </div>
		</div>
	</div>
</div>
</body>
</html>