<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="/js/jquery2.2.2.min.js"></script>

<title>成功提示</title>
</head>
<body>
		<h4>${User.uname},${Message}</h4>
		请稍等<span>3</span>秒
		<script>
		 var time=$("span").text();
		 setTimeout(function(){window.location.href='../${url}';}, 3000);
		 setInterval(clock,1001);
		 function clock(){
			 	time--;
			 	 $("span").text(time);
		 }
		</script>
		<div id="foot">
	<p> <a href="">网站导航</a>| <a href="">技术支持</a>| <a href="">联系我们</a>|  <a href="">网站声明</a></p>
	<br>
	黑ICP备17005584号&nbsp;Copyright © Chang'an University 坚冰文化, All Rights Reserved </div>
</body>
</html>