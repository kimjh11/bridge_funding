$(function(){
	$("#search").click(function(){
		 new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("subAddr").value = extraAddr;
	                
	                } else {
	                    document.getElementById("subAddr").value = '';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('zipcode').value = data.zonecode;
	                document.getElementById("mainAddr").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("deTailAddr").focus();
	            }
	        }).open();
	});
	$(".radio1").click(function(){
		$(".radio1:eq(0)").prop("checked",true);
	})
	$(".radio2").click(function(){
		$(".radio2:eq(0)").prop("checked",true);
	})
	var addrInfo = {};
	var cnt = $(".addr").length;
	for(var i=0;i<=cnt;i++){
		addrInfo[i] = $(".addr:eq("+i+")").val();
	}
	$(".addr").val("");
	$(".radio1").click(function(){
		$(".addr").val("");
		
	})
	$(".radio2").click(function(){
		for(var i=0;i<=cnt;i++){
			$(".addr:eq("+i+")").val(addrInfo[i]);
		}
	})
	$(".off").attr("disabled",true);
	$("#btn1").click(function(){
		$(this).addClass("on");
		$("#btn2").removeClass("on");
		$(".off").css("background","lightgray");
		$(".off").attr("disabled",true);
		$("#CardEnroll").css("background","none");
		$("#resultCardNum").attr("disabled",false);
		$("#writeNum").attr("disabled", true);
		$("#writeName").attr("disabled", true);
		$("#simpleName").attr("disabled", false);
		$("#simpleNum").attr("disabled", false);
		
	})
	$("#btn2").click(function(){
		$(this).addClass("on");
		$("#btn1").removeClass("on");
		$("#CardEnroll").css("background","lightgray");
		$("#payCard").css("background","none");
		$(".off").css("background","none");
		$(".off").attr("disabled",false);
		$("#resultCardNum").attr("disabled",true);
		$("#writeNum").attr("disabled", false);
		$("#writeName").attr("disabled", false);
		$("#simpleName").attr("disabled", true);
		$("#simpleNum").attr("disabled", true);

	})
	if($("#cardhidden").val() != null && $("#cardhidden").val() != "" ){
		$("#enroll1").text($("#cardhidden").val());
	}else{
		$("#enroll1").text("등록된 카드가 없습니다.");
	}
	$("#enter").click(function(){
		var o = 0;
		for(i =0;i<=$(".input").length-2;i++){
			if($(".input:eq("+i+")").val() == "" || $(".input:eq("+i+")").val() == null){
				o += i;
			}
		}
		for(i =0;i<=$(".off").length-1;i++){
			if($(".off:eq("+i+")").val() == "" || $(".off:eq("+i+")").val() == null){
				o += i;
			}
		}
		if(o>0){
			alert("빈칸을 입력해주세요.");
		}else{
			alert("info all")
		}
		
		$("#writeNum").val($("#tel1").val()+"-"+$("#tel2").val()+"-"+$("#tel3").val()+"-"+$("#tel4").val());
		
		if(o==0){
			$(".complete").submit();
		}
		
	});
	$("#prv").click(function(){
		$(".three").css("background","none");
		$("#s_title").html("참여자 목록에 이름과 펀딩금액이 공개됩니다.");
		$.ajax({
			type : "POST",
			url : "payment2",
			success : function(data){
				$("#ajax").html(data);
			},
			error : function(){
				alert("실패");
			}
		});
	});
	
})