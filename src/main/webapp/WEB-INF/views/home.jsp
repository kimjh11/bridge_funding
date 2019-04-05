<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/home.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script src="<%=request.getContextPath()%>/js/home.js"></script>
<title>브릿지 펀딩</title>
</head>
<body>
<div class="home-wrap">
	<div class="background" id="guide-back">
		<a href="<%=request.getContextPath()%>/guideHome">
			<img alt="브릿지 이용안내" src="<%=request.getContextPath()%>/img/home/home-br-icon-wh.png"/>
			<h2>브릿지 이용안내</h2>
			<span>처음 방문한 고객님을 위한 브릿지 펀딩 이용 안내</span>
		</a>
	</div>
	<div class="background" id="reward-back">
		<a href="<%=request.getContextPath()%>/reward">
			<img alt="리워드 이동하기" src="<%=request.getContextPath()%>/img/home/home-in-icon.png"/>
			<h2>리워드 이동하기</h2>
			<span>모든 서포터즈와 메이커를 위한 리워드 홈</span>
		</a>
	</div>
</div>
</body>
</html>

