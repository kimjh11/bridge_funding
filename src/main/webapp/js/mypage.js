$(function(){
	//간편결제 버튼 클릭시 
	$("#mypage-card-ok").click(function(){
		$("#card-submit").toggle();
	});
	//간편결제 x버튼 클릭시
	$("#card-x").click(function(){
		$("#card-submit").hide();
	})
	
	//서포터즈 - 나의 리워드
	$("#mypage-reward").click(function(){
		$("#myReward").css('display','block');
		$("#my-like").css('display','none');
		$("#mypage-reward").css('text-decoration','underline').css('color','#92D1E0');
		$("#mypage-like").css('text-decoration','none').css('color','black');
	});
	//서포터즈 - 좋아요
	$("#mypage-like").click(function(){
		$("#myReward").css('display','none');
		$("#my-like").css('display','block');
		$("#mypage-reward").css('text-decoration','none').css('color','black');
		$("#mypage-like").css('text-decoration','underline').css('color','#92D1E0');
	});
	
	//서포터즈
	$("#mypage-su").click(function(){
		$("#mypage-su").css('background','#021044').css('color','white');
		$("#mypage-cr").css('background','white').css('color','black');
		
		$("#mypage-reward").show();
		$("#myReward").show();
		
		$("#mypage-like").show();
		
		$("#cr-list").hide();
	});
	//크리에이터
	$("#mypage-cr").click(function(){
		$("#mypage-su").css('background','white').css('color','black');
		$("#mypage-cr").css('background','#021044').css('color','white');
		
		$("#cr-list").show();
		
		$("#mypage-reward").hide();
		$("#myReward").hide();
		
		$("#mypage-like").hide();
		$("#my-like").hide();
	});
	
	//좋아요 리스트
	$("#cr-list-like").click(function(){
		$("#like-list-div").css('display','block');
		$("#like-wrap").css('width',$(window).width()+'px').css('height',$(window).height()+'px');
		$("body").css('height',$(window).height()+'px');
	});
	//좋아요 리스트 x버튼 클릭시 
	$("#like-card-x").click(function(){
		$("#like-list-div").hide();
		$("#like-wrap").css('display','none');
	});
	
	//펀딩 리스트
	$("#cr-list-funding").click(function(){
		$("#funding-list-div").css('display','block');
		$("#funding-wrap").css('width',$(window).width()+'px').css('height',$(window).height()+'px');
		$("body").css('height',$(window).height()+'px');
	});
	//펀딩 리스트 x버튼 클릭시 
	$("#funding-card-x").click(function(){
		$("#funding-list-div").hide();
		$("#funding-wrap").css('display','none');
	});
});