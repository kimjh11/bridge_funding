<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="/bridge/css/payment.css">
<script type="text/javascript" src="/bridge/js/payment.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>결제 페이지</title>
</head>
<body>
<div class="wrap">
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
		<div id="foreach돌릴곳">
			<div class="tap">
				<div id="select">
					<input type="checkbox" value="선택1"/> 선택1
				</div>
				<div id="money">
					펀딩금액 ???원
				</div>
			</div>
			<div class="content">
			<div class="txt_center">
				<span class="optionTitle">슈퍼얼리버드 더 와이즈 로봇키즈(제목)</span><br/>
				더와이즈로봇키즈 1세트(옵션)<br/>
				구성품 : 로봇교구1세트 + 워크북 12권 등등(설명)
			</div>
				<div class="opt_option">
					<div class="txt1">
						옵션명 
					</div>
					<div class="txt2">
						<select>
							<option>색깔들</option>
							<option>색깔들</option>
							<option>색깔들</option>				
						</select>
					</div>
					<div class="txt1">
						수량
					</div>
					<div class="txt2">
						<input type="text" name="count"/>
					</div>
				</div>
			</div>
				<div class="bottom">
					<div class="btm1" id="first">
						남은수량/제한수량  
					</div>
					<div class="btm2">
						500/500
					</div>	
					<div class="btm1">
						발송일 : 
					</div>	
					<div class="btm2">
						2019-05-30
					</div>	
				</div>
		</div>
		<div id="nextPage">다음페이지 </div>
	</div>
</div>
</body>
</html>