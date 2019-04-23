<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="/bridge/js/payment.js"></script>
		<c:forEach var="list" items="${list}"> 
		<form method="post" action="/bridge/payment2" name="frm${list.itemRank}" id="frm${list.itemRank}">
			<div class="tap">
				<div id="select">
					<input type="checkbox" value="선택${list.itemRank }"/> 선택${list.itemRank }
				</div>
				<div id="money">
					펀딩금액 ${list.itemPrice}원
				</div>
			</div>
			<div class="content">
			<div class="txt_center">
				<span class="optionTitle">${list.itemName}</span><br/>
				${list.itemContent}
				<input type="hidden" name="itemName" value="${list.itemName }"/>
				<input type="hidden" name="itemContent" value="${list.itemContent}"/>
				<input type="hidden" name="itemPrice" value="${list.itemPrice}"/>
			</div>
				<div class="opt_option">
					<div class="txt1">
						옵션명 
					</div>
					<div class="txt2">
						<select class="select" name="itemOption">
							<input type="hidden" value="${list.itemOption}" class="option"/>
						</select>
					</div>
					<div class="txt1">
						수량
					</div>
					<div class="txt2">
						<input type="text" name="count"/>
					</div>
				</div>
			</div>
				<div class="bottom">
					<div class="btm1" id="first">
						남은수량/제한수량  
					</div>
					<div class="btm2">
						${list.stateCnt}/${list.limitCnt}
					</div>	
					<div class="btm1">
						발송일 : 
					</div>	
					<div class="btm2">
						~ ${proEnd}까지
					</div>	
				</div>
				</form>
			</c:forEach>
			<div id="nextPage">다음페이지 </div>
		