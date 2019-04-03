$(function(){
	//메뉴 파일 불러오기
	$('.guide-wrap').ready(function(){
		var filename = $('.guide-menu input[type="button"]').first().attr('name');
		getContent(filename);
	});
	//클릭한 메뉴 파일 불러오기
	$('input[type="button"]').click(function(){
		var filename = $(this).attr('name');
		getContent(filename);
	});
	//메뉴 파일 불러오기
	function getContent(filename){
		var url = 'txt/'+filename+'.txt';
		$.get(url, function(data, status){
			if(status=='success'){
				$('.guide-content').html(data);
			}
		});
	}
});