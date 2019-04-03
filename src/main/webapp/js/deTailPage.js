
$(function(){
	$("#tap td").click(function(){
			$("#tap td").removeClass("selectTap");
			$(this).addClass("selectTap");
		})
var next = 0;
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
	$(".taps").click(function(){
		var activeTap = $(this).attr("data-tab");
		$.ajax({
			type : "GET",
			url : activeTap,
			error : function(){
				console.log(activeTap);
				alert("실패");
			},
			success : function(data){
				$("#left").html(data);	
			}
		})
	})
	$.ajax({
			type : "GET",
			url : "deTailAtm",
			error : function(){
				alert("실패");
			},
			success : function(data){
				$("#firstblk").html(data);			
			}
	})
})