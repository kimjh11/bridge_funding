<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="/bridge/css/payment.css">
<script type="text/javascript" src="/bridge/js/payment.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>결제 페이지</title>
</head>
<body>
<div class="wrap">
		<iframe src="#" name="iframe" style="width:1px; height:1px; border:0; visibility:hidden;"></iframe>
	<div class="titleDiv"> 
		<div id="arrow1"></div>
		<div id="arrow2"></div>
		<span id="s_title">펀딩해주시는금액에 따라 감사의 의미로 리워드를 제공해 드립니다.</span>
		<div class="one"></div>
		<div id="one">1<br/>리워드<br/> 선택</div>
		<div class="two"></div>
		<div id="two">2<br/>공개여부<br/> 선택</div>
		<div class="three"></div>
		<div id="three">3<br/>결제 예약</div>
	</div>
	<div id="ajax">
		<c:forEach var="list" items="${list}">
			<form method="post" action="/bridge/payment2" name="frm${list.itemRank}" id="frm${list.itemRank}" target="iframe" >
			<div class="tap">
				<div id="select">
					<input type="checkbox" value="${list.itemRank}" name="selectRank"/> 선택${list.itemRank}
					<input  type="hidden" value="${list.itemNum}" name="itemNum"/> 
				</div>
				<div id="money">
					펀딩금액 ${list.itemPrice}원
					<input type="hidden" name="itemPrice" value="${list.itemPrice}"/>
				</div>
			</div>
			<div class="content">
				<div class="txt_center">
					<span class="optionTitle">${list.itemName}</span><br/>
					${list.itemContent}
					<input type="hidden" name="itemName" value="${list.itemName }"/>
					<input type="hidden" name="itemContent" value="${list.itemContent}"/>
				</div>
				<div class="opt_option">
					<div class="txt1">
						옵션명 
					</div>
					<div class="txt2">
						<select class="select" name='itemOption'>
							<input type="hidden" value="${list.itemOption}" class="option"/>
						</select>
					</div>
					<div class="txt1">
						수량
					</div>
					<div class="txt2">
						<input type="text" name="selectCount"/>
					</div>
				</div>
			</div>
				<div class="bottom">
					<div class="btm1" id="first">
						남은수량/제한수량  
					</div>
					<div class="btm2">
						${list.stateCnt }/${list.limitCnt}
					</div>	
					<div class="btm1">
						발송일 : 
					</div>	
					<div class="btm2">
						~ ${proEnd}까지
					</div>	
				</div>
			</form>
		</c:forEach>
		<button id="nextPage">다음페이지 </button>
	</div>
</div>
</body>
</html>