<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<title>Bridge</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/inputProOk.css"/>
</head>
<body>
		
		<%@include file="projectTitle.jspf"%>
		<div id="proBody">
			<div id="proLeft">
				<ul id="proTitle">
					<li class="row1">오픈예정<br/><span class="ex3">버튼 클릭하지 않을시 오픈예정 미신청 으로 등록 됩니다.</span></li>
					<li class="row2">수수료<br/><span class="ex3">수수료안내 동의 체크 하셔야 검토요청 가능합니다.</span></li>
				</ul>
			</div>
			<div id="proMid">	
				<form action="/bridge/saveProOk" id="frm" method="get">		
					<input type="hidden" name="userMail" value="${userMail }"/>
					<input type="hidden" name="proCode" value="${proCode }"/>
					<input type="hidden" name="proNum" value="${vo.proNum }"/>	
					<div class="inputData row">
						<ul>
							<li class="row1">
								<div class="row1_1"><input type="radio" name="proWait" value="0" <c:if test="${vo.proWait == 0 }">checked</c:if>/>
									<span class="ex2">오픈예정 미신청<br/>
										<span class="ex3">오픈예정 미신청 시 검토요청 승인이후 1일 지나 바로 해당 카테고리에 노출 됩니다.</span>
									</span>
								</div>
								<div class="row2_1"><input type="radio" name="proWait" value="7" <c:if test="${vo.proWait == 7 }">checked</c:if>/>
									<span class="ex2">
										오픈예정 신청<br/>
										<span class="ex3">검토승인일 부터 7일간 오픈예정 카테고리에 상품이 노출 됩니다.<br/>
										ex) 승인일 1/1일 경우 1/2일부터 1/8일까지 오픈예정 카테고리에 노출 1/9일 해당 카테고리 페이지에 노출.</span>
									</span>
								</div>
							</li>
							<li class="row2">
								<div class="row2_1">
									<span class="ex2">
										펀딩 오픈 수수료는 10%(VAT별도)입니다.<br/>
										<input type="checkbox"id="agree"/>
										<span class="ex3">
											브릿지(BRIDGE)의 수수료 정책을 확인 했으며, 이에 동의 합니다.
										</span>
									</span>
								</div>
							</li>
						</ul>
					</div>
					<div class="inputData" id="saveDiv">
						<!-- 저장하기 and 다음단계로 -->
						<script>
						function check(){
							var agree = document.getElementById('agree').checked;
							alert(agree);
							if(agree == true){
								
							}else{
								alert("수수료안내 문구를 동의하시지 않앗습니다.");
							}
							
						}
						</script>
						<div class="saveNext"><a href="#"onclick="document.getElementById('frm').submit();">저장하기</a></div>
						<div class="saveNext" style="border-bottom:1px solid #666"><a href="javascript:check();">검토요청</a></div>
					</div>
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