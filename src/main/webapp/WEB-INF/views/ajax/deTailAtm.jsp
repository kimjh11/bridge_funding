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
				??명의 서포터
			</p>
			<div id="big">
				<input type="button" value="펀딩하기"/>
			</div>
			<div id="likes">
				<input type="button" value="좋아요"/>
				<input type="button" value="문의하기"/>
			</div>
			