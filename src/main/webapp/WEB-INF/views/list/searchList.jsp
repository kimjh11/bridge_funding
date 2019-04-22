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
	<div class="list-wrap">
		<div class="list-header">
			<span>전체보기</span>
			<!-- 정렬 -->
			<form>
				<span class="page-name" style="display:none">${page }</span>
				<span class="cate-code" style="display:block">${cateCode }</span>
				<select name="${page }sort">
					<option value="date">최신순</option>
					<option value="like">인기순(좋아요)</option>
					<option value="funding">펀딩액순</option>
				</select>
			</form>
			<form class="project-search">
				<input type="button" class="search-toggle txt-none" value="검색">
				<input type="text" name="serach" placeholder="프로젝트명 검색하기"/>
			</form>
		</div>
		<div class="list-content">
			<input type="hidden" name="keyword" value="${keyword }"/>
			<ul class="project-view">
			<c:forEach var="vo2" items="${searchList }">
				<li>
					<a href="./deTailPage?proCode=${vo2.proCode }&cateCode=${vo2.cateCode }">
						<img alt="상품이미지" src="<%=request.getContextPath()%>/ckstorage/${vo2.proImg}">
						<h4>${vo2.proName }</h4>
						<c:if test="${vo2.likeChk=='N' }">
							<button class="like-btn">${vo2.likeCount }</button>
						</c:if>
						<c:if test="${vo2.likeChk=='Y' }">
							<button class="like-btn">${vo2.likeCount }</button>
						</c:if>
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