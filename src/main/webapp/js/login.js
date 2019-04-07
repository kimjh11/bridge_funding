//헤더 로그인 클릭시 이벤트
$(function(){
	//클릭시 로그인 창 출력
	$("#login").click(function(){
		$("#login-div").toggle();
	});
	
	//로그인 아이디, 비밀번호 입력 없을시 경고글
	$("#login-btn").click(function(){
		var id = $("#login-user-mail").val();
		if(id==""){
			$("#login-warning").text("이메일 또는 비밀번호를 입력해 주세요:)");
		}else{
			return true;
		}
		return false;
	});
	/* 아이디 입력시 경고글 없어지게 하는 부분 */
	$("#login-user-mail").on("keydown",function(){
		$("#login-warning").text("");
	});
	
	$("#login-btn").click(function(){
		var pwd = $("#login-user-pwd").val();
		if(pwd == ""){
			$("#login-warning").text("이메일 또는 비밀번호를 입력해 주세요:)");
		}else{
			return true;
		}
		return false;
	});
	/* 비밀번호 입력시 경고글 없어지게 하는 부분 */
	$("#login-user-pwd").on("keydown",function(){
		$("#login-warning").text("");
	});
});