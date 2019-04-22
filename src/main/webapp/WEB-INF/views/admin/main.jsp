<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bridge 관리자 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="styleSheet" href="<%=request.getContextPath()%>/css/login.css" />
</head>
<body>
	<!-- 로그인 페이지 Div -->
	<div id="adminlogin-div">
		<div id="admin-div">
			<h2 id="adminlogin-h2">관리자 로그인</h2>
			<form method="post" action="/bridge/adminloginOk">
				<input type="text" id="adminlogin-user-mail" name="adminId"
					placeholder="아이디" /> <input type="password"
					id="adminlogin-user-pwd" name="adminPwd" placeholder="비밀번호" /> <label
					id="login-warning"></label> <br />
				<input type="submit" value="로그인" id="adminlogin-btn" />
			</form>
			<div id="login-text">
				BRIDGE<br /> 우리는 모든 창작활동을 응원합니다.<br />
			</div>
		</div>
	</div>
	<!-- 로그인 페이지 Div -->
</body>
</html>