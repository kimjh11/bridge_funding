<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/bridge/css/deTailSpter.css"/>
<script type="text/javascript" src="/bridge/js/deTailSpter.js"></script>
	<div id="title">
		현재 이 프로젝트에 ${cnt}명이 참여했습니다.
	</div>
	<!-- for each로 계속 반복시킬 부분 -->
<c:forEach var="list" items="${list}">	
	<div class="OneSpter">
		<div class="Img">
			${list.userImg }
		</div>
		<div class="Spter">
			${list.userMail}님이 ${list.totalPrice}원 펀딩으로 참여하셨습니다.<br/>
			<div class="Time">${list.payDate }</div>
		</div>
	</div>
</c:forEach>
