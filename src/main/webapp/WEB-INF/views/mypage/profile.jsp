<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<form method="post" action="/bridge/updateProfile?menu=${menu}" enctype="multipart/form-data">
				<div>
					<!-- ================ 이미지 ==================== -->
					<img id="profile-img" src="/bridge/upload/${img}" onerror="this.src='<%=request.getContextPath() %>/img/profile/user.png'"/>
					<div id="file-btn">
						<input type="button" value="이미지 등록" id="file-img-btn"/>
						<input type="file" id="pro-img" name="userImg" title="이미지" accept='image/*' onchange='openFile(event)'/>
					</div>
					<!-- ================ 이미지 ==================== -->	
				</div>
					<div class="profile-div">
						이름<input type="text" name="userName" id="userName" value="${userName}" class="profile-text" readonly/>
					</div>
					<div class="profile-div">
						이메일<input type="text" name="userMail" id="userMail" value="${userMail}" class="profile-text" readonly/>
					</div>
					<div class="profile-div">
						연락처<input type="text" name="userTel" id="userTel" value="${vo.userTel}" class="profile-text" placeholder="ex)010-1234-1234"/>
					</div>
					<div class="profile-div">
						생년월일  <select name="birth1" id="year" class="birth-select">
									<option value="년">년</option>				
							   </select>
								<script>
									$(function(){
										$("#year").val('${vo.birtharr[0]}').prop('selected',true);
									});
								</script>
								
								<select name="birth2" id="month" class="birth-select">
  									<option value="월">월</option>
								</select>
								<script>
									$(function(){
										$("#month").val('${vo.birtharr[1]}').prop('selected',true);
									});
								</script>
								
								<select name="birth3" id="day"  class="birth-select">
  									<option value="일">일</option>
								</select>
								<script>
									$(function(){
										$("#day").val('${vo.birtharr[2]}').prop('selected',true);
									});
								</script>
					</div>
					<div class="profile-div">
						<div>
							우편번호 <input type="text" name="zipcode" id="zipcode" value="${vo.zipcode}" class="profile-text"/>
							<input type="button" onclick="execDaumPostcode()" value="우편번호" id="zipcode-btn"/>
						</div>
						<div>
							기본주소 <input type="text" name="addr" id="addr" value="${vo.addr}" class="profile-text"/>
						</div>
						<div>	
							상세주소 <input type="text" name="addrdetail" id="addrdetail" value="${vo.addrdetail}" class="profile-text"/>
						</div>
						<div>
							참고항목<input type="text" name="addrSearch" id="addrSearch" value="${vo.addrSearch}" class="profile-text"/> 
						</div>
					</div>
					
					<!-- ==========================다음 API 연동 start ============================= -->
					<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
					<script>
					    function execDaumPostcode() {
					        new daum.Postcode({
					            oncomplete: function(data) {
					                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
					
					                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
					                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					                var addr = ''; // 주소 변수
					                var extraAddr = ''; // 참고항목 변수
					
					                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
					                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					                    addr = data.roadAddress;
					                } else { // 사용자가 지번 주소를 선택했을 경우(J)
					                    addr = data.jibunAddress;
					                }
					
					                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
					                if(data.userSelectedType === 'R'){
					                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
					                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
					                        extraAddr += data.bname;
					                    }
					                    // 건물명이 있고, 공동주택일 경우 추가한다.
					                    if(data.buildingName !== '' && data.apartment === 'Y'){
					                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					                    }
					                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					                    if(extraAddr !== ''){
					                        extraAddr = ' (' + extraAddr + ')';
					                    }
					                    // 조합된 참고항목을 해당 필드에 넣는다.
					                    document.getElementById("addrSearch").value = extraAddr;
					                
					                } else {
					                    document.getElementById("addrSearch").value = '';
					                }
					
					                // 우편번호와 주소 정보를 해당 필드에 넣는다.
					                document.getElementById('zipcode').value = data.zonecode;
					                document.getElementById("addr").value = addr;
					                // 커서를 상세주소 필드로 이동한다.
					                document.getElementById("addrdetail").focus();
					            }
					        }).open();
					    }
					</script>
					<!-- ==========================다음 API 연동 end ============================= -->	
					
					<a href="/bridge/mypageForm?menu=${menu}"><input type="button" value="취소" id="cancel-btn"/></a>
					<input  type="submit" value="확인" id="ok-btn"/>
				</form>
			</div>
		</div>
</body>
</html>