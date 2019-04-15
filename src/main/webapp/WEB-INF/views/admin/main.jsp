<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<c:if test="${cnt>0 }">
	<script>  
		alert('등록완료!');
	</script>
</c:if>
<form action="<%=request.getContextPath() %>/bannerSubmit" method="post" id="bannerSubmitFrm" enctype="multipart/form-data">
	<ul>
		<li>
			<span>페이지</span>
			<select>
				<option>선택하세요</option>
				<option>리워드홈</option>
				<option>오픈예정</option>
				<option>기부와후원</option>
			</select>
			<input type="hidden" name="pageName" />
			<script>
				$(function(){
					$('#bannerSubmitFrm select').change(function(){
						$('input[name="pageName"]').val(this.value);
						console.log(this.value);
					});
				});
			</script>
		</li>
		<li>
			<span>제목</span>
			<input type="text" name="bannerTitle" />
		</li>
		<li>
			<span>설명</span>
			<input type="text" name="bannerSubTitle" />
		</li>
		<li>
			<span>이미지</span>
			<input type="file" name="bannerImgFile" />
		</li>
		<li>
			<input type="submit" value="등록" />
		</li>
	</ul>
</form>

</body>
</html>