<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bridge</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/inputProject.css"/>
<script>

</script>
</head>
<body>
		
		<%@include file="projectTitle.jspf"%>
		<div id="proBody">
			<div id="proLeft">
				<ul id="proTitle">
					<li class="row1">카테고리</li>
					<li class="row2">
						대표이미지<br/>
						<span class="ex3">메인에 노출되는 썸네일 이미지</span><br/><br/>
						<span class="ex">반드시 사진은 가로 0000px X 세로 000px 크기를 지켜주세요.</span>
					</li>
					<li class="row3">프로젝트명<br/><span class="ex3">썸네일 에서 나올 이름입니다.</span></li>
					<li class="row4">목표금액<br/><span class="ex3">프로젝트 기간동안 희망하는 목표금액을 써주세요.</span></li>
					<li class="row5">프로젝트기간<br/><span class="ex3">프로젝트 기간은 15, 30, 90, 180 일에서 선택하셔야 합니다.</span></li>
					<li class="row6">회사이미지</li>
					<li class="row7">
						사업자명<br/>
						<span class="ex3">또는 회사명</span>
					</li>
					<li class="row8">
						사업자번호<br/>
						<span class="ex3">사업자 있을시 기재</span>
					</li>
					<li class="row9">
						계좌번호<br/>
						<span class="ex3">목표금액 달성시 받으실 계좌번호</span>
					</li>
					<li class="row10">회사연락처</li>
					<li class="row11">회사이메일<br/><span class="ex3">펀딩 등록시 고객의정보를 받아보실려면 이메일 주소를 반드시 기재하셔야 합니다.</span></li>
					<li class="row12">회사홈페이지</li>
					
				</ul>
			</div>
			<div id="proMid">		
					<form>				
						<div class="inputData row1" id="selectOpt1">
							<!-- 카테고리선택 -->
							<select class="data">
								<option value="">카테고리 선택</option>
								<option value="">영화</option>
								<option value="">공연</option>
								<option value="">게임</option>
								<option value="">아이템</option>
							</select>
						</div>
						
						<div class="inputData row2 inputImg" id="selectImg1">
							<!-- 대표이미지 -->
							<img id="loadImg" src="${pageContext.request.contextPath }/img/inputImg.png" /><br/>
						  	<label for="proImg">업로드</label>
						  	<input type="file" name="proImg" id="proImg"/>
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
						</script>

						<div class="inputData row3">
							<!-- 프로젝트명 -->
							<input type="text" class="data"/>
						</div>
						
						<div class="inputData row4">
							<!-- 목표금액 -->
							<input type="text" class="data"/><span class="ex2">원</span>
						</div>
						
						<div class="inputData row5">
							<!-- 기간 -->
							<select class="data">
								<option value="">프로젝트 기간 선택</option>
								<option value="">15일</option>
								<option value="">1개월</option>
								<option value="">3개월</option>
								<option value="">6개월</option>
							</select>
						</div>
						
						<div class="inputData row6 inputImg" id="selectImg2" >
							<!-- 회사이미지 -->
							<img id="loadImg2" src="${pageContext.request.contextPath }/img/inputImg.png" /><br/>
						  	<label for="comImg">업로드</label>
						  	<input type="file" name="comImg" id="comImg"/>
						</div>
						
						<script>
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
						</script>
						
						<div class="inputData row7">
							<!-- 사업자명 -->
							<input type="text" class="data"/>
						</div>
						<div class="inputData row8">
							<!-- 사업자번호 -->
							<input type="text" class="data"/>
						</div>
						<div class="inputData row9">
							<!-- 계좌번호 -->
							<input type="text" class="data"/>
						</div>
						<div class="inputData row10">
							<!-- 연락처 -->
							<input type="text" class="data"/>
						</div>
						<div class="inputData row11">
							<!-- 이메일 -->
							<input type="text" class="data"/>
						</div>
						<div class="inputData row12">
							<!-- 홈페이지 -->
							<input type="text" class="data"/>
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