<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head> 
<title>회원가입</title>
<!-- 커스텀 CSS -->
<link href="<%=request.getContextPath()%>/css/join.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" />
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- join.js -->
<script src="<%=request.getContextPath() %>/js/join.js"></script>
</head> 
<body>
	<div class="wrap">
		<div id="join-center">
			<h1 id="join-h1">회원가입</h1>
			
			<!-- =================이메일 인증======================== -->
			<form method="post" action="/bridge/insertMember" id="frm">
				<div class="join-div">
					<span>이메일</span>
					<input type="text" id="user-email" name="userMail" class="join-text" placeholder="ex)aaaa@naver.com"/> 		
					<label id="text-div"> 위 이메일로 인증번호가 발송됩니다. </label>
					<label id="warning-id"></label>
				</div>
				<div>
					<!-- 작성한 인증번호 비교 -->
					<input type="hidden" id="ok-number" placeholder="인증번호를 입력해 주세요 :)" />
				</div>
				<!-- 인증번호 요청 버튼 -->
				<div class="join-div">
					<input type="button" value="인증번호 요청" id="email-Ok" class="join-btn"/>
				</div>
				<!-- 인증확인 버튼 -->
				<div class="join-div">
					<input type="hidden" value="인증번호 확인" id="joinStart" class="join-btn" />
				</div>
				<label id="email-ok-label"></label>
			
			<!-- =================이메일 인증======================== -->
			
			<!-- ===================이름, 비밀번호======================  -->
			
				<div class="join-div">
					<span>이름</span>
					<input type="text" id="user-name" name="userName"  class="join-text" placeholder="ex)홍길동" disabled/>
				</div>
				<div class="join-div">
					<span>비밀번호</span>
					<input type="password" id="user-pwd" name="userPwd"  class="join-text" disabled/>
				</div>
				<div class="join-div">
					<span>비밀번호 확인</span>
					<input type="password" id="user-pwdchk" name="userPwdChk"  class="join-text" disabled/>
				</div>
				<div class="join-div">
					<input type="submit" value="가입하기" id="join" class="join-btn"/>
				</div>
			</form>
			<!-- ===================이름, 비밀번호======================  -->
			
		</div>
	</div>
</body>
</html>