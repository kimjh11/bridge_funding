$(function(){
	//비밀번호 버튼 클릭시
	$("#pwd-title-div").on("click",function(){
		$("#pwd-div").css('display','block');
		$("#id-div").css('display','none');
		$("#pwd-title-div").css('background','#021044').css('color','white');
		$("#id-title-div").css('background','rgba(2, 16, 68, 0)').css('color','black');
	});
	
	//아이디 버튼 클릭시
	$("#id-title-div").on("click",function(){
		$("#id-div").css('display','block');
		$("#pwd-div").css('display','none');
		$("#id-title-div").css('background','#021044').css('color','white');
		$("#pwd-title-div").css('background','rgba(2, 16, 68, 0)').css('color','black');
	});
});