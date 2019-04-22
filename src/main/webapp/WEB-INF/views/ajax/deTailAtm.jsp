<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="/bridge/js/deTailAtm.js"></script>	
			<p> 
				${vo.remainingDay}일 남음 
			</p>
			<div id="goal">
				<div id="ok"></div>
			</div>
			<p>
				<span id="goalRate">${vo.proGoalRate}</span>% 달성
			</p>
			<p>
				${vo.proNow}원 펀딩
			</p>
			<p>
				${cnt}명의 서포터
			</p>
			<input type="hidden" id="logStatus" value="${logStatus}"/>
			<input type="hidden" id="chk" value="${chk }"/>
			<input type="hidden" id="proCode" value="${proCode}"/>
			<input type="hidden" id="cateCode" value="${cateCode}"/>
			<div id="big">
				<div id="punding">펀딩하기</div>
			</div>
			<div id="likes">
				<div class="btns" id="like"><img src="/bridge/img/detail/unlike.png" id="bin"/> 
											<img src="/bridge/img/detail/like.png" id="full"/>											
											<div>${vo.likeCount}</div>
				</div>
				<div class="btns" id="ask">문의하기</div>
			</div>
			