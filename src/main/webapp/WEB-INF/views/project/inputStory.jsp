<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bridge</title>
 
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/inputStory.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.7/angular.min.js"></script>
<script>
	$(function(){
		$("#titleBtn").click(function(){
			var url = $("#proTitle").val();
			alert("정상적인 url 주소인지 체크 합니다.\n"+url);
			
			$("#myFrame").attr("src",url);
		});
	});
</script>
</head>
<body>
		
		<%@include file="projectTitle.jspf"%>
		<div id="proBody">
			<div id="proLeft">
				<ul id="proTitle2">
					<li class="row1">소개영상 등록<br/><br/><br/><span class="ex2">url 입력후 Check 버튼 클릭시 정상적인 url주소면 영상이 나옵니다.</span></li>
					<li class="row2">프로젝트 스토리 <br/><br/><span class="ex2">리워드 클릭시 노출되는 내용으로 필수항목이 누락 되거나 부족할시 오픈 승인이 안될수 있습니다.</span></li>
					<li class="row3">교환/환불 정책 </li>
				</ul>
			</div>
			<div id="proMid">		
				<form action="/bridge/saveStory" id="frm" method="get">
					<input type="hidden" name="userMail" value="${userMail }"/>
					<input type="hidden" name="proCode" value="${proCode }"/>
					<input type="hidden" name="proNum" value="${vo.proNum }"/>		
					<div class="inputData row1">
						<!-- 소개영상 및 사진등록 -->
						<input type="text" name="proTitle" id="proTitle" value="${vo.proTitle }" placeholder="url을 입력해 주세요"/><input type="button" value="urlCheck" id="titleBtn"/>
						<iframe id="myFrame" width="100%" height="80%" src="${vo.proTitle }" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
					</div>
		
					<div class="inputData row2">
					<!-- 스토리 -->
					<textarea id="content" name="proContent">${vo.proContent}
					</textarea>
						<script>
							CKEDITOR.replace('proContent',
											{height:555
											});
						</script>
					</div>
					<div class="inputData row3">
						<!-- 교환환불 정책 -->
						<textarea style="resize:none" name="proRefund" id="proRefund">${vo.proRefund }
						</textarea>
					</div>
					<!-- 저장하기 and 다음단계로 -->
					<div class="saveNext"><a href="#"onclick="document.getElementById('frm').submit();">저장하기</a></div>
					
				</form>	
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
		<div id="preview">
			<a href="/bridge/preview?proCode=${vo.proCode }">미리보기</a><br/>
			입력한 정보를 미리보기를 통하여 보실수 있습니다.<br/>
			입력한 정보를 저장후에 미리보기를 눌러주세요.
		</div>
</body>
</html>

<!-- Q. 배송은 언제 되나요?
A. 소셜커머스, 오픈마켓과 달리 *월 *일까지 펀딩이 진행되고 프로젝트 성공하면 결제 기간을 거쳐 리워드 제작이 진행됩니다. 프로젝트 성공 시, *월 *일부터 *월 *일까지 17시마다 결제가 실행됩니다. (주말/공휴일 제외, 총 4영업일) 잔고 부족, 한도 초과, 분실/정지카드 등의 사유로 결제 실패하실 수 있으며 결제 실패하신 경우, 최종 결제일인 *월 *일 오후 4시 30분 전까지 마이페이지에서 다른 카드로 변경하실 수 있습니다.

Q. 펀딩 후, 옵션/배송지/카드 정보 변경은 어떻게 해야하나요?
A. 펀딩 마감일 전까지 '펀딩내역' 페이지에서 수정하실 수 있습니다. 펀딩 마감 이후, 불가피한 사유로 배송지 변경하셔야 하는 분은 해당 페이지 내 '메이커에게 문의하기'를 통해서 문의해 주세요.

Q. 교환/환불/AS 정책은 어떻게 되나요?
A. 프로젝트 마감일 전까지는 '펀딩내역' 페이지에서 펀딩 취소 가능하지만 마감일 이후에는 취소가 불가능합니다. 해당 페이지의 상단 '펀딩정보' 탭에서 교환/환불/AS정책을 꼼꼼하게 확인하시고 펀딩해 주세요.											
				 -->		
						<!-- 아래 안내드리는 스토리 필수항목이 누락되거나 충분하지 않은 경우, 심사나 오픈 승인이 되지 않을 수 있습니다. (필수항목의 순서는 상관 없으며 프로젝트에 맞게 재배치해도 무방합니다.<br/>
						<br/>
						1. 메이커 소개 (필수 : 실명 소개 및 실물 사진)<br/>
						<br/>
						2. 와디즈에서 펀딩하시는 이유 및 후원금 사용 계획<br/>
						<br/>
						3. 리워드 상세 설명 (필수: 실물 사진, 스펙 등)<br/>
						<br/>
						4. 리워드 리스트 (필수: 가격, 제공되는 리워드)<br/>
						<br/>
						5. 프로젝트 일정 (필수: 펀딩 마감일, 향후 일정 포함)<br/>
						<br/>
						1) 현재 : 프로젝트 시작<br/>
						2) *월 *일 : 프로젝트 마감(이 날까지만 펀딩 참여 및 마이페이지에서 펀딩 취소가 가능합니다)<br/>
						3) *월 *일 : 리워드 제작 시작<br/>
						4) *월 *일 : 리워드 발송 시작<br/>
						<br/>
						6. 발송 안내 (필수: 리워드 발송 방법, 발송 시작일, 순차 배송인 경우 1일 최대 발송 수량, 리워드 발송 관련 메이커님 연락처)<br/>
						<br/>
						1) 리워드는 **택배사를 통해 발송됩니다. (리워드 발송 방법 : 택배/SMS/메일/우편/등기)<br/>
						<br/>
						2) *월 *일부터 하루에 ***개씩 펀딩순으로 순차 발송됩니다. 배송 소식은 새소식을 통해 내용 공유해 드리겠습니다.<br/>
						<br/>
						3) 가능한 리워드/배송 관련 문의는 ****(예 : 카카오플러스친구, 해당 페이지 내 '메이커에게 문의하기', 전화번호, 이메일 중 택1)로 해 주시면, 가장 빠르게 답변드릴 수 있습니다.<br/>
						<br/>
						4) (도서/산간지방도 배송 가능하신 경우 작성) 도서/산간지방의 경우, 펀딩 시 추가 후원금 입력하기란에 ****원을 입력해 주세요.<br/>
						<br/>
						7.FAQ<br/>
						<br/>
						Q. 배송은 언제 되나요?<br/>
						A. 소셜커머스, 오픈마켓과 달리 *월 *일까지 펀딩이 진행되고 프로젝트 성공하면 결제 기간을 거쳐 리워드 제작이 진행됩니다. 프로젝트 성공 시, *월 *일부터 *월 *일까지 17시마다 결제가 실행됩니다. (주말/공휴일 제외, 총 4영업일) 잔고 부족, 한도 초과, 분실/정지카드 등의 사유로 결제 실패하실 수 있으며 결제 실패하신 경우, 최종 결제일인 *월 *일 오후 4시 30분 전까지 마이페이지에서 다른 카드로 변경하실 수 있습니다.<br/>
						<br/>
						Q. 펀딩 후, 옵션/배송지/카드 정보 변경은 어떻게 해야하나요?<br/>
						A. 펀딩 마감일 전까지 '펀딩내역' 페이지에서 수정하실 수 있습니다. 펀딩 마감 이후, 불가피한 사유로 배송지 변경하셔야 하는 분은 해당 페이지 내 '메이커에게 문의하기'를 통해서 문의해 주세요.<br/>
						<br/>
						Q. 교환/환불/AS 정책은 어떻게 되나요?<br/>
						A. 프로젝트 마감일 전까지는 '펀딩내역' 페이지에서 펀딩 취소 가능하지만 마감일 이후에는 취소가 불가능합니다. 해당 페이지의 상단 '펀딩정보' 탭에서 교환/환불/AS정책을 꼼꼼하게 확인하시고 펀딩해 주세요.<br/>													
					 -->