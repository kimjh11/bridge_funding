<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
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
<%@ include file="../inc/topBanner.jspf" %>	
<%@ include file="../inc/cateBanner.jspf" %>	
<div class="list-wrap">
	<div class="list-header">
		<span>전체보기</span>
		<c:if test="${page!='commingSoon' }">
		<!-- 정렬 -->
		<form>
			<span class="page-name" style="display:none">${page }</span>
			<span class="cate-code" style="display:none">${cateCode }</span>
			<select name="sort">
				<option value="date">최신순</option>
				<option value="like">인기순(좋아요)</option>
				<option value="funding">펀딩액순</option>
			</select>
		</form>
		</c:if>
		<!-- 검색 -->
		<form class="project-search" method="get" action="/bridge/search">
			<input type="text" name="serach" placeholder="프로젝트명 검색하기"/>
			<input type="button" class="search-toggle txt-none" value="검색">
		</form>
	</div>
	
	<!-- 리스트 start -->
	<div class="list-content">
		<ul class="project-view">
		<c:forEach var="vo2" items="${itemList }">
			<li>
				<!-- 리스트 상단  -->
				<a href="./deTailPage?proCode=${vo2.proCode }&cateCode=${vo2.cateCode }">
					<!-- 카테고리명 -->
					<span class="cate-txt">${vo2.cateName }</span>
					<!-- 썸네일 -->
					<img alt="상품이미지" src="<%=request.getContextPath()%>/ckstorage/${vo2.proImg}">
					<!-- 프로젝트명 -->
					<h4>${vo2.proName }</h4>
					<!-- 좋아요 -->
					<!-- <input type="button" class="like-btn" name="likeCount" value="${vo2.likeCount }"> -->
					<span class="like-btn">${vo2.likeCount }</span>
				</a>
				<!-- 리스트 하단 -->
				<div class="list-content-bottom">
					<!-- 달성률 그래프 -->
					<c:if test="${page!='commingSoon' }">
					<div class="bar-wrap">
						<c:if test="${vo2.proGoalRate >= 100}">
							<div class="goal-rate-bar" style="width:100%"></div>
						</c:if>
						<c:if test="${vo2.proGoalRate < 100}">
							<div class="goal-rate-bar" style="width:${vo2.proGoalRate }%"></div>
						</c:if>
					</div>
					</c:if>
					<!-- 상세설명 -->
					<ul class="detail-info">
						<li class="col1">
							<span>목표달성</span>
							<strong>${vo2.proGoalRate }</strong>
						</li>
						<li class="col2">
							<span>판매금액</span>
							<strong>${vo2.proNow }</strong>
						</li>
						<li class="col3">
							<span>일정</span>
							<strong>${vo2.remainingDay }</strong>
						</li>
					</ul>
				</div>
			</li>
		</c:forEach>				
		</ul>
	</div>
</div>
	

</div>
</body>
</html>