<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배너 관리페이지</title>
<link rel="styleSheet" href="<%=request.getContextPath() %>/css/adminModule.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="wrap width-auto">
	<div class="admin-update-wrap">
		<h3>배너관리</h3>
		<div class="admin-content">
			<form action="<%=request.getContextPath() %>/bannerSubmit" method="post" id="bannerSubmitFrm" enctype="multipart/form-data">
				<ul>
					<li>
						<span class="bold">페이지선택</span>
						<select name="pageName">
							<option value="">선택하세요</option>
							<option value="리워드홈">리워드홈</option>
							<option value="오픈예정">오픈예정</option>
							<option value="기부와후원">기부와후원</option>
						</select>
						<!-- <input type="hidden" name="pageName" /> -->
					</li>
					<li>
						<span class="bold">배너 타이틀</span>
						<input type="text" name="bannerTitle" />
					</li>
					<li>
						<span class="bold">배너 설명</span>
						<input type="text" name="bannerSubTitle" />
					</li>
					<li>
						<span class="bold">배너 이미지</span>
						<input type="file" name="bannerImgFile" />
					</li>
					<li>
						<span class="bold">배너 링크</span>
						<input type="text" name="bannerLink" />
					</li>
					<li>
						<input type="submit" value="배너 등록" />
					</li>
				</ul>
			</form>
		</div>
	</div>
	<div class="admin-list-wrap">
		<h3>배너 목록</h3>
		<div class="admin-content">
			<ul class="list-head bold">
				<li class="col1">코드</li>
				<li class="col2">페이지명</li>
				<li class="col3">배너 타이틀</li>
				<li class="col3">배너 링크</li>
				<li class="col1">공개</li>
				<li class="col1">수정</li>
				<li class="col1">삭제</li>
			</ul>
			<c:forEach var="vo" items="${bannerList }">
			<form>
			<ul class="list-content">
				<li class="col1">${vo.bannerNum }</li>
				<li class="col2">${vo.pageName }</li>
				<li class="col3">${vo.bannerTitle }</li>
				<c:if test="${vo.bannerLink==null || vo.bannerLink=='' }">
					<li class="col3">링크없음</li>
				</c:if>
				<c:if test="${vo.bannerLink!=null && vo.bannerLink!='' }">
					<li class="col3">${vo.bannerLink }</li>
				</c:if>
				<li class="col1"><span class="open-toggle">${vo.bannerOpen }</span></li>
				<li class="col1"><a href=>수정</a></li>
				<li class="col1"><a href=>삭제</a></li>
			</ul>
			</form>
			</c:forEach>
		</div>
	</div>
</div>
<script>
$(function(){
	//등록하기 버튼 클릭시 값 체크
	$('#bannerSubmitFrm').submit(function(){
		var selectIdx = $('select option').index($('select option:selected'));
		const arr = $('#bannerSubmitFrm ul li input').toArray();

		if(selectIdx==0){
			alert('페이지를 선택헤주세요');
			return false;
		}else if($(arr[0]).val()==null || $(arr[0]).val()==''){
			alert('배너 타이틀을 입력해주세요');
			return false;
		}else if($(arr[1])==null || $(arr[1]).val()==''){
			alert('배너 설명을 입력해주세요');
			return false;
		}else if($(arr[2])==null || $(arr[2]).val()==''){
			alert('이미지를 선택해주세요');
			return false;
		}else if($(arr[3])==null || $(arr[3]).val()==''){
			return confirm('링크없이 등록을 진행하시겠습니까?');
		}
	});
	
	//공개여부 클릭시
	$('.open-toggle').click(function(){
		const open = $(this);
		if(confirm('상태를 변경하시겠습니까?')){
			const num = $(this).parent().siblings().eq(0).text();		
			const params = 'itemNum='+num+'&table=banner';
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
	
	
</script>
</body>
</html>