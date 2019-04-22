$(function(){
	var page = $('.page-name').text();
	$(document).ready(function(){
		$('.banner-slider').bxSlider({
			auto: true
			, randomStart : true
		});
		$('.cate-nav ul').bxSlider({
			//ticker: true
			//, tickerHover : true
			//, speed: 60000
			 auto: false
			, minSlides : 3
			, maxSlides : 7
			, slideWidth : 150
			, slideHeight : 55
			, moveSlides : 2
			, slideMargin: 5
			, pager: false
			, infiniteLoop: true
			, touchEnabled : false/*a태그 링크 이동 해결*/
			, onSliderLoad: function(){
				$("#edd").css("visibility", "visible").animate({opacity:1}); 
			}
		});
	});
	
	$('.cate-nav ul').hover(
		function(){//마우스 인
			console.log('마우스 인');
		},function(){//마우스 아웃
			console.log('마우스 아웃');
		}
	);
	
	//정렬순 페이지 호출
	$('select[name="sort"]').change(function(){
		var sort = this.value; //정렬기준
		var page = $('.page-name').text(); //페이지명
		var cateCode = $('.cate-code').text() ;
		var url = '/bridge/listSort';
		var params = 'sort='+sort+'&page='+page+'&cateCode='+cateCode;
		console.log(params);
		
		ajaxListPrint(params, url);

	});
	
	//검색페이지 정렬
	$('select[name="searchsort"]').change(function(){
		var sort = this.value; //정렬기준
		var keyword = $('input[name="keyword"]');
		console.log($('input[name="keyword"]').val());
		var url = '/bridge/searchListSort';
		var params = 'sort='+sort+'&keyword='+keyword;
		//console.log(params);
		
		//ajaxListPrint(params, url);

	});
	
	//카테고리 페이지내 클릭한 카테고리 페이지 호출
	$('.category.cate-select').click(function(){
		var page = $(this).children('input[name="page"]').val().replace('/','');
		var cateCode = $(this).children('input[name="cateCode"]').val().replace('/','');
		var params = "cateCode="+cateCode;
		var url = '/bridge/selectCate';
		console.log(page+','+cateCode);
		history.pushState(null, cateCode, './list?page=category&cateCode='+cateCode);
		ajaxListPrint(params, url);
	});

	//view
	function ajaxListPrint(params, url){
		$.ajax({
			type : 'get',
			url : url,
			data : params,
			contentType : 'application/json; charset=utf-8',
			success : function(data){
				var tag = '';
				$(data).each(function(){
					console.log(this['proImg']);
					tag += '<li>';
					tag += '<a href="./deTailPage?proCode='+this["proCode"]+'&cateCode='+this["cateCode"]+'">';
					tag += 		'<!-- 카테고리명 -->';
					tag += 		'<span class="cate-txt">'+this["cateName"]+'</span>';
					tag += 		'<!-- 썸네일 -->';
					tag += 		'<img alt="상품이미지" src="./ckstorage/'+this["proImg"]+'">';
					tag += 		'<!-- 프로젝트명 -->';
					tag +=		'<h4>'+this["proName"]+'</h4>';
					tag +=		'<!-- 세부설명 -->';
					tag +=		'<ul class="detail-info">';
					tag +=			'<li class="col1">';
					tag +=				'<span>목표달성</span>';
					tag +=				'<strong>'+this["proGoalRate"]+'</strong>';
					tag +=			'</li>';
					tag +=			'<li class="col2">';
					tag +=				'<span>판매금액</span>';
					tag +=				'<strong>'+this["proNow"]+'</strong>';
					tag +=			'</li>';
					tag +=			'<li class="col3">';
					tag +=				'<span>일정</span>';
					tag +=				'<strong>'+this["remainingDay"]+'</strong>';
					tag +=			'</li>';
					tag +=		'</ul>';
					tag +=	 '</a>';
					//tag +=		'<!-- //////////////////////좋아요 : 로그인 -->';
					//tag +=		'<c:if test="${logStatus=="Y" }">';
					//tag +=				'<c:forEach var="likeProCode" items="${likeProCode }" >';
					//tag +=				'<c:if test="${vo2.proCode==likeProCode }">';
					//tag +=					'<input type="button" class="like-btn" value="'+this["likeCount"]+'"/>';
					//tag +=				'</c:if>';
					//tag +=				'<c:if test="${vo2.proCode!=likeProCode }">';
					//tag +=					'<input type="button" class="like-btn on" value="'+this["likeCount"]+'"/>';
					//tag +=				'</c:if>';
					//tag +=				'</c:forEech>';
					//tag +=		'</c:if>';
					//tag +=		'<!--////////////////////// 좋아요 : 로그아웃 -->';
					//tag +=		'<c:if test="${logStatus=="N" }">';
					tag +=				'<span class="like-btn">'+this["likeCount"]+'</span>';
					//tag +=		'</c:if>';
					//tag += '</li>';
				});
				$('.project-view').html(tag);
			},
			error : function(e){
				alert(e.responseText);
			}
		});
	}
	
});