<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>리워드 홈</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/list.js"></script>
</head>
<body>
<div class="reward-wrap">
	<!-- 상단배너  -->
	<div class="top-banner">
		<!-- 인기아이템/마감임박아이템/앵콜아이템/추천아이템 , 주제별 리스트페이지 이동 -->
		<ul class="main-banner banner-slider">
			<li>
				<a href="">
					<img src="<%=request.getContextPath()%>/img/reward/bannerImg01.jpg" alt="배너이미지"/>
				</a>
				<div class="txt">
					<p class="banner-title">배너 타이틀111</p>
					<p class="banner-sub-title">배너 서브타이틀222</p>
				</div>
			</li>
			<li>
				<a href="">
					<img src="<%=request.getContextPath()%>/img/reward/bannerImg01.jpg" alt="배너이미지"/>
				</a>
				<div class="txt">
					<p class="banner-title">배너 타이틀111</p>
					<p class="banner-sub-title">배너 서브타이틀222</p>
				</div>
			</li>
		</ul>
		<!-- 오픈예정 이동 배너 -->
		<div class="commingsoon-banner">
			<a href=""></a>
		</div>
	</div>
	<nav class="cate-nav">
		<ul>
			<li><a href="">카테고리1</a></li>
			<li><a href="">카테고리2</a></li>
			<li><a href="">카테고리3</a></li>
			<li><a href="">카테고리3</a></li>
			<li><a href="">카테고리3</a></li>
			<li><a href="">카테고리3</a></li>
			<li><a href="">카테고리3</a></li>
			<li><a href="">카테고리3</a></li>
		</ul>
	</nav>
	<div class="list-wrap">
		<div class="list-header">
			<span>전체보기</span>
			<form class="project-search">
				<input type="button" class="search-toggle txt-none" value="검색">
				<input type="text" name="serach" placeholder="프로젝트명 검색하기"/>
			</form>
			<select>
				<option>최신순</option>
				<option>인기순(좋아요)</option>
				<option>펀딩액순</option>
			</select>
		</div>
		<div class="list-content">
			<ul class="project-view">
			<c:forEach var="vo" begin="0" end="20">
				<li>
					<span class="cate-txt">카테고리명</span>
					<img alt="상품이미지" src="<%=request.getContextPath()%>/img/reward/listThumnail.jpg">
					<h4>프로젝트명</h4>
					<button class="like-btn">좋아요</button>
					<ul class="detail-info">
						<li class="col1">
							<span>목표달성</span>
							<strong>값</strong>
						</li>
						<li class="col2">
							<span>판매금액</span>
							<strong>값</strong>
						</li>
						<li class="col3">
							<span>종료일</span>
							<strong>값</strong>
						</li>
					</ul>
				</li>
			</c:forEach>
				
			</ul>
		</div>
	</div>
	

</div>
</body>
</html>