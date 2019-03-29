$(function(){
	
	//이메일 유효성 검사
	$("#emailok").on("click",function(){
		//이메일 
		var emailVal = $("#userEmail").val();	
		var emailcheck = /^(\w+\.)*\w+@(\w+\.)+[A-Za-z]+$/;
		
		if(emailVal.match(emailcheck) == null){
	    	$("#warningid").text("이메일 형식이 옳바르지 않습니다 :)");
	    	return false;
	    }else if(emailVal.equals("") && emailVal == null){
			$("#warningid").text("이메일을 입력해 주세요:)");
			return false;
		}
	});
	
	//이메일 입력시 경고글  없어지게
	$("#userEmail").on("keydown",function(){
		("#warningid").text("");
	});
	
	//인증 버튼 클릭하면 인증번호 쓰는 부분 나오게
	("#emailOk").on("click",function(){
		//인증 번호를 가져올때 보이게 하기
		
	});
});