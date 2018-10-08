<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	
	.warning{
	display:none;
	color:red;
	font-weight:500;
	}
</style>
</head>
<body>
		<h2>修改信息</h2>
		<form action="userCenter/PasswordChange" method="post" id="form0">
					  <div class="form-group">
					    <label for="name">用户名(不可修改)</label>
					    <input value="${User.uname}" readonly="readonly" type="text" class="form-control" name="name" id="name" >
					  </div>
					  <div class="form-group">
					    <label for="password0">原密码</label>
					    <input  value="" type="password" class="form-control" name="password0" id="password0" >
					  </div>
					  <div class="form-group">
					    <label for="password1">新密码</label>
					    <input oninput="check()" value="" type="password" class="form-control" name="password1" id="password1" >
					 	<span id="checkPass2" class="warning"><img src="pics/x.png">密码不能为空</span>
					 	<span id="checkPass3" class="warning"><img src="pics/x.png">密码过长</span>
					  </div>
					  <div class="form-group">
					    <label for="password2">确认新密码</label>
					    <input oninput="check()" value="" type="password" class="form-control" name="password2" id="password2" >
					 	<span id="checkPass"  class="warning"><img src="pics/x.png">两次输入密码不一致</span>
					  </div>
					 
			 <button id="Btn" style="left:0" type="button" disabled="disabled" onclick="submit0()" class="button button-glow button-border button-rounded button-primary">修改信息</button>
		</form>
		<script>
		function check() {
      	  $('#Btn').attr("disabled",false);
			var p1 = $("#password1").val();
			var p2 = $("#password2").val();
			if(p1==""){
				$("#checkPass2").show();
				$("#checkPass").hide();
            	$("#checkPass3").hide();
            	$('#Btn').attr("disabled",true);
			}else if(p1.length>16){
				$("#checkPass3").show();
				$("#checkPass1").hide();
            	$("#checkPass2").hide();
	        	$('#Btn').attr("disabled",true);
			}else if(p1==p2){
				$("#checkPass").hide();
				$("#checkPass").hide();
            	$("#checkPass2").hide();
			}else{
				$("#checkPass").show();
				$("#checkPass2").hide();
            	$("#checkPass3").hide();
	        	$('#Btn').attr("disabled",true);
			}
		}
		
		function submit0(){
			var p0 = $("#password0").val();
	        $.ajax({
	          url: "ajaxp",
	          type: "POST",
	          dataType: "json",
	          data: {
	          	"password0":p0,
	          	"uname":"${User.uname}"
	          },
	          async: true,
	          success: function(data) {
	        	  $('#Btn').attr("disabled",false);
	        	  	if(data.flag=="true"){
	        	  		alert("正确");
	        		  $('#form0').submit();
	        	  	}else{
	        	  		alert("原密码输入错误");
	        	  	}
	          },
	          error: function() {
	            alert("error");
	          }
	        });
	      }
		</script>
</body>
</html>