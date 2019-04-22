<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/bridge/js/payment2.js"></script>
<link rel="stylesheet" href="/bridge/css/payment2.css">
<div>
	<div class="checkBox">
		<div class="sel1"><input type="radio" name="pick"/> 이름/금액 공개</div>
		<div>예시*) <img src=""/> <span>???</span>님이 <span>?????원 펀딩</span>으로 참여하셨습니다.</div>
	</div>
	<div class="checkBox">
		<div class="sel1"><input type="radio" name="pick"/> 이름 공개/금액 미공개</div>
		<div>예시*) <img src=""/> <span>익명의 서포터즈</span> 님이 <span>?????원 펀딩</span>으로 참여하셨습니다.</div>
	</div>
	<div class="checkBox" id="last">
		<div class="sel1"><input type="radio" name="pick"/> 미공개</div>
		<div>예시*) <img src=""/> <span>???</span>님이<span>펀딩</span>으로 참여하셨습니다.</div>
	</div>
	<div>
		<div id="pev">이전페이지</div>
		<div id="next">다음페이지</div>
	</div>
</div>