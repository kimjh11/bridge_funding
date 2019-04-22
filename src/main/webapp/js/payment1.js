$(function(){
	$("#nextPage").click(function(){
		$(".two").css("background","gold");
		$("#s_title").html("참여자 목록에 이름과 펀딩금액이 공개됩니다.");
		  $('input[type=checkbox]:checked').each(function() { 
		       $(this).parent().parent().parent().submit();
		   });

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
	var option = $(".select").length;
		for(var k=0;k<=option;k++){
			optselect = $(".option:eq("+k+")").val();
			var opt = optselect.split("/");
			for(var i=0; i<=opt.length-1;i++ ){	
				$(".select:eq("+k+")").append("<option>"+opt[i]+"</option>");
			}
		}
	
	
})