<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<title>Bridge</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/inputItem.css"/>
</head>
<body>
		
		<%@include file="projectTitle.jspf"%>
		<div id="proBody">
			<div id="proLeft">
				<ul id="proTitle">
					<li class="row1">금액</li>
					<li class="row2">리워드명</li>
					<li class="row3">상세설명</li>
					<li class="row4">세부옵션</li>
					<li class="row5">배송료</li>
					<li class="row6">제한수량</li>
					<li class="row7">발송시작일</li>
				</ul>
			</div>
			<div id="proMid">		
					<form>				
						<div class="inputData row1">
							<!-- 리워드가격 -->
							<input type="text"/>원
						</div>
						<div class="inputData row2">
							<!-- 리워드 명 -->
							<input type="text"/>
						</div>	
						<div class="inputData row3">
							<!-- 리워드설명 -->
							<input type="text"/>
						</div>
						
						<!-- 저장하기 and 다음단계로 -->
						<div class="saveNext"><a href="#">저장하기</a></div>
						<div class="saveNext" style="border-bottom:1px solid #666"><a href="#">다음단계로 ></a></div>
			
				</form>	
			</div>
			<div id="proRight">
				<ul>
					<li>
						
						<a href="#" style="color:#000066">펀딩 오픈 가이드</a><br/>
						<span class="ex">내 펀딩 오픈하기 가이드 영상 입니다.</span>
						
					</li>
					<li>
						오픈까지 일정 확인하기<br/>
						<span class="ex">검토요청 이후 오픈까지 평균 2주가 걸립니다.</span>
					</li>
					<li>
						수정 가능 항목 확인하기<br/>
						<span class="ex">모든 내용은 심사 통과 전까지 수정이 가능합니다.</span>
					</li>
					<li>
						입력한 정보를 저장하기<br/>
						<span class="ex">입력하신 정보를 저장하기 누르지 않고 다음으로 넘어가면 정보가 저장되지 않습니다.</span>
					</li>
				</ul>
			</div>
		</div>
</body>
</html>