$(function(){
	$("#next").click(function(){
		$(".three").css("background","gold");
		
		$.ajax({
			type : "POST",
			url : "payment3",
			success : function(data){
				$("#ajax").html(data);
			},
			error : function(){
				alert("실패");
			}
		})
	});
	$("#pev").click(function(){
		$(".two").css("background","none");
		$.ajax({
			type : "POST",
			url : "payment1",
			success : function(){
				$("#ajax").html(data);
			},
			error : function(){
				alert("못돌아가");
			}
			
		});
	});
})
