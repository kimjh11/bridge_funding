$(function(){
	$("#replyFrm").submit(function(){
		if($("#logStatus").val() == "Y"){
			if( $("#textarea").val() == ""){
				alert("댓글을 입력해주세요!!");
				return false;
			}
			
		}else{
			alert("로그인 후 사용 가능합니다.");
			return false;
		}
		
	})
})