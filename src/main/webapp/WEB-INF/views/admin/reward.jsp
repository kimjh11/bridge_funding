<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<html>
<head>
<title>리워드 관리 페이지</title>
<!-- 커스텀 CSS -->
<link href="<%=request.getContextPath()%>/css/reward.css" rel="stylesheet"/>
<link rel="styleSheet" href="<%=request.getContextPath() %>/css/adminModule.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="wrap width-auto">
	<div class="admin-update-wrap">
		<h3>리워드 관리</h3>
		<div class="admin-content"></div>
		<c:forEach var="vo" items="${list}">
			<table class='cr-list-table'>
			<tbody>
			<tr>
				<th>
					<div class='cr-list-img'>
						<img class='cr-list-img' src='/bridge/ckstorage/"+data.proImg+"'/>
					</div>
					<div class='cr-list-cat'>${vo.cateName}</div>
					<div class='cr-list-proname'>${vo.proName}</div>
				</th> 	
				<th>
					<div class='cr-list-last'>목표펀딩금액</div>
					<div>${vo.proGoal}</div>
				</th>
				<th>
					<div class='cr-list-pro'>프로젝트기간</div>
					<div>시작일 &nbsp; ${vo.proOpen}</div>
					<div>종료일 &nbsp; ${vo.proEnd}</div>
				</th>
				<th>
					<div class='cr-list-review'>미리보기</div>
				</th>			
			</tr>	
			</tbody> 
		</table>	
		</c:forEach>
	</div>
</div>		
</body>
</html>