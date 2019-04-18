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
	<!-- Top Banner Start  -->
	<div class="top-banner">
		<!-- 인기아이템/마감임박아이템/앵콜아이템/추천아이템 , 주제별 리스트페이지 이동 -->
		<ul class="main-banner banner-slider">
		<c:forEach var="vo" items="${bannerList }">
			<li> 
				<a href="" title="${vo.bannerLink }">
					<img src="<%=request.getContextPath()%>/img/banner/${vo.bannerImg}" alt="배너이미지"/>
				</a>
				<div class="txt">
					<p class="banner-title">${vo.bannerTitle }</p>
					<p class="banner-sub-title">${vo.bannerSubTitle }</p>
				</div>
			</li>
		</c:forEach>
		</ul>
		<!-- 오픈예정 이동 배너 -->
		<div class="commingsoon-banner">
			<a href="<%=request.getContextPath()%>/list?page=오픈예정&cate=">오픈예정 프로젝트 보러가기</a>
		</div>
	</div>
	<!-- Top Banner End -->
	
	<!-- Category Nav Start -->
	<nav class="cate-nav">
		<ul>
		<c:forEach var="vo1" items="${cateList }">
		<li>
			<a href="./category?page=${vo1.cateCode }" style="background:url('./img/category/${vo1.cateImg}')">${vo1.cateName }</a>
		</li>
		</c:forEach>
		</ul>
	</nav>
	
	<div class="list-wrap">
		<div class="list-header">
			<span>전체보기</span>
			<c:if test="${sort=='Y' }">
				<select>
					<option>최신순</option>
					<option>인기순(좋아요)</option>
					<option>펀딩액순</option>
				</select>
			</c:if>
			<form class="project-search">
				<input type="button" class="search-toggle txt-none" value="검색">
				<input type="text" name="serach" placeholder="프로젝트명 검색하기"/>
			</form>
		</div>
		<div class="list-content">
			<ul class="project-view">
			<c:forEach var="vo2" items="${itemList }">
				<li>
					<a href="">
					<input type="hidden" name="cateCode" value="${vo2.cateCode }" />
					<input type="hidden" name="likeClick" value="${vo2.likeClick }" />
						<span class="cate-txt">${vo2.cateName }</span>
						<img alt="상품이미지" title="${vo2.proImg }" src="<%=request.getContextPath()%>/img/reward/listThumnail.jpg">
						<h4>${vo2.proName }</h4>
						<!-- to do: 하트는 배경이미지로? / 클릭시 좋아요수 바로 변경될 수 있도록 -->
						<button class="like-btn">${vo2.likeCount }</button>
						<ul class="detail-info">
							<li class="col1">
								<span>목표달성</span>
								<strong></strong>
							</li>
							<li class="col2">
								<span>판매금액</span>
								<strong>${vo2.proGoal }</strong>
							</li>
							<li class="col3">
								<span>종료일</span>
								<strong>${vo2.remainingDay }</strong>
							</li>
						</ul>
						
					</a>
				</li>
			</c:forEach>
			<%-- <c:forEach var="vo" begin="0" end="20">
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
			</c:forEach> --%>
				
			</ul>
		</div>
	</div>
	

</div>
</body>
</html>