<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>프로젝트 미리보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="/bridge/css/deTailPage.css"/>
<script type="text/javascript" src="/bridge/js/admindeTailReView.js"></script>
</head> 
<body>
	<div class="wrap">
		<!-- 컨트롤러로 가져가기  -->
		<div hidden="hidden">${vo2.proCode}</div>
		<div hidden="hidden">${vo2.proDate}</div>
		<div hidden="hidden">${vo2.proWait}</div>
		<div hidden="hidden">${vo2.cateCode}</div>
		<!-- 컨트롤러로 가져가기  -->
		<%-- <div id="border">
			<div id="topback">${vo2.proImg }</div>
		</div> --%>
		<div>
			<a href="/bridge/projectOk?proCode=${vo2.proCode}&proDate=${vo2.proDate}&proWait=${vo2.proWait}
					 &cateCode=${vo2.cateCode}"><button id="ReViewOk">승인하기</button></a>
		</div>
		<!-- 맨위 타이틀 div -->
		<%-- <div id="topTap"> 
		<ruby>	
			<span>${vo2.proName }</span>
			<rt>${vo2.cateName }</rt>
		</ruby> --%>
		<div id="topTap"> <!-- 맨위 타이틀 div -->
			<div id="border">
				<div id="topback">
					<img src="<%=request.getContextPath() %>/ckstorage/${vo2.proImg }"/>
				</div>
				<ruby>	
					<span>${vo2.proName }</span>
					<rt>${vo2.cateName }</rt>
				</ruby>
			</div>	
		</div>
		
		
		
		<!-- 나중에 ajax를 이용한 탭 만들기 -->	
		<table id="tap"> 
			<tr>
				<td data-tab="deTailOpenReView?cateCode=${vo2.cateCode}&proCode=${vo2.proCode}" class="selectTap taps">스토리</td>
				<td data-tab="deTailGuideReView?cateCode=${vo2.cateCode}&proCode=${vo2.proCode}" class="taps">펀딩안내</td>
			</tr>
		</table>


	<div id="left" class="marginleft">
		<div id="thumbnail"> <!-- 타이틀로 걸어 놓은 이미지나 동영상 -->
			<iframe id="myFrame" width="100%" height="80%" src="${vo2.proTitle }" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div>
		<div id="company"> <!-- 프로젝트를 발행한 회사정보 -->
			<div id="comimg">
				<p>회사정보</p>
				<img src="/bridge/upload/${vo2.comImg }" onerror="this.src='<%=request.getContextPath() %>/img/profile/user.png'"/>
			</div>
			<div id="comTitle">
				<div id="line"> 
					${vo2.comName}<br/>
					<span>이메일 : ${vo2.comEmail}</span><br/>
					<span>전화번호 : ${vo2.comTel }</span><br/>
				</div>
			</div> 
		</div>
		<div id="deTail">
			${vo2.proContent}
		</div>
	</div>
	
	<div id="right" class="marginright">
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
		
		<!-- 아이템 출력 -->
		<c:forEach var="item" items="${listItem}">
			<div id="optionStr" class="topMargin">
				<span>리워드 선택</span>
				<hr/>
				<div class="option">
					<div class="left">펀딩금액</div>
					<div class="right">${item.itemPrice}원</div>
					<div id="text">
						${item.itemName}<br/>
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