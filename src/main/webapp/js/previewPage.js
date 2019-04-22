
$(function(){
	$("#tap td").click(function(){
			$("#tap td").removeClass("selectTap");
			$(this).addClass("selectTap");
		})
	$(".taps").click(function(){
		var activeTap = $(this).attr("data-tab");
		$.ajax({
			type : "POST",
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

})