$(function(){
	/* 검색버튼 클릭이벤트 */
	$('.search-toggle').click(function(){
		var searchTxt = $('.project-search input[type="text"]').val();
		console.log($(this).siblings('input'));
		if(searchTxt==null || searchTxt==''){
			//검색어없이 검색버튼 클릭시 창 숨기기
			if($(this).siblings('input[type="text"]').css('display')=='block'){
				$(this).siblings('input[type="text"]').css('display','none');
				$(this).parent().removeClass('show');
			}else{
				//검색창 보이기
				$(this).siblings('input[type="text"]').css('display','block');
				$(this).parent().addClass('show');
			}
		}else{//검색어가 있을경우
			$(this).parent().submit();
		}
	});
	
	//토글버튼 클릭시 함수호출
	$('.guide-toggle').click(function(){
		guideShow();
	});
	
	//////////이용안내 페이지 열고 닫기
	function guideShow(){
		if($('.guide-wrap').hasClass('show')){
			$('.toggle-bar').removeClass('on');
			$('.guide-wrap').removeClass('show');
			$('.guide-wrap').css('height', '0px');
		}else{
			$('.toggle-bar').addClass('on');
			$('.guide-wrap').addClass('show');
			$('.guide-wrap').css('min-height', $(document).height()+'px');
		}
	};
	
	//이용안내  첫페이지 파일 불러오기
	$('.guide-wrap').ready(function(){
		var filename = $('.guide-menu input[type="button"]').first().attr('name');
		getContent(filename);
	});
	
	//클릭한 파일 불러오기
	$('.guide-menu input[type="button"]').click(function(){
		var filename = $(this).attr('name');
		getContent(filename);
	});
	
	/////////이용안내 메뉴파일 불러오기
	function getContent(filename){
		var url = 'txt/'+filename+'.txt';
		$.get(url, function(data, status){
			if(status=='success'){
				$('.guide-content').html(data);
			}
		});
	}
	
	//스크롤 이벤트 
	$(window).scroll(function(){
		//console.log($(this).scrollTop());
		//console.log($(document).height());
		if($(this).scrollTop()>=900){
			$('header .top-controller').addClass('show');
		}else{
			$('header .top-controller').removeClass('show');
		}
	});
	
	//맨위로 이동
	$('header .top-controller').click(function(){
		$('html, body').scrollTop(0);
	});
	
	
});

