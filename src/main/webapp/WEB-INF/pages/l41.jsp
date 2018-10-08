<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	
	.marks{
		height:20px;
		margin:5px 0 10px 0;
		border-bottom:solid 1px grey;
		width:80%;
		color:blue;
		cursor:pointer;
	}
</style>
</head>
<body>
	
	
	<div id="mark0" class="markz"></div>
	<div id="mark1" class="markz"></div>
	<div id="mark2" class="markz"></div>
	<div id="mark3" class="markz"></div>
	<div id="mark4" class="markz"></div>
	<div id="mark5" class="markz"></div>
	<div id="mark6" class="markz"></div>
	<div id="mark7" class="markz"></div>
	<div id="mark8" class="markz"></div>
	<div id="mark9" class="markz"></div>
	
	<div id="page"></div>
	<script>
		
		$('.markz').click(function(e) {
			var hotel=$(this).text();
			$.ajax({
		          url: "/hotel/getUByName",
		          type: "POST",
		          dataType: "json",
		          data: {
		          	"hotel":hotel
		          },
		          async: true,
		          success: function(data) {
		        	 window.location.href=data.url;
		          },
		          error: function() {
		            alert("error");
		          }
		        });
		});
		var marks="${User.mark}";
		var page=1;
		arr=marks.split("|");
		var tp=arr.length/10;
		
		
		for(var i=1;i<tp+1;i++){
			if(tp<20)
				$("#page").append("<a onclick='changePage("+i+")'>"+i+"</a>&nbsp;&nbsp;")
			else
				break;
		}
		for(var i=0;i<10;i++){
			if(arr[i]!=undefined){
				$("#mark"+i).text(arr[i+page*10-10]);
				$("#mark"+i).attr("class","marks");
			}else{
				$("#mark"+i).text("");
				$("#mark"+i).attr("class","markz");
			}
		}
		function changePage(j){
			page=j;
			for(var i=0;i<10;i++){
			if(arr[i+page*10-10]!=undefined){
				$("#mark"+i).text(arr[i+page*10-10]);
				$("#mark"+i).attr("class","marks");
			}else{
				$("#mark"+i).text("");
				$("#mark"+i).attr("class","markz");
			}
			}
		}
	</script>

</body>
</html>
