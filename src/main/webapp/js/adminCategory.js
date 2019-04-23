$(function(){
	var open = $('.open-toggle').toArray();
	console.log(open);
	
	/*if(open=='Y'){
		$('.open-toggle').parent().addClass('open');
	}
	console.log($('.open-toggle').text());*/
	
	//카테고리 등록 순위세팅 
	console.log($('.list-content .cate-rank').text());
	$('.list-content .cate-rank').text();
	//$('#cateSubmitFrm input[name="cateRank"]').val(cnt);
	//순서 selected
	var rank = $('.cate-rank').text();
	console.log(rank);
	var rankSelect = $('.rank-select option');
	console.log(rankSelect);
	if(rank=='미정'){
		//$(rankSelect).val('미정');
	}else{
		//$(rankSelect).val(rank).prop("selected", true);
	}
	
	//수정확인(submit)클릭시
	$('#cateUpdateFrm').submit(function(){
		var rank = $('input[name="cateRank"]').val();
		var cateName = $('input[name="cateName"]').val();
		var cateImg = $('input[name="cateImgFile"]').val();
		
		console.log('rank='+rank);
		console.log('name='+cateName);
		console.log('img='+cateImg);
		
		alert('실행');
		//text수정사항 없을경우
		if($('input[name="cateName"]').val==""
			&&$('input[name="cateImgFile"]').val==""){
			return false;
		}
	});
	
	//수정버튼(button) 클릭시
	$('.update-form-open').click(function(){
		var list = $('.list-content');//카테리스트
		var updateForm = $('.list-content.update-all');//업데이트 폼
		var updateBtn = $('.update-btn-wrap');//확인,취소버튼
		
		
		$(this).toggle();//수정하기 버튼 없애기
		$(updateBtn).css('display','block');//확인취소버튼 오픈
		$(list).css('display','none');//카테리스트 없애기
		$(updateForm).css('display','block');//업데이트폼 오픈
		
		
	});
	
	//수정취소클릭시
	$('.update-btn-wrap').click(function(){
		$('.update-form-open').css('display','block');
		$(this).toggle();
	});
	
	//공개여부 클릭시
	$('.open-toggle').click(function(){
		const open = $(this);
		if(confirm('상태를 변경하시겠습니까?')){
			const num = $(this).parent().siblings().eq(0).text();		
			const params = 'itemNum='+num+'&table=category';
			console.log(params);
			$.ajax({
				type : 'get',
				url : '/bridge/openToggle',
				data : params,
				contentType : 'application/json; charset=utf-8',
				success : function(result){
					console.log(open);
					if(result=='ok'){
						if(open.text()=='N'){
							open.text('Y');
						}else{
							open.text('N');
						}
					}
				},
				error : function(error){
					alert(error.responseText);
				}
			});
		}
	});
});