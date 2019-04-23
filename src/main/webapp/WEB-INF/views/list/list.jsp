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
<!-- Top Banner Start  -->
<c:if test="${page!='search' && page!='category'}">
	<%@ include file="../inc/topBanner.jspf" %>	
</c:if>
<!-- Category Nav Start -->
<c:if test="${page=='reward' || page=='category' }">
	<%@ include file="../inc/cateBanner.jspf" %>
</c:if>
<div class="list-wrap">
	<div class="list-header">
		<span>전체보기</span>
		<c:if test="${page!='commingSoon' }">
			<!-- 정렬 -->
			<div>
				<span class="page-name" style="display:none">${page }</span>
				<span class="cate-code" style="display:none">${cateCode }</span>
				<span class="key" style="display:none">${keyword }</span>
				<select name="sort">
					<option value="date">최신순</option>
					<option value="like">인기순(좋아요)</option>
					<option value="funding">펀딩액순</option>
				</select>
			</div>
			<!-- 검색 -->
			<%@ include file="../inc/search.jspf" %>
		</c:if>
	</div>
	<!-- 리스트 start -->
	<%@ include file="../inc/itemList.jspf" %>
</div>
</div>
</body>
</html>