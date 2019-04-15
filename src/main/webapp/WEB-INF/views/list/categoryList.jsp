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
<div class="reward-wrap cate-page-wrap">
	<!-- Category Nav Start -->
	<nav class="cate-nav">
		<ul>
		<c:forEach var="vo1" items="${cateList }">
			<li>
				<%-- <a href="<%=request.getContextPath()%>/list?menuIndex=2&banner=&cate=open&sorting=open&num=${vo1.cateCode }">${vo1.cateName }</a> --%>
				<a href="<%=request.getContextPath()%>/category?cateName=${vo1.cateName }">${vo1.cateName }</a>
				<input type="hidden" name="cateImg" value="${vo1.cateImg }">
			</li>
		</c:forEach>
		</ul>
	</nav>
	
	<div class="list-wrap">
		<div class="list-header">
			<span>전체보기</span>
			<select>
				<option>최신순</option>
				<option>인기순(좋아요)</option>
				<option>펀딩액순</option>
			</select>
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
						<!-- todo: 하트는 배경이미지로? / 클릭시 좋아요수 바로 변경될 수 있도록 -->
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
			</ul>
		</div>
	</div>
	

</div>
</body>
</html>