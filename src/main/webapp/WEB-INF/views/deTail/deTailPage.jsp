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
	<div id="topback">
		<img src="<%=request.getContextPath() %>/ckstorage/${vo.proImg }"/>
	</div>
</div>
	<div id="topTap"> <!-- 맨위 타이틀 div -->
		<ruby>	
			<span>${vo.proName }</span>
			<rt>${vo.cateName }</rt>
		</ruby>	
		<table id="tap"> <!-- 나중에 ajax를 이용한 탭 만들기 -->
			<tr>
				<td data-tab="deTailOpen?cateCode=${vo.cateCode}&proCode=${vo.proCode}" class="selectTap taps">스토리</td>
				<td data-tab="deTailGuide?cateCode=${vo.cateCode}&proCode=${vo.proCode}" class="taps">펀딩안내</td>
				<td data-tab="deTailReply?cateCode=${vo.cateCode}&proCode=${vo.proCode}" class="taps">댓글</td>
				<td data-tab="deTailSpter?cateCode=${vo.cateCode}&proCode=${vo.proCode}" class="taps">서포터</td>
			</tr>
		</table>
	</div>

	<div id="left" class="marginleft">
		<div id="thumbnail"> <!-- 타이틀로 걸어 놓은 이미지나 동영상 -->
			<iframe id="myFrame" width="100%" height="100%" src="${vo.proTitle }" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div>
		<div id="company"> <!-- 프로젝트를 발행한 회사정보 -->
				<div id="comimg">
					<p>회사정보</p>
					<img src="<%=request.getContextPath() %>/ckstorage/${vo.comImg}"/>
				</div>
			<div id="comTitle">
				<div id="line"> 
				${vo.comName}<br/>
				<span>이메일 : ${vo.comEmail}</span><br/>
				<span>전화번호 : ${vo.comTel }</span><br/>
				
				</div>
			</div> 
		</div>
		<div id="deTail">
			${vo.proContent}
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
		<c:forEach var="item" items="${list}">
			<div id="optionStr" class="topMargin">
				<span>리워드 선택</span>
				<hr/>
				<div class="option">
					<div class="left">펀딩금액</div>
					<div class="right">${item.itemPrice}원</div>
					<div id="text">${item.itemName}<br/>
						${item.itemContent}<br/>
						${item.itemOption}
					</div>
					<div class="left">발송 시작일</div>
					<div class="right">2019.05월 말(20~말일)예정</div>
					<div class="left">제한수량 / 현재수량</div>
					<div class="right">${item.limitCnt } / ${item.stateCnt }</div>
				</div>
			</div>
		</c:forEach>	
	</div>
</div>
</body>
</html>