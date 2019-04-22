<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="/bridge/js/payment3.js"></script>
<link rel="stylesheet" href="/bridge/css/payment3.css">
<div>
<form method="post" action="/bridge/complete" class="complete">
	<div id="main">
		<div class="line">
			<div class="leftSide"> 선택${vo.selectRank}</div>
			<div class="rightSide">${vo.itemName}</div>
		</div>
		<div class="line" id="content">
			${vo.itemContent}
		</div><br/>
		<div class="line">
			<div class="leftSide">옵션명</div>
			<div class="rightSide">${vo.itemOption}</div>
		</div>
		<div class="line">
			<div class="leftSide">수량</div>
			<div class="rightSide">${vo.selectCount}개</div>
		</div>
		<div class="line">
			<div class="leftSide">발송시작일</div>
			<div class="rightSide">${start }</div>
		</div>
	</div>
	<div id="result">
		최종결제액은 ${vo.itemPrice}원입니다.
	</div>
	
	<input type="hidden" value="${vo.itemNum}" name="itemNum"/>
	<input type="hidden" value="${vo.itemName}" name="itemName"/>
	<input type="hidden" value="${vo.itemContent}" name="itemContent"/>
	<input type="hidden" value="${vo.itemOption}" name="itemOption"/>
	<input type="hidden" value="${vo.itemPrice }" name="itemPrice"/>
	<input type="hidden" value="${vo.selectCount}" name="selectCount"/>
	<input type="hidden" value="${membervo.userMail}" name="userMail"/>
	<input type="hidden" value="${vo.proCode}" name="proCode"/>

	<div id="userInfo">
		<div id="user">
			<div class="infoTap">
				펀딩 서포터
			</div>
			<div id="info">
				<div class="stop">이름</div>
				<div><input type="text" name="username" class="input"/></div><br/>
				<div class="stop">이메일</div>
				<div><input type="text" name="useremail" class="input"/></div><br/>
				<div class="stop">휴대폰번호</div>
				<div><input type="text" name="tel" class="input"/></div><br/>
			</div>
			<div id="guid">
				<span>배송안내</span>
				<ul>
					<li>펀딩에 대한 보답으로 메이커는 리워드를 약속한 배송일에 제공하기 위해 노력을 다합니다. 다만, 프로젝트 진행 중 예기치 못한 사정으로 리워드 제작 및 배송일정 변경이 있을 수 있음을 알려드립니다.</li>
					<li>리워드 제품의 불량 또는 배송오류에 대한 문의 접수 및 교환 처리는 리워드를 제공하는 메이커가 직접 담당하고 있습니다. 메이커에게 문의하기에 기재된 연락처로 직접 소통할 수 있습니다.</li>
					<li>기부&후원 또는 소셜/캠패인 프로젝트인 경우, 리워드가 발송되지 않더라도 배송지를 입력해야만 펀딩이 완료됩니다.</li>
				</ul>
			</div>
		</div>
		<div id="addr">
			<div class="infoTap">배송정보</div>
			<div id="radios">
				<input type="radio" name="addr" class="radio1" checked value="새로입력"/> <span class="radio1">새로입력</span> 
				<input type="radio" name="addr" class="radio2" value="기존 배송지 입력" id="migan"/> <span class="radio2">기존 배송지 입력</span>
			</div>
			<div id="newInfo">
				<div class="stop">이름</div>
				<div><input type="text" name="userName" class="input addr" value="${membervo.userName}"/></div>
				<div class="stop">휴대폰번호</div>
				<div><input type="text" name="userTel" class="input addr"value="${membervo.userTel }"/></div>
				<div class="stop">주소</div>
				<div><input type="text" name="zipcode"  id="zipcode" class="addr" placeholder="우편번호" value="${membervo.zipcode }"/></div>
				<div id="search">우편번호검색</div>
				<div><input type="text" name="addr" class="input move addr" id="mainAddr" placeholder="주소" value="${membervo.addr }"/></div>
				<div><input type="text" name="addrdetail" class="input move addr" id="deTailAddr" placeholder="상세주소" value="${membervo.addrdetail }"/></div>
				<div><input type="text" name="addrSearch" class="input move addr" id="subAddr" placeholder="참고항목"/ value="${membervo.addrSearch}"></div>
				<div class="stop">배송 요청사항</div>
				<div><input type="text" name="" class="input" placeholder="ex)경비실에 맡겨주세요."/></div>
			</div>
			<div>			
			</div>
		</div>
	</div>
	<div id="payInfo">
		<div class="infoTap">
			결제정보
		</div>
		<div id="paySelect">
			<div class="pay_title">
				결제 정보 입력
			</div>
			<div id="pay_Content">
				<div id="btn1" class="on">간편결제</div>
				<div id="btn2">직접입력</div>
			</div>
			<div class="pay_title">
				결제카드
			</div>
			<div id="CardEnroll">
				<div id="enroll1"><input type="hidden" value="${membervo.cardName} ${membervo.cardNum}"/></div>
				<div id="enroll2">간편결제 등록하기</div>
	
				<input type="hidden" value="${membervo.cardName}" name="cardName" id="simpleName"/>
				<input type="hidden" value="${membervo.cardNum}" name="cardNum" id="simpleNum"/>

			</div>
			<div id="guid2">
				<div id="guid2_title">브릿지(bridge)에서는 결제예약시스템을 이용합니다.</div>
				<div id="guid2_content">쇼핑하기처럼 바로 결제 되지 않습니다. 
					프로젝트의 성공여부에 따라 결제가 진행됩니다. 
					결제정보 입력 후 결제 예약을 완료하시면, 
					결제 대기중으로 예약상태로 등록됩니다. 
					프로젝트 종료일기준 다음 영업일에 펀딩 성공여부에 따라 결제실행/결제취소가진행됩니다.
				</div>
			</div>
		</div>
		<div id="payCard">
			<div class="left10">신용(체크)카드 번호</div>
			<div class="left10" id="card_num">
				<div><input type="text" class="tel off" id="tel1"/></div>			
				<div><input type="text" class="tel off" id="tel2"/></div>			
				<div><input type="text" class="tel off" id="tel3"/></div>			
				<div><input type="text" class="tel off" id="tel4"/></div>
			</div>
			<div class="left10">
				<div class="card_content">유효기간</div>						
				<div class="card_content">카드사</div>						
			</div><br/>
			<div class="left10">
				<div class="card_year"><input type="text" class="off" /></div>						
				<div class="card_year">	
					<input type="hidden" id="writeNum" name="cardNum"/>		
					<select class="off" name="cardName" id="writeName">
						<option>신한카드</option>
						<option>우리카드</option>
						<option>삼성카드</option>
						<option>현대카드</option>
						<option>하나카드</option>
						<option>그외</option>
					</select>
			
				</div><br/>	
			</div>			
			<div class="left10">카드비밀번호</div>						
			<div class="left10"><input type="password" class="off" /></div>							
		</div>
	</div>
	<div>
		<div id="prv">뒤로가기</div>
		<div id="enter">결제확인</div>
	</div>
</form>
</div>