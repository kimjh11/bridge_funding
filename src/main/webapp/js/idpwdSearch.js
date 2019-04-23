$(function(){
	//비밀번호 찾기 버튼 클릭시
	$("#pwd-title-div").on("click",function(){
		$("#pwd-div").css('display','block');
		$("#id-div").css('display','none');
		$("#pwd-title-div").css('background','#021044').css('color','white');
		$("#id-title-div").css('background','rgba(2, 16, 68, 0)').css('color','black');
	});
	
	//아이디 찾기 버튼 클릭시
	$("#id-title-div").on("click",function(){
		$("#id-div").css('display','block');
		$("#pwd-div").css('display','none');
		$("#id-title-div").css('background','#021044').css('color','white');
		$("#pwd-title-div").css('background','rgba(2, 16, 68, 0)').css('color','black');
	});
	 
	
	//이메일 인증번호 전송
	$("#id-btn-sending").click(function(){
		var uri = "/bridge/changePwd"
		var param = $("#frm").serialize();
		
		$.ajax({
			type: "POST",
			url: uri,
			data: param,
			success: function(result){
				//인증번호가 있으면 인증번호 입력창/ 인증확인 보이고, 인증요청 없어지게.
				$("#ok-number").attr('type','text');
				$("#joinStart").attr('type','button');
				$("#id-btn-sending").attr('type','hidden');
				
				//////////////////유효성 검사//////////////////////////
				//이메일 
				var emailVal = $("#user-mail-pwd").val();	
				var emailcheck = /^(\w+\.)*\w+@(\w+\.)+[A-Za-z]+$/;
				
				if(emailVal.match(emailcheck) == null){
			    	$("#warning").text("이메일 형식이 옳바르지 않습니다 :)");
			    	$("#ok-number").attr('type','hidden');
					$("#joinStart").attr('type','hidden');
					$("#id-btn-sending").attr('type','button');
					
			    	return false;
			    }
				//////////////////유효성 검사//////////////////////////
				
			},
			error: function(error){
				$("#warning").html("인증번호 전송 ERROR :(");
			}
		});	
	});
	
	//인증번호 확인
	$("#joinStart").click(function(){
		var uri = "/bridge/changePwdOk"
		var param = "okNum="+$("#ok-number").val();	
	
		$.ajax({
			type:"POST",
			url:uri,
			data:param,
			success:function(result){
				if(result == 'success'){
					$("#warning").html("인증이 완료 되었습니다.<br/>새 비밀번호로 변경해 주세요 :)");
					$("#user-pwd").attr('disabled',false);
					$("#user-pwdchk").attr('disabled',false);
					$("#ok-number").attr('type','hidden');
					$("#joinStart").attr('type','hidden');
				}
			},
			error:function(error){
				$("#warning").html("인증번호 확인 ERROR :(");
			}
		});
	});
	
	//이메일 입력시 경고글  없어지게
	$("#user-mail-pwd").on("keydown",function(){
		$("#warning").text("");
	});
	
	//비밀번호 변경 모든 값 입력시 넘어가게, 비밀번호 확인 검사
	$("#pwd-btn").click(function(){
		//alert("////");
		var usermail = $("#user-mail-pwd").val();
		var userpwd = $("#user-pwd").val();
		var userpwdchk = $("#user-pwdchk").val();
		var okNumber = $("#ok-number").val();
		
		if(usermail =="" || userpwd == "" || userpwdchk == ""){
			$("#warning").html("모든 값을 입력해주세요 :(");
			return false;
		}else if(okNumber == ""){
			$("#warning").html("모든 값을 입력해주세요 :(");
			return false;
		}
		
		//비밀번호 확인하기
	    if(userpwd != userpwdchk){
	    	$("#warning").text("비밀번호가 일치하지 않습니다.");	
	    	return false;
	    }
		
	});
	
});