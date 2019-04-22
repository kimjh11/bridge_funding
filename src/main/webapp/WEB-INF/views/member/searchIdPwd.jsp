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
			<div class="idpwd-center">
				<!-- 아이디 찾기 -->
				<h2 id="idpwd-h2">가입이력조회</h2>
				<div id="idpwd-div">
					<!-- 아이디 찾기 -->
					<div id="id-title-div">아이디 찾기</div>
					<!-- 비밀번호 찾기 -->
					<div id="pwd-title-div">비밀번호 찾기</div>
					
					<!-- 아이디 찾기 -->
					<div id="id-div">
						<div id="id-text">
							<form method="post" action="/bridge/idSearch">
								<c:if test="${vo.userMail != null}" >
									<p id="id-search">
										<span id="id-search-color">${vo.userMail }</span><br/>									
										회원으로 등록된 이메일 아이디입니다.
										해당 이메일로 로그인하고 브릿지를 이용하세요!
									</p>
								</c:if>
								이메일 <input type="text" id="user-mail" name="userMail" placeholder="ex)aaaa@naver.com"/>
								<input type="submit" value="확인" id="id-btn"/>
							</form>
						</div>
					</div>
					<!-- 아이디 찾기 -->
					<!-- 비밀번호 찾기 -->
					<div id="pwd-div" hidden="hidden">
						<div id="pwd-text">
							<form method="post" action="/bridge/newPwd" id="frm">
								이메일 <input type="text" id="user-mail-pwd" name="userMail" placeholder="ex)aaaa@naver.com"/>
								<div id="text-div">
									위 이메일로 인증번호가 발송됩니다. 
								</div>
								<label id="warning"></label>
								<!-- 작성한 인증번호 비교 -->
								<input type="hidden" id="ok-number" placeholder="인증번호를 입력해 주세요 :)" /><br/>
								
								<!-- 인증번호 요청 버튼 -->
								<input type="button" value="인증번호 요청" id="id-btn-sending"/><br/>
								<!-- 인증확인 버튼 -->
								<input type="hidden" value="확인" id="joinStart"/><br/>
								
								새 비밀번호 <input type="password" id="user-pwd" name="userPwd" disabled/><br/>
								새 비밀번호 확인 <input type="password" id="user-pwdchk" disabled/>
								<input type="submit" value="비밀번호 변경" id="pwd-btn"/>
							</form>
						</div>
					</div>
					<!-- 비밀번호 찾기 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>