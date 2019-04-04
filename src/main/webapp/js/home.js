$(function(){
	var winHeight = $(window).height();
	var headerHeight = $('header').height();//헤더높이
	
	$('.home-wrap').ready(function(){
		background();
	});	
	// 윈도우창 크기 변경시 
	$(window).resize(function(){
		winsize();
	});
	
	function winsize(){
		winHeight = $(window).height();
		background();//배경설정
	};
	
	function background(){
		var backHeight = winHeight - headerHeight;//배경높이
		var top = (backHeight/2);
		$('#guide-back').css('border-top-width', backHeight+'px');
		$('#reward-back').css('border-bottom-width', backHeight+'px');
		$('#guide-back > *').css('top', -top+'px');
		$('#reward-back > *').css('top', top+'px');
	};
});