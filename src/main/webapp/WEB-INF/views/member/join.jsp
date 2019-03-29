<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<html>
<head>
<title>회원가입</title>
<!-- 커스텀 CSS -->
<link href="<%=request.getContextPath()%>/css/join.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" />
<!-- join.js -->
<script src="<%=request.getContextPath() %>/js/join.js"></script>
</head>
<body>
	<div class="wrap">
		<h1 id="joinh1">회원가입</h1>
		<form method="post" action="${pageContext.request.contextPath}/member/mailSending">
			<div class="joindiv">
				이메일  <input type="text" id="userEmail" name="userEmail" class="jointext" placeholder="ex)aaaa@naver.com"/> 		
				<div id="textdiv">
					위 이메일로 인증번호가 발송됩니다. 
				</div>
				<label id="warningid"></label>
			</div>
			<div>
				<input type="hidden" id="okNumber" placeholder="인증번호"/>
			</div>
			<div class="joindiv">
				<input type="button" value="인증하기" id="emailOk" class="joinbtn"/>
			</div>
		</form>
		<form method="post" action="">	
			<div class="joindiv">
				이름  <input type="text" id="userName" name="userName"  class="jointext" placeholder="ex)홍길동" disabled/>
			</div>
			<div class="joindiv">
				비밀번호  <input type="password" id="userPwd" name="userPwd"  class="jointext" disabled/>
			</div>
			<div class="joindiv">
				비밀번호 확인  <input type="password" id="userPwdChk" name="userPwdChk"  class="jointext" disabled/>
			</div>
			<div class="joindiv">
				<input type="button" value="가입하기" class="joinbtn"/>
			</div>
		</form>
	</div>
</body>
</html>