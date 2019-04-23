<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>아이디/비밀번호 찾기</title>
<!-- 커스텀 CSS -->
<link href="<%=request.getContextPath()%>/css/idpwdSearch.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" />
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- idpwdSearch.js -->
<script src="<%=request.getContextPath() %>/js/idpwdSearch.js"></script>
</head>
<body>
<div class="wrap">
	<div class="idpwd-center">
		<!-- 아이디 찾기 -->
		<h2 id="idpwd-h2">가입이력조회</h2>
		<div id="idpwd-div">
			<ul class="idpwd-title">
				<!-- 아이디 찾기 -->
				<li class="hover-pointer on" id="id-title-div">아이디 찾기</li>
				<!-- 비밀번호 찾기 -->
				<li class="hover-pointer" id="pwd-title-div">비밀번호 찾기</li>
			</ul>
			<!-- 아이디 찾기 -->
			<div class="idpwd-content"> <!-- id="id-div" -->
				<div id="id-div">
					<!-- 아이디찾기입력란 -->
					<form method="post" action="/bridge/idSearch">
						<div class="col">
							<span>이메일</span>
							<input type="text" id="user-mail" name="userMail" placeholder="ex)aaaa@naver.com"/>
						</div>
						<input class="hover-pointer" type="submit" value="확인" id="id-btn"/>
						<!-- 아이디찾기 안내멘트 -->
						<c:if test="${vo.userMail != null}" >
							<p id="id-search">
								<span id="warning">${vo.userMail }</span><br/>								
								회원으로 등록된 이메일 아이디입니다.<br/>
								해당 이메일로 로그인하고 브릿지를 이용하세요!
							</p>
						</c:if>
					</form>
				</div>
				<div id="pwd-div" style="display:none">
					<form method="post" action="/bridge/newPwd" id="frm">
						<!-- 이메일입력 -->
						<div class="col">
							<span>이메일</span>
							<input type="text" id="user-mail-pwd" name="userMail" placeholder="ex)aaaa@naver.com"/>
							<p>위 이메일로 인증번호가 발송됩니다.</p>
							<p id="warning"></p>
						</div>
						<div class="col blank">
							<!-- 작성한 인증번호 비교 -->
							<input type="hidden" id="ok-number" placeholder=" 인증번호를 입력해 주세요 :)" />
							<!-- 인증번호 요청 버튼 -->
							<input type="button" value="인증번호 요청" id="id-btn-sending"/>
							<!-- 인증확인 버튼 -->
							<input type="hidden" value="확인" id="joinStart"/>
						</div>
						<div class="col">
							<span>새 비밀번호</span>
							<input type="password" id="user-pwd" name="userPwd" disabled/>
						</div>
						<div class="col">
							<span>새 비밀번호 확인</span>
							<input type="password" id="user-pwdchk" disabled/>
						</div>
						<input type="submit" value="비밀번호 변경" id="pwd-btn"/>
					</form>
				</div>
			</div>
			<!-- 아이디 찾기 -->
			<!-- 비밀번호 찾기 -->
			<div id="pwd-div" >
				
			</div>
			<!-- 비밀번호 찾기 -->
		</div>
	</div>
</div>
</body>
</html>