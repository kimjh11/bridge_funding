$(function(){
	$("#ok").css({"background":"#92D1E0",
				  "height":"10px",
				  "margin-bottom":"0px"})
	
	if($("#goalRate").text()>=100){			  
		$("#ok").css("width","100%");
	}else{
		$("#ok").css("width",$("#goalRate").text());
	}
})