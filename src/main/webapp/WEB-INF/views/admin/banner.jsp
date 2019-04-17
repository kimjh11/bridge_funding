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
<c:if test="${cnt>0 }">
	<script>  
		alert('등록완료!');
	</script>
</c:if>
<div class="wrap width-auto">
	<div class="admin-update-wrap">
		<h3>배너관리</h3>
		<div class="admin-content">
			<form action="<%=request.getContextPath() %>/bannerSubmit" method="post" id="bannerSubmitFrm" enctype="multipart/form-data">
				<ul>
					<li>
						<span class="bold">페이지선택</span>
						<select>
							<option>선택하세요</option>
							<option>리워드홈</option>
							<option>오픈예정</option>
							<option>기부와후원</option>
						</select>
						<input type="hidden" name="pageName" />
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
				<li class="col1">수정</li>
				<li class="col1">공개</li>
				<li class="col1">삭제</li>
			</ul>
			<ul class="list-content">
				<li class="col1">코드</li>
				<li class="col2">페이지명</li>
				<li class="col3">배너 타이틀</li>
				<li class="col3">배너 링크</li>
				<li class="col1"><a href=>수정</a></li>
				<li class="col1"><a href=>공개</a></li>
				<li class="col1"><a href=>삭제</a></li>
			</ul>
			<ul class="list-content">
				<li class="col1">코드</li>
				<li class="col2">페이지명</li>
				<li class="col3">배너 타이틀</li>
				<li class="col3">배너 링크</li>
				<li class="col1"><a href=>수정</a></li>
				<li class="col1"><a href=>공개</a></li>
				<li class="col1"><a href=>삭제</a></li>
			</ul>
		</div>
	</div>
</div>
<script>
	$(function(){
		$('#bannerSubmitFrm select').change(function(){
			$('input[name="pageName"]').val(this.value);
			console.log(this.value);
		});
	});
</script>
</body>
</html>