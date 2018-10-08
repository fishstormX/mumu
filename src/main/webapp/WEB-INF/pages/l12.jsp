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
		<form action="userCenter/MessageChange" method="post">
					  <div class="form-group">
					    <label for="tele">手机号</label>
					    <input oninput="ajaxxRequest()" value="${User.tele}" type="text" class="form-control" name="tele" id="tele" placeholder="请输入手机号码">
					  	<span id="checkTele"  class="warning"><img src="pics/x.png">请校验手机号码格式</span>
					 	<span id="checkTele2" class="warning"><img src="pics/x.png">手机号不能为空</span>
					  </div>
					  <div  class="form-group">
					    <label for="gender">性别</label>
					 	<input style="width:15px;" id="man" value="男" type="radio" checked="checked" name="ge" />男
					 	<input style="width:15px;" value="女" id="woman" type="radio"  name="ge"/>女
					  </div>
					  <div class="form-group">
					    <label for="city">常驻城市</label>
					    <select name="hw_1"  onChange = "hw_select()"></select>
					    <select name="hw_2"  onChange = "hw_select()"></select> 
					    <input value="${User.city}" readonly="readonly" class="input" size="15"    type="text" name="hw">
					  </div>
			 <button id="Btn" style="left:0" type="submit" class="button button-glow button-border button-rounded button-primary">修改信息</button>
		</form>
		<script type="text/javascript" src="js/cityTemplate.js"> </script>
		<script type="text/javascript">
		function ajaxxRequest(){
			var tele = $("#tele").val();
	        $.ajax({
	          url: "ajax",
	          type: "POST",
	          dataType: "json",
	          data: {
	          	"tele":tele
	          },
	          async: true,
	          success: function(data) {
			if(data.tele=="1"){
            	$("#checkTele").show();
            	$("#checkTele2").hide();
            	$('#Btn').attr("disabled",true);
            }else if(data.tele=="0"){
            	$("#checkTele2").show();
            	$("#checkTele").hide();
            	$('#Btn').attr("disabled",true);
            }else{$("#checkTele").hide();$("#checkTele2").hide();$('#Btn').attr("disabled",false);}
	          },
	          error: function() {
	            alert("error");
	          }
	        });
	      }
		</script>
		<script type="text/javascript">hw_init();</script>
</body>
</html>