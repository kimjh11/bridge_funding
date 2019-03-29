<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 상세 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="/bridge/css/deTailPage.css">
<script type="text/javascript" src="/bridge/js/deTailPage.js"></script>
</head>
<body>
<div class="wrap">
		<div id="topTap"> <!-- 맨위 타이틀 div -->
			<ruby>	
				<span>우리아이 창의력 뿜뿜! 직접조립하고 리모콘으로 조종하는 블록로봇</span>
				<rt>카테코리</rt>
			</ruby>	
		</div>
		<table id="tap"> <!-- 나중에 ajax를 이용한 탭 만들기 -->
			<tr>
				<td class="selectTap">스토리</td>
				<td>펀딩안내</td>
				<td>댓글</td>
				<td>서포터</td>
			</tr>
		</table>
	<div id="left" class="marginTop5">
		<div id="thumbnail"> <!-- 타이틀로 걸어 놓은 이미지나 동영상 -->
			이미지/동영상
		</div>
		<div id="company"> <!-- 프로젝트를 발행한 회사정보 -->
			<div id="comTitle">
				<div id="comimg">
					<img src="/bridge/img/abocado.gif"/>				
				</div>
				<div id="line"> 
					(주)아보카도<br/>
					gkdkkfkg
				</div>
			</div>
		</div>
	</div>
	<div id="right" class="marginTop5">
		<div class="real">
			마하반야바라밀다심경
		</div>
		<div class="real">
			이렇게라도 하지 않으면
		</div>
	</div>
</div>
</body>
</html>