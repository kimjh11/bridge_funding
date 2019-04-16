<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<html> 
<head>
<title>마이페이지</title>
<!-- 커스텀 CSS -->
<link href="<%=request.getContextPath()%>/css/mypage.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" />
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- join.js -->
<script src="<%=request.getContextPath() %>/js/mypage.js"></script>
</head>
<body>
	<div class="wrap">
			<div> 
				<h2 id="mypage">마이페이지</h2>
			</div>
			<!-- 프로필 편집, 간편결제등록 버튼 -->
			<div id="button">
				<a href="/bridge/profileFormUpdate?userMail=${userMail}"><button id="mypage-profile-edit">프로필 편집</button></a>
				
				<c:if test="${cardName == null || cardName == ''}">
					<button id="mypage-card-ok" >간편결제등록</button>
				</c:if>
				<c:if test="${cardName != null && cardName != ''}">
					<button id="mypage-card-delete" >간편결제삭제</button>
				</c:if>
			</div>	
			
			<!-- 간편결제 등록 div -->
			<div id="card-submit" hidden="hidden">
				<div id="card-x">&Chi;</div>
				<span id="card-span">간편결제 정보 등록</span>
				<hr id="card-hr"/>
				<h2 id="card-h2">결제 정보 입력</h2>
				<form method="post" action="/bridge/cardInfo?userMail=${userMail}">
					<div class="card-div">
						신용(체크)카드번호<br/>
						<input type="text" name="cardNum1" id="cardNum1" class="cardNum" maxlength="4"/>
						<input type="password" name="cardNum2" id="cardNum2" class="cardNum"  maxlength="4"/>
						<input type="text" name="cardNum3" id="cardNum3" class="cardNum"  maxlength="4"/>
						<input type="password" name="cardNum4" id="cardNum4" class="cardNum" maxlength="4"/>
					</div>
					<div class="card-div">
						<div id="card-term">
							유효기간<br/>
							<input type="text" name="cardDate" id="cardDate" placeholder="ex)07/19"/>
						</div>
						<div>
							카드사<br/>
							<select name="cardName" id="cardName">
			  					<option value="카드선택" selected="selected">카드선택</option>
			  					<option value="신한">신한</option>	
			  					<option value="우리">우리</option>
			  					<option value="하나">하나</option>
			  					<option value="현대">현대</option>
			  					<option value="현대">삼성</option>
			  					<option value="현대">그외</option>
							</select>
						</div>
					</div>
					<div class="card-div">
						카드 비밀번호<br/>
						<input type="password" name="cardPwd" id="cardPwd" maxlength="4"/>
					</div>
					<!-- <div class="card-div">
						생년월일 (주민번호 앞 6자리)<br/>
						<input type="text" name="birth" id="birth" placeholder="ex)940901"/>
					</div> -->
					<input type="submit" value="카드등록" id="card-btn"/>
				</form>
			</div>
			<!-- 간편결제 등록 div -->
		
		<div id="mypage-center">	
			<!-- 이미지 -->
			<img id="mypage-img" src="/bridge/upload/${img}" onerror="this.src='<%=request.getContextPath() %>/img/profile/user.png'"/>
			<div id="mypage-username">${userName} 님</div> 	
		</div>
		
		<!-- ajax - 이메일 가져가기 -->
		<input type="hidden" id="userMail" value="${userMail}"/>
		
		<!-- 서포터즈/ 크리에이터 탭 -->
		<div id="mypage-tab">
			<div id="mypage-su">서포터즈</div>
			<div id="mypage-cr">크리에이터</div>
		</div>
		<hr/>
	
		<!-- 서포터즈 - 나의리워드/좋아요 -->
		<div id="mypage-reward">나의 리워드</div>
		<div id="mypage-like">좋아요</div>
		
		<!-- 크리에이트 - 승인 대기중/진행중  -->
		<label id="warning-text"></label>
		<div id="mypage-starting" hidden="hidden">진행중</div>
		<div id="mypage-waiting" hidden="hidden">승인 대기중</div>
		
		<!-- 나의 리워드 -->
		<div id="myReward"></div>
		<!-- 나의 리워드 -->
		
		<!-- 좋아요 -->
		<div id="my-like" hidden="hidden"></div>	
		<!-- 좋아요 -->
	</div><!-- wrap end -->
	<!-- 서포터즈  -->
		
		<!-- 크리에이터  -->
			<!-- 리스트 확인 - 진행중  -->
			<div id="cr-list" hidden="hidden"></div>
			<!-- 리스트 확인 -진행중  -->
			
			<!-- 리스트 확인 - 승인 대기중  -->
			<div id="cr-list-waiting" hidden="hidden"></div>
			<!-- 리스트 확인 -승인 대기중  -->
		<!-- 크리에이터  -->
		
		
		<!-- 프로젝트 코드  -->
		<input type="hidden" id="proCode"/>
		<!-- 좋아요 리스트 확인 -->
		<div id="like-wrap"></div>
		<!-- 좋아요 리스트 확인 -->
		
		<!-- 펀딩 리스트 확인 -->
		<div id="funding-wrap"></div>
		<!-- 펀딩 리스트 확인 -->
</body>
</html>