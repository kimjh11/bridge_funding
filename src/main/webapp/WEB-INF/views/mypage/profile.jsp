<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<html>
<head>
<title>프로필</title>
<!-- 커스텀 CSS -->
<link href="<%=request.getContextPath()%>/css/profile.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" />
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- join.js -->
<script src="<%=request.getContextPath() %>/js/profile.js"></script>
</head>
<body>
	<div class="wrap">
		<div id="profile-center">
			<h2 id="profile-h2">프로필 설정</h2>
			<div>
				<!-- 이미지 -->
				<div id="profile-img"></div>
				<input type="button" value="이미지 등록" id="img-btn"/>	
			</div>
			<div>
				<form>
					<div class="profile-div">
						이름<input type="text" name="userName" id="userName" class="profile-text" readonly/>
					</div>
					<div class="profile-div">
						이메일<input type="text" name="userMail" id="userMail" class="profile-text" readonly/>
					</div>
					<div class="profile-div">
						연락처<input type="text" name="userTel" id="userTel" class="profile-text" placeholder="ex)010-1234-1234"/>
					</div>
					<div class="profile-div">
						생년월일  <select name="birth" class="birth-select">
  									<option value="년도" selected="selected">년도</option>
  									<option value="1950">1950</option>
  									<option value="1951" >1951</option>
								</select>
								<select name="birth" class="birth-select">
  									<option value="월" selected="selected">월</option>
  									<option value="11">11</option>
  									<option value="12">12</option>
								</select>
								<select name="birth" class="birth-select">
  									<option value="일" selected="selected">일</option>
  									<option value="29">29</option>
  									<option value="30">30</option>
								</select>
					</div>
					<div class="profile-div">
						<div>
							우편번호 <input type="text" name="zipcode" id="zipcode" class="profile-text"/>
							<input type="button" value="우편번호" id="zipcode-btn"/>
						</div>
						<div>
							기본주소 <input type="text" name="addr" id="addr" class="profile-text"/>
						</div>
						<div>	
							상세주소 <input type="text" name="addrdetail" id="addrdetail" class="profile-text"/>
						</div>
						<div>
							참고항목<input type="text" name="addrSearch" id="addrSearch" class="profile-text"/> 
						</div>
					</div>
					<input type="button" value="취소" id="cancel-btn"/>
					<input  type="submit" value="확인" id="ok-btn"/>
				</form>
			</div>
		</div>
	</div>
</body>
</html>