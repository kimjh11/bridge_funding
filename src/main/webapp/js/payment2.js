$(function(){
	$("#next").click(function(){
		$(".three").css("background","gold");
		$("#s_title").html("종료일까지 목표한 금액이 달성해야만 결제가 진행됩니다.");
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
		$("#s_title").html("펀딩해주시는금액에 따라 감사의 의미로 리워드를 제공해 드립니다.");
		$.ajax({
			type : "POST",
			url : "payment1",
			success : function(data){
				$("#ajax").html(data);
			},
			error : function(){
				alert("오류가 발생했습니다.");
			}
			
		});
	});
})
