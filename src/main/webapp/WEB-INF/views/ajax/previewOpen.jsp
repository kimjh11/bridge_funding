<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="/bridge/js/deTailSlider.js"></script>
		<div id="thumbnail"> <!-- 타이틀로 걸어 놓은 이미지나 동영상 -->
			<iframe id="myFrame" width="100%" height="100%" src="${vo.proTitle }" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div>
		<div id="company"> <!-- 프로젝트를 발행한 회사정보 -->
				<div id="comimg">
					<p>회사정보</p>
					 <img src="${pageContext.request.contextPath }/ckstorage/${vo.comImg}" onerror="this.src='${pageContext.request.contextPath }/img/inputImg.png'"/>
				</div>
			<div id="comTitle">
				<div id="line">
				<c:if test="${vo.comName == null || vo.comName.equals('') }">
					회사이름<br/>
				</c:if>
				<c:if test="${vo.comName != null || !vo.comName.equals('') }">
					${vo.comName}<br/>
				</c:if>
				<span>
					<c:if test="${vo.comEmail == null || vo.comEmail.equals('') }">
						이메일 : 회사메일
					</c:if>
					<c:if test="${vo.comEmail != null && !vo.comEmail.equals('') }">
						이메일 : ${vo.comEmail}
					</c:if>
				</span><br/>
				<span>
					<c:if test="${vo.comTel == null || vo.comTel.equals('') }">
						전화번호 : 회사연락처
					</c:if>	
					<c:if test="${vo.comTel != null && !vo.comTel.equals('') }">
						전화번호 : ${vo.comTel }
					</c:if>	
				</span><br/>
				
				</div>
			</div> 
		</div>
		<div id="deTail">
			<c:if test="${vo.proContent != null || !vo.proContent.equals('') }">
				${vo.proContent}
			</c:if>
			<c:if test="${vo.proContent == null || vo.proContent.equals('') }">
				아래 안내드리는 스토리 필수항목이 누락되거나 충분하지 않은 경우, 심사나 오픈 승인이 되지 않을 수 있습니다. (필수항목의 순서는 상관 없으며 프로젝트에 맞게 재배치해도 무방합니다.<br/>
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
			</c:if>	
		</div>
