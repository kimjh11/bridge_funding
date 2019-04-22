var next = 0;
$(function(){
	
	$("button").click(function(){
		if($(this).text()==">"){
			next-=230;
			if(next<-460)next=0;
			$("#hidden").animate({marginLeft: next+"px"},500)
			
		}else if($(this).text()=="<"){
			next+=230;
			if(next>0)next=-460;
			$("#hidden").animate({marginLeft: next+"px"},500)
		}
	})
})