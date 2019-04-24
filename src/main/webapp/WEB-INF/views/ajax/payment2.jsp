<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/bridge/js/payment2.js"></script>
<link rel="stylesheet" href="/bridge/css/payment2.css">
<div>
	<div class="checkBox">
		<div class="sel1"> 이름/금액 공개</div><br/>
		<div><span id="ex">예시*)</span> <%-- <img src="<%=request.getContextPath()%>/upload/${img}"/> <span>${userMail}</span>님이 <span>${vo.itemPrice} , ${vo.selectCount}원 펀딩</span>으로 참여하셨습니다. --%>
				<div class="OneSpter">
					<div class="Img">
						<img src="<%=request.getContextPath() %>/upload/${img}" onerror="this.src='<%=request.getContextPath() %>/img/profile/user.png'"/>
					</div>
					<div class="Spter">
						${userMail}님이 ${vo.itemPrice * vo.selectCount}원 펀딩으로 참여하셨습니다.<br/>
						<div class="Time">${list.payDate }</div>
					</div>
				</div>
		</div>
	</div>
		<div id="pev">이전페이지</div>
		<div id="next">다음페이지</div>
</div>
