<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/bridge/css/deTailReply.css"/>
<script type="text/javascript" src="/bridge/js/deTailReply.js"></script>
<div id="css">	
	<div>
		<p class="title">댓글 이용안내</p>
		<ul>
			<li>펀딩 취소와 리워드 옵션 및 배송지 변경은 펀딩 기간(${proOpen} ~ ${proEnd})동안만 펀딩내역에서 가능합니다.</li>
			<li>리워드 관련 문의 및 배송 문의는 '메이커에게 문의하기'를 통해 정확한 답변을 받을 수 있습니다.</li>
			<li>서포터님의 연락처, 성명, 이메일 등의 소중한 개인정보는 절대 남기지 마세요.</li>
			<li>본 프로젝트와 무관한 글, 광고성, 욕설, 비방, 도배 등의 글은 예고없이 삭제 등 조치가 취해질 수 있으며, 해당 용으로 인해 메이커, 서포터, 제3자에게 피해가 발생되지 않도록 유의하시기 바랍니다.</li>
		</ul>
	</div>
	<div>
		<p class="title">댓글 작성 유의사항</p>
		최근 메이커 또는 제3자에 대한 허위사실 유포, 비방 목적의 댓글로 인해 당사자간 법적분쟁이 발생한 사례가 증가하고 있습니다.
		악의적 댓글 작성자는 명예훼손, 모욕 등으로 법적 책임을 부담하게 될 수 있다는 점을 유의하여 주시기 바랍니다.
	</div>
	<div>
		<p class="title">댓글쓰기</p>
		<form method="post" action="/bridge/replyWrite" id="replyFrm">		
			<input type="hidden" name="userName" value="${userName}"/>
			<input type="hidden" name="userMail" value="${userMail}"/>
			<input type="hidden" name="userImg" value='${userImg}'/>
			<input type="hidden" name="proCode" value="${ProCode}"/>
			<input type="hidden" name="cateCode" value="${CateCode}"/>
			<input type="hidden" id="logStatus" value="${logStatus}"/>

			<textarea id="textarea" name="replyContent"></textarea>
			<input type="submit" style="cursor: pointer;" value="댓글등록"/>
		</form>
		<div>
		<p class="title">댓글 ${count}개</p>
			배송 및 리워드, 기타 관련하상은 메이커에게 직접 문의하세요.
		</div>
		<c:forEach var="list" items="${list}">
			<div class="oneReply">
				<div class="userImg">
					<img src="<%=request.getContextPath() %>/upload/${userImg }"/>
				</div>
				<div class="userInfo"> 
					<span class="userID">${list.userMail}</span><br/>
					<span class="sysdate">${list.replyDate}</span>
				</div>
				<div class="userContent">${list.replyContent}</div>
			</div>
		</c:forEach>
	</div>
</div>