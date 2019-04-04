<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 상세 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="/bridge/css/deTailPage.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>
<script type="text/javascript" src="/bridge/js/deTailPage.js"></script>
</head> 
<body>
<div class="wrap">
<div id="border">
	<div id="topback"></div>
</div>
	<div id="topTap"> <!-- 맨위 타이틀 div -->
		<ruby>	
			<span>우리아이 창의력 뿜뿜! 직접조립하고 리모콘으로 조종하는 블록로봇</span>
			<rt>카테코리</rt>
		</ruby>	
		<table id="tap"> <!-- 나중에 ajax를 이용한 탭 만들기 -->
			<tr>
				<td data-tab="deTailOpen" class="selectTap taps">스토리</td>
				<td data-tab="deTailGuide" class="taps">펀딩안내</td>
				<td data-tab="deTailReply" class="taps">댓글</td>
				<td data-tab="deTailSpter" class="taps">서포터</td>
			</tr>
		</table>
	</div>

	<div id="left" class="marginleft">
				<div id="thumbnail"> <!-- 타이틀로 걸어 놓은 이미지나 동영상 -->
			<div id="btns">	
				<img src="/bridge/img/abocado.gif"/>
			</div>
		</div>
		<div id="company"> <!-- 프로젝트를 발행한 회사정보 -->
				<div id="comimg">
					<p>회사정보</p>
					<img src="/bridge/img/abocado.gif"/>				
				</div>
			<div id="comTitle">
				<div id="line"> 
					(주)아보카도<br/>
					아보카도는 무슨맛이 날까??	
				</div>
			</div> 
		</div>
		<div id="deTail">
			여기서부터 상세 설명 <br/>
			이미지도 들어가고 영상도 들어가고 블라블라
		</div>
	</div>
	<div id="right" class="marginright">
		<div class="real" id="firstblk">
		</div>
		<div class="camgo topMargin" id="second">
			<h5>참고사항!</h5>
				<ul>
					<li>지금 펀딩하기 고민된다면 좋아요를 눌러보세요.<br/>
						마이페이지에서 쉽게 확인할 수 있습니다.
					</li>
					<li>100% 이상이 모여야 성공되는 프로젝트 마감일까지 
						목표금액을 달성하지 못하면 결제가 진행되지 않습니다.
					</li>
				</ul>
		</div>
		<div id="optionStr" class="topMargin">
			<span>리워드 선택</span>
			<hr/>
			<div class="option">
				<div class="left">펀딩금액</div>
				<div class="right">???,???원</div>
				<div id="text">슈퍼얼리버드 더 와이즈 로봇키즈<br/>
					더와이즈로봇키즈 1세트<br/>
					(구성품:로봇교구세트1세트+워크북12권+보호자용 지도서 1권)
				</div>
				<div class="left">배송비</div>
				<div class="right">???,???원</div>
				<div class="left">발송 시작일</div>
				<div class="right">2019.05월 말(20~말일)예정</div>
				<div class="left">제한수량 / 현재수량</div>
				<div class="right">?? / ??</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>