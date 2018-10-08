$(document).ready(function() {
    
    var top=document.getElementById("to_top");
    $(window).scroll(function() {
	if(document.documentElement.scrollTop+document.body.scrollTop==0){
	top.style.visibility="hidden";
	}else{
	top.style.visibility="visible";
	}
	});
	
	
	
})


	
function toTop(){
	"use strict";
	window.scrollBy(0,-60);
	var scrolldelay = setTimeout('toTop()',5);
	var sTop=document.documentElement.scrollTop+document.body.scrollTop;
	if(sTop==0) clearTimeout(scrolldelay);
}



$(document).ready(function() {
    $("li.detail").mouseleave(function(){
	  $(this).find("ul").stop();
 	  $(this).find("ul").hide();
  	  $(this).find("ul").css('height','0');
 });
    
    		    $("#service").mouseenter(function(){
        		$(this).find("img").attr("src","/pics/qq_c.jpg"); 
        	}); 
        	    $("#service").mouseleave(function(){
        	    $(this).find("img").attr("src","/pics/qq_b.jpg");      
        	}); 
})
    		
    		
    