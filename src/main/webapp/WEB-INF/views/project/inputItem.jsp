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
					<li class="row1">리워드(상품)등록<br/><span class="ex3">리워드(상품)정보 입력후 임시저장 버튼을 클릭해야 입력한 정보가 저장 됩니다.</span></li>
					<li class="row2">등록상품 미리보기<br/><span class="ex3">입력한 리워드(상품)정보 입니다. 삭제버튼 클릭시 저장한 정보가 지워집니다.</span></li>
				</ul>
			</div>
			<div id="proMid">	
				<form action="/bridge/saveItem" id="frm" method="get">
					<input type="hidden" name="userMail" value="${userMail }"/>
					<input type="hidden" name="proCode" value="${proCode }"/>	
					<div class="row1 inputData">
						<ul style="margin-top:10px">
							<li class="row" >
								<div class="row1_1">리워드명</div>
								<div class="row1_2">
									<input type="text" name="itemName" class="data" placeholder="ex)&nbsp;&nbsp;슈퍼얼리버드 로봇키즈"/>
								</div>
							</li>
							<li class="row li2">
								<div class="row2_1">상세설명</div>
								<div class="row2_2">
									<textarea style="resize:none" name="itemContent" id="itemContent"  placeholder="제공하는 리워드가 무엇인지 간략하게 써주세요.
									ex) 1.구성품(백팩+레인커버+파우치)
									ex) 2.트래블 백팩 2개"></textarea>
								</div>
							</li>
							<li class="row">
								<div class="row3_1">세부옵션</div>
								<div class="row3_2"><input type="text" name="itemOption" class="data" placeholder="ex)&nbsp;&nbsp;색상:빨강/주황/노랑"/></div>
							</li>
							<li class="row">
								<div class="row4_1">상품 순위</div>
								<div class="row4_2"><input type="text" name="itemRank" class="data data2" placeholder="ex)&nbsp;&nbsp;1"/></div>
							</li>
							<li class="row">
								<div class="row5_1">금액</div>
								<div class="row5_2"><input type="text" name="itemPrice" class="data data2" placeholder="ex)&nbsp;&nbsp;26500"/><span class="ex2">원</span></div>
							</li>
							<li class="row">
								<div class="row6_1">제한수량</div>
								<div class="row6_2"><input type="text" name="limitCnt" class="data data2" placeholder="ex)&nbsp;&nbsp;300"/><span class="ex2">개</span></div>
							</li>
							<div class="saveNext itemSave"><a href="#" onclick="document.getElementById('frm').submit();">상품 임시저장</a></div>
						</ul>
					</div>
				</form>	
					<div class="tableDiv row2">
						<table>
							<thead>
								<tr>
									<td class="td1">상품순위</td>
									<td class="td2">상품명</td>
									<td class="td3">상세설명</td>
									<td class="td4">세부옵션</td>
									<td class="td5">상품금액</td>
									<td class="td6">제한수량</td>
									<td class="td7">삭제</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="vo" items="${list }">
									<tr>
										<td class="td1">${vo.itemRank}</td>
										<td class="td2">${vo.itemName }</td>
										<td class="td3">${vo.itemContent }</td>
										<td class="td4">${vo.itemOption }</td>
										<td class="td5">${vo.itemPrice }</td>
										<td class="td6">${vo.limitCnt }</td>
										<td class="td7"><a href="/bridge/delItem?userMail=${vo.userMail }&proCode=${vo.proCode }&itemRank=${vo.itemRank }">x</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="inputData" id="saveDiv">
						<!-- 저장하기 and 다음단계로 -->
						<div class="saveNext"><a href="#">저장하기</a></div>
						
					</div>
				
			</div>
			<!-- <div id="proRight">
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
			</div> -->
		</div>
</body>
</html>