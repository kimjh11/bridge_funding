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
			<div id="button">
				<a href="/bridge/profileFormUpdate?userMail=${userMail}"><button id="mypage-profile-edit">프로필 편집</button></a>
				<button id="mypage-card-ok">간편결제등록</button>
			</div>	
			
			<!-- 간편결제 등록 div -->
			<div id="card-submit" hidden="hidden">
				<div id="card-x">&Chi;</div>
				<span id="card-span">간편결제 정보 등록</span>
				<hr id="card-hr"/>
				<h2 id="card-h2">결제 정보 입력</h2>
				<form method="post" action="">
					<div class="card-div">
						신용(체크)카드번호<br/>
						<input type="text" name="cardNum" class="cardNum"/>
						<input type="password" name="cardNum" class="cardNum"/>
						<input type="text" name="cardNum" class="cardNum"/>
						<input type="password" name="cardNum" class="cardNum"/>
					</div>
					<div class="card-div">
						<div id="card-term">
							유효기간<br/>
							<input type="text" name="cardDate" id="cardDate"/>
						</div>
						<div>
							카드사<br/>
							<select name="cardName" id="cardName">
			  					<option value="신한" selected="selected">신한</option>
			  					<option value="우리">우리</option>
			  					<option value="하나">하나</option>
			  					<option value="현대">현대</option>
							</select>
						</div>
					</div>
					<div class="card-div">
						카드 비밀번호<br/>
						<input type="password" name="cardPwd" id="cardPwd"/>
					</div>
					<div class="card-div">
						생년월일 (주민번호 앞 6자리)<br/>
						<input type="text" name="birth" id="birth"/>
					</div>
					<input type="submit" value="카드등록" id="card-btn"/>
				</form>
			</div>
			<!-- 간편결제 등록 div -->
		
		<div id="mypage-center">	
			<!-- 이미지 -->
			<img id="mypage-img" src="/bridge/upload/${img}" onerror="this.src='<%=request.getContextPath() %>/img/profile/user.png'"/>
			<!-- <div id="mypage-img"></div> -->
			<div id="mypage-username">${userName} 님</div> 	
		</div>
		<div id="mypage-tab">
			<div id="mypage-su">서포터즈</div>
			<div id="mypage-cr">크리에이터</div>
		</div>
		<hr/>
		
		<!-- 서포터즈  -->
		<div id="mypage-reward">나의 리워드</div>
		<div id="mypage-like">좋아요</div>
		
		<!-- 나의 리워드 -->
		<div id="myReward">
			<div class="list-content">
				<ul class="project-view">
					<c:forEach var="vo" begin="1" end="5">
						<li>
							<span class="cate-txt">카테고리명</span>
							<img alt="상품이미지" src="<%=request.getContextPath()%>/img/reward/listThumnail.jpg">
							<h4>프로젝트명</h4>
							<button class="like-btn">좋아요</button>
							<ul class="detail-info">
								<li class="col1">
									<span>목표달성</span>
									<strong>값</strong>
								</li>
								<li class="col2">
									<span>판매금액</span>
									<strong>값</strong>
								</li>
								<li class="col3">
									<span>종료일</span>
									<strong>값</strong>
								</li>
							</ul>
						</li>	
					</c:forEach>
				</ul>
			</div>	
		</div>
		<!-- 나의 리워드 -->
		
		
		<!-- 좋아요 -->
		<div id="my-like" hidden="hidden">
			<div class="list-content">
				<ul class="project-view">
					<c:forEach var="vo" begin="1" end="4">
						<li>
							<span class="cate-txt">카테고리명</span>
							<img alt="상품이미지" src="<%=request.getContextPath()%>/img/reward/listThumnail.jpg">
							<h4>프로젝트명</h4>
							<button class="like-btn">좋아요</button>
							<ul class="detail-info">
								<li class="col1">
									<span>목표달성</span>
									<strong>값</strong>
								</li>
								<li class="col2">
									<span>판매금액</span>
									<strong>값</strong>
								</li>
								<li class="col3">
									<span>종료일</span>
									<strong>값</strong>
								</li>
							</ul>
						</li>	
					</c:forEach>
				</ul>
			</div>	
		</div>
		<!-- 좋아요 -->
		<!-- 서포터즈  -->
		
		<!-- 크리에이터  -->
			<!-- 리스트 확인  -->
			<div id="cr-list" hidden="hidden">
			 	<table class="cr-list-table">
				 	<tbody>
				 		<tr>
				 			<th>
				 				<div id="cr-list-img"></div>
				 				<div id="cr-list-cat">카테고리</div>
				 				<div>프로젝트명</div>
				 			</th>
				 			<th>
				 				<div id="cr-list-last">최종펀딩금액</div>
				 				<div id="cr-list-goal">(목표금액: 1,000,000 원)</div>
				 				<div>2,000,000원</div>
				 			</th>
				 			<th>
				 				<div id="cr-list-pro">프로젝트기간</div>
				 				<div>시작일 &nbsp; 2019.01.01</div>
				 				<div>종료일 &nbsp; 2019.01.30</div>
				 			</th>
				 			<th><div id="cr-list-like">좋아요<br/>리스트 확인</div></th>
				 			<th><div id="cr-list-funding">펀딩<br/>리스트 확인</div></th>
				 		</tr>
				 	</tbody>
			 	</table>
			</div>
			<!-- 리스트 확인  -->
		<!-- 크리에이터  -->
		
		<!-- 좋아요 리스트 확인 -->
		<div id="like-wrap">
			<div id="like-list-div" hidden="hidden">
				<div id="like-card-x">&Chi;</div>
				<hr/>
				<c:forEach var="vo" begin="1" end="4">
					<!-- 이미지 -->
					<img id="like-list" src="/bridge/upload/${img}" onerror="this.src='<%=request.getContextPath() %>/img/profile/user.png'"/>
					<div id="like-text">김준호님이 좋아요를 눌렀습니다.</div>
					<hr/>
				</c:forEach>
			</div>
		</div>
		<!-- 좋아요 리스트 확인 -->
		
		<!-- 펀딩 리스트 확인 -->
			<div id="funding-wrap">
				<div id="funding-list-div" hidden="hidden">
					<div id="funding-card-x">&Chi;</div>
					<hr/>
					<c:forEach var="vo" begin="1" end="4">
						<!-- 이미지 -->
						<img id="funding-list" src="/bridge/upload/${img}" onerror="this.src='<%=request.getContextPath() %>/img/profile/user.png'"/>
						<div id="funding-text">김준호님이 펀딩하였습니다.</div>
						<hr/>
					</c:forEach>
				</div>
			</div>
		<!-- 펀딩 리스트 확인 -->
		
	</div>
</body>
</html>