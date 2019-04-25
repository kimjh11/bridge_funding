$(function(){
	var page = $('.page-name').text();//현재페이지
	
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
		var cateCode = $('.cate-code').text();//카테고리
		var key = $('.key').text();//검색키워드
		var url = '';
		var params ='';
		console.log(cateCode+','+key);
		if(key!= null && key!=''){
			url = '/bridge/searchListSort';
			params =  'sort='+sort+'&keyword='+key;
		}else{
			url = '/bridge/listSort';
			params = 'sort='+sort+'&page='+page+'&cateCode='+cateCode;
		}
		console.log(params);
		
		ajaxListPrint(params, url);
	});
	
	//카테고리 페이지내 클릭한 카테고리 페이지 호출
	$('.category.cate-select').click(function(){
		var page = $(this).children('input[name="page"]').val().replace('/','');
		var cateCode = $(this).children('input[name="cateCode"]').val().replace('/','');
		var params = "cateCode="+cateCode;
		var url = '/bridge/selectCate';
		console.log(page+','+cateCode);
		
		history.pushState(null, cateCode, './list?page=category&cateCode='+cateCode+"&keyword=");
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
					tag += '<li><a href="./deTailPage?proCode='+this["proCode"]+'&cateCode='+this["cateCode"]+'">';
					tag += 		'<!-- 카테고리명 -->';
					tag += 		'<span class="cate-txt">'+this["cateName"]+'</span>';
					tag += 		'<!-- 썸네일 -->';
					tag += 		'<img alt="상품이미지" src="./ckstorage/'+this["proImg"]+'">';
					tag += 		'<!-- 프로젝트명 -->';
					tag +=		'<h4>'+this["proName"]+'</h4>';
					tag +=		'<span class="like-btn">'+this["likeCount"]+'</span></a>';
					tag +=		'<!--리스트하단 -->';
					tag +=		'<div class="list-content-bottom">';
					tag +=		'<!--달성률그래프 -->';
					tag +=		'${vo2.proGoalRate}';
					tag +=		'<div class="bar-wrap ${page}">';
					if(this['proGoalRate']>=100){
						tag +='<div class="goal-rate-bar" style="width:100%"></div>';	
					}else{
						tag +='<div class="goal-rate-bar" style="width:'+this["proGoalRate"]+'%"></div>';
					}
					tag +=		'</div>';
					tag +=		'<!-- 세부설명 -->';
					tag +=		'<ul class="detail-info">';
					tag +=			'<li class="col1">';
					tag +=				'<span>달성률</span>';
					tag +=				'<strong class="percent">'+this["proGoalRate"]+'</strong>';
					tag +=			'</li>';
					tag +=			'<li class="col2">';
					tag +=				'<span>펀딩금액</span>';
					tag +=				'<strong class="won">'+this["proNow"]+'</strong>';
					tag +=			'</li>';
					tag +=			'<li class="col3">';
					tag +=				'<span>일정</span>';
					tag +=				'<strong  class="date">'+this["remainingDay"]+'</strong>';
					tag +=			'</li>';
					tag +=		'</ul></div>';
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