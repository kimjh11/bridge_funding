$(function(){
	$('.search-toggle').click(function(){
		var searchTxt = $('.top-search input[type="text"]').val();
		console.log(searchTxt);
		if(searchTxt==null || searchTxt==''){
			if($('.top-search input[type="text"]').css('display')=='block'){
				$('.top-search input[type="text"]').css('display','none');
				$('.top-search').removeClass('show');
			}else{
				console.log(this);
				$('.top-search input[type="text"]').css('display','block');
				$('.top-search').addClass('show');
			}
		}else{//검색어가 있을경우
			$('.top-search').submit();
		}
	});
});