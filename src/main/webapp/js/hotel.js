$('.detail').click(function(e) {
	id=$(this).parents(".hotel_bar").attr("id");
	name=$(this).parents(".hotelDetail").find(".Hname").text();
	city=$("#place").val();
	window.location.href="/hotel/hotelDetail?cid="+id+"&city="+city+"&name="+name;
	
   });

$('.button-tiny').click(function(e) {
	name=$(this).parents(".hotelDetail").find(".Hname").text();
	var ele=$(this);
	$.ajax( {
		 url: "/hotel/mark", //这里是静态页的地址
	     type: "POST", 
	     data:{"name":name},
	     dataType: "json",
	     async : false,
	     success: function(data){
	    	 if(data.data=="请先登录"){
	    		 alert("请先进行登录操作！");
	    	 }else if(data.data=="收藏成功！"){
	    		 ele.next().html("<span style='color:orange'>收藏成功</span>");
	    		 ele.attr("disabled","disabled");
	    	 }else{
	    		 alert("您已收藏过");
	    		 ele.next().html("<span style='color:orange'>收藏成功</span>");
	    		 ele.attr("disabled","disabled");
	    	 }
	    	 
	    	 
	     	}, error: function() {
	             alert("服务器无响应，请稍后再重新访问");
	         }
			});
   });