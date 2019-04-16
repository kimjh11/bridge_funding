$(function(){
	$("#nextPage").click(function(){
		$(".two").css("background","gold");
		$("#s_title").html("참여자 목록에 이름과 펀딩금액이 공개됩니다.");
		$.ajax({
			type : "POST",
			url : "payment2",
			success : function(data){
				$("#ajax").html(data);
			},
			error : function(){
				alert("에러여");
			}
		});
		
	});
})