<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bridge</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/inputProject.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	$(document).ready(function(){

		$('#proCategory div a').removeClass('on');
		$('#proCat1 a').addClass('on');
	})
	
});
</script>
</head>
<body>
<div class="wrap project">
	<!-- 상단 네비 -->
	<%@include file="projectTitle.jspf"%>
	<!-- 미리보기 이용탭 -->
	<%@ include file="preview_nav.jspf" %>
	<div id="proBody" class="project">
		<form action="/bridge/savePro" name="frm" id="frm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="userMail" value="${userMail }"/>
			<input type="hidden" name="proCode" value="${vo.proCode }"/>
			<input type="hidden" name="proNum" value="${vo.proNum }"/>
			<ul id="proTitle">
				<li>
					<div>
						<strong>카테고리</strong>
						<span class="ex3">프로젝트가 노출될 카테고리를 선택해 주세요.</span>
					</div>
					<div>
						<select name="cateCode" id="cateCode" class="data">								
							<c:forEach var="cat" items="${list }">
								<option value="${cat.cateCode }"<c:if test="${vo.cateCode == cat.cateCode }">selected</c:if>>${cat.cateName}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li>
					<div>
						<strong>대표이미지</strong>
						<span class="ex3">메인에 노출되는 썸네일 이미지</span>
						<span class="ex">가로320px X 세로220px 비율이 바람직합니다.</span>
					</div>
					<div class="inputData row2 inputImg" id="selectImg1">
						<label for="proImg">이미지업로드</label>
						<img id="loadImg"src="${pageContext.request.contextPath }/ckstorage/${vo.proImg}" 
							onerror="this.src='${pageContext.request.contextPath }/img/inputImg.png'"/>
					  	<input type="file" name="proImg" id="proImg" value="${pageContext.request.contextPath }/ckstorage/${vo.proImg}" />
					</div>
				</li>
				<li>
					<div>
						<strong>프로젝트명</strong>
						<span class="ex3">썸네일 에서 나올 이름입니다.</span>
					</div>
					<div>
						<input type="text" name="proName" id="proName" class="data" placeholder="ex)&nbsp;&nbsp;봄!! 필수 아이템 핵 인싸가 되는 갓템~" value="${vo.proName }"/>
					</div>
					
				</li>
				<li>
					<div>
						<strong>목표금액</strong>
						<span class="ex3">프로젝트 기간동안 희망하는 목표금액을 써주세요.</span>
					</div>
					<div class="won">
						<input type="text" name="proGoal" id="proGoal"class="data" placeholder="ex)&nbsp;&nbsp;150000" value="${vo.proGoal }"/>
					</div>
				</li>
				<li>
					<div>
						<strong>프로젝트기간</strong>
						<span class="ex3">기간은 15, 30, 90, 180 일에서 선택하셔야 합니다.</span>
					</div>
					<div>
						<select name="proDate" id="proDate" class="data">
							<option value="0"<c:if test="${vo.proDate == 0 }">selected</c:if>>프로젝트 기간 선택</option>
							<option value="15"<c:if test="${vo.proDate == 15 }">selected</c:if>>15일</option>
							<option value="1"<c:if test="${vo.proDate == 1 }">selected</c:if>>1개월</option>
							<option value="3"<c:if test="${vo.proDate == 3 }">selected</c:if>>3개월</option>
							<option value="6"<c:if test="${vo.proDate == 6 }">selected</c:if>>6개월</option>
						</select>
					</div>
				</li>
				<li>
					<div>
						<strong>회사이미지</strong>
					</div>
					<div class="inputImg">
						<label for="comImg">이미지업로드</label>
						<img id="loadImg2" src="${pageContext.request.contextPath }/ckstorage/${vo.comImg}" onerror="this.src='${pageContext.request.contextPath }/img/inputImg.png'" /><br/>
					  	<input type="file" name="comImg" id="comImg" value="${vo.comImg}"/>
					</div>
				</li>
				<li>
					<div>
						<strong>사업자명</strong>
						<span class="ex3">또는 회사명</span>
					</div>
					<div>
						<input type="text" name="comName" class="data" placeholder="ex)&nbsp;&nbsp;(주)BRIDGE" value="${vo.comName }"/>
					</div>
				</li>
				<li>
					<div>
						<strong>사업자번호</strong>
						<span class="ex3">사업자 있을시 기재</span>
					</div>
					<div>
						<input type="text" name="comNum" class="data" placeholder="ex)&nbsp;&nbsp;0000-00000-0000-000" value="${vo.comNum }"/>
					</div>
				</li>
				<li>
					<div>
						<strong>계좌번호</strong>
						<span class="ex3">목표금액 달성시 받으실 계좌번호</span>
					</div>
					<div>
						<input type="text" name="account" class="data" placeholder="ex)&nbsp;&nbsp;0000-00000-0000-000" value="${vo.account }"/>
					</div>
				</li>
				<li>
					<div>
						<strong>회사연락처</strong>
					</div>
					<div>
						<input type="text" name="comTel" class="data" placeholder="ex)&nbsp;&nbsp;010-0000-0000" value="${vo.comTel }"/>
					</div>
				<li>
					<div>
						<strong>회사이메일</strong>
						<span class="ex3">펀딩 등록시 고객의정보를 받아보실려면 이메일 주소를 반드시 기재하셔야 합니다.</span>
					</div>
					<div>
						<input type="text" name="comEmail" class="data" placeholder="ex)&nbsp;&nbsp;0000@naver.com" value="${vo.comEmail }"/>
					</div>
				</li>
				<li>
					<div>
						<strong>회사홈페이지</strong>
					</div>
					<div>
						<input type="text" name="comSite" class="data" placeholder="ex)&nbsp;&nbsp;https://www.Bridge.com" value="${vo.comSite }"/>
					</div>
					
				</li>			<%-- ${userMail }:${vo.proCode }:${vo.proNum } --%>
			</ul>
			<div class="saveNext"><a href="javascript:check();">저장하기</a></div>	
		</form>
		<!-- </div> -->
	</div>
</div>

<script>
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#loadImg').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#proImg").change(function() {
    readURL(this);
});
	
function readURL2(input) {
     if (input.files && input.files[0]) {
         var reader = new FileReader();
         reader.onload = function(e) {
             $('#loadImg2').attr('src', e.target.result);
         }
         reader.readAsDataURL(input.files[0]);
     }
 }

 $("#comImg").change(function() {
     readURL2(this);
 });
  
//저장하기 and 다음단계로
function check(){
	var cateCode = document.getElementById('cateCode').value;
	var proImg = document.getElementById('proImg').value;
	var comImg = document.getElementById('comImg').value;
	var proName = document.getElementById('proName').value;
	var proGoal = document.getElementById('proGoal').value;
	var proDate = document.getElementById('proDate').value;
	if(cateCode != null &&  proImg != null && comImg != null && proName != null && proGoal != null && proImg != "" && proGoal != "" && comImg != ""){
		document.getElementById('frm').submit();
	}else{
		alert("카테고리 / 대표이미지 / 프로젝트명\n목표금액 / 프로젝트기간 은 필수입력 입니다.");
	}
	
}
</script>

</body>
</html>
			
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
		
		

