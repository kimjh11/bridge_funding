$(function(){
	//이메일 인증번호 전송
	$("#email-Ok").click(function(){
		var uri ="/bridge/mailSending" //서버에서 인증번호 리턴 받은것 처리하기
		var param = $("#frm").serialize(); //인증번호 전송될 이메일 
		
		$.ajax({
			//파라미터를 서버로 보내는 전송 방식 GET, POST
			type: "POST",
			//호출할 서버 페이지 컨트롤러
			url: uri,
			//
			data : param,
			//
			success : function(result){
				//인증번호가 있으면 인증번호 입력창/ 인증확인 보이고, 인증요청 없어지게.
				$("#ok-number").attr('type','text');
				$("#joinStart").attr('type','button');
				$("#email-Ok").attr('type','hidden');
				
				//////////////////유효성 검사//////////////////////////
				//이메일 
				var emailVal = $("#user-email").val();	
				var emailcheck = /^(\w+\.)*\w+@(\w+\.)+[A-Za-z]+$/;
				
				if(emailVal.match(emailcheck) == null){
			    	$("#warning-id").text("이메일 형식이 옳바르지 않습니다 :)");
			    	$("#ok-number").attr('type','hidden');
					$("#joinStart").attr('type','hidden');
					$("#email-Ok").attr('type','button');
					
			    	return false;
			    }
				//////////////////유효성 검사//////////////////////////
			},
			error : function(error){
				$("#warning-id").html("인증번호 전송 ERROR :(");
			}
		});	
	});
	
	//인증번호 확인
	$("#joinStart").click(function(){
		var uri ="/bridge/mailSendingOk"
		var param = "okNum="+$("#ok-number").val();
	
		$.ajax({
			//파라미터를 서버로 보내는 전송 방식 GET, POST
			type: "POST",
			//호출할 서버 페이지 컨트롤러
			url: uri,
			//
			data : param,
			//
			success : function(result){
				if(result == 'success'){
					$("#email-ok-label").html("인증이 완료 되었습니다. 다음 정보를 입력해 주세요 :)");
					$(".join-text").prop('disabled', false);
					$("#ok-number").attr('type','hidden');
					$("#joinStart").attr('type','hidden');
					$("#user-email").attr('readonly', true);
				}
			},
			error : function(error){
				$("#warning-id").html("인증확인 ERROR :(");
			}
		});	
	});
	
	//이메일 입력시 경고글  없어지게
	$("#user-email").on("keydown",function(){
		$("#warning-id").text("");
	});
	
	//가입하기 유효성 검사
	$(".join-btn").click(function(){
		alert("aaaa");
		var joinText = $(".join-text").val();
		var oknumber =$("#ok-number").val();
		
		if(joinText == null || oknumber == null){
			$("#warning-id").html("모든 값을 입력해주세요 :(");
		}
		
	});
});