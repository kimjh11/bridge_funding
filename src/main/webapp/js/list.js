$(function(){
	$(document).ready(function(){
		$('.banner-slider').bxSlider({
			auto: true
			, randomStart : true
		});
		$('.cate-nav ul').bxSlider({
			/*ticker: true
			, tickerHover : true
			, speed: 60000
			,*/
			auto: false
			, minSlides : 3
			, maxSlides : 7
			, slideWidth : 150
			, slideHeight : 55
			, moveSlides : 1
			, slideMargin: 5
			, pager: false
			, infiniteLoop: true
		});
	});
	
	$('.cate-nav ul').hover(
		function(){//마우스 인
			console.log('마우스 인');
		},function(){//마우스 아웃
			console.log('마우스 아웃');
		}
	);
	
	
	
});