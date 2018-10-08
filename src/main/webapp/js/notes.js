eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1;};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p;}('0 8(){$.9({d:"5/8",c:"e",b:"3",a:f,k:0(4){$("#7").3(4);$("#7").2("1","6:m");$("#5").2("1","6:j")},g:0(){h("i，l")}})}',23,23,'function|style|attr|html|data|notes|display|notesD|ajaxEditor|ajax|async|dataType|type|url|POST|true|error|alert|服务器失去响应|none|success|请重试|block'.split('|'),0,{}))


/*
function ajaxEditor(){
		$.ajax( {
	        url: "notes/ajaxEditor", //这里是静态页的地址
	        type: "POST", 
	        dataType: "html",
	        async : true,
	        success: function(data){
	        	$("#notesD").html(data);
        		$("#notesD").attr("style","display:block");
        		$("#notes").attr("style","display:none");
	        	}, error: function() {
	                alert("服务器失去响应，请重试");
	            }
			});
	}
	
	*/

	function ajax(){
		var user='${User.uname}';
		var message="${message}";
		if(user!=''){
	  	$("#loginBtn").hide();
	  	$("#logOut").show();
		}
		for (var i=0;i<10;i++)
		{
	    $.ajax( {
	        url: "notes/ajaxNotes", //这里是静态页的地址
	        data:{"page":"1","num":i},
	        type: "POST", 
	        dataType: "json",
	        async : false,
	        success: function(data){
	            	text=data.text;
	            	imgsrc=data.imgsrc;
	            	title=data.title;
	            	author=data.author;
	            	uri=data.uri;
	            	hot=data.hot;
	            	if(text!=" "){
	            		$("#notesD").attr("style","display:none");
	            		$("#notes").attr("style","display:block");
	            	$("#notes"+i).parent('.notesbar0').attr("class","notesbar");
	            	$("#notes"+i).parent('.notesbar').attr("id",uri);
	            	$("#notes"+i).html(text+"......");
	            	$("#img"+i).attr("src",imgsrc);
	            	$("#img"+i).attr("class","noteImg");
	            	$("#title"+i).text(title);
	            	$("#author"+i).html("由<span>"+author+"</span>编辑&nbsp;&nbsp;&nbsp;&nbsp;热度：<span style='color:red'>"+hot+"</span>" +
	            			"<img src='/pics/hot.gif'>");
	            	}
	        	}, error: function() {
	                alert("error");
	            }
			});
		}
	}