<%@ page language="java" import="java.util.*" import="java.util.*,java.io.*,java.net.*,java.lang.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
 <head>
 <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="css/buttons.css">
 <link rel="stylesheet" href="css/index.css" type="text/css" />
 <link rel="stylesheet" href="css/style.css" type="text/css" />
 <script type="text/javascript" src="js/jquery2.2.2.min.js"></script>
 <script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
 <script type="text/javascript" src="js/bootstrap.js"></script>
 <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

 <title>啊哦,页面跑丢了</title>
 </head>
<body>
<h1>啊哦,页面跑丢了，请稍后再进行尝试，正为你传送首页</h1>
<h2>loading</h2>
<p><br>可能的原因有:</p>
1.长时间未操作，用户失去会话<br>
2.当前访问用户过多，服务器失去资源<br>
3.服务器维护重置中<br>
4.你进行了一些不被允许的操作<br>


状态码：${message}
<script>
		
		 setTimeout(function(){window.location.href='/';}, 2000);
		 
		</script>
</body>
</html>