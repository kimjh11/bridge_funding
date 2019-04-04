$(function(){
	var winWidth = $(window).width();
	var winHeight = $(window).height();
	var headerHeight = $('header').height();//헤더높이
	
	$('.home-wrap').ready(function(){
		background();
	});
	
	
	/* 윈도우창 크기 변경시 */
	$(window).resize(function(){
		winsize();
	});
	
	function winsize(){
		winWidth = $(window).width();
		winHeight = $(window).height();

		background();//배경설정
	};
	
	function background(){
		var backHeight = winHeight - headerHeight;//배경높이
		var top = (backHeight/2);
		console.log(top);
		$('.home-wrap #guide-back').css('border-top', backHeight+'px solid #021044');
		$('.home-wrap #reward-back').css('border-bottom', backHeight+'px solid #92D1E0');
		$('.home-wrap #guide-back a').css('top', -top+'px');
		$('.home-wrap #reward-back a').css('top', top+'px');
	};
	
	
	
});