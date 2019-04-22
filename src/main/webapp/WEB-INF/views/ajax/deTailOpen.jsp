<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="/bridge/js/deTailSlider.js"></script>
		<div id="thumbnail"> <!-- 타이틀로 걸어 놓은 이미지나 동영상 -->
			${vo.proTitle }
		</div>
		<div id="company"> <!-- 프로젝트를 발행한 회사정보 -->
				<div id="comimg">
					<p>회사정보</p>
					${vo.comImg }
				</div>
			<div id="comTitle">
				<div id="line"> 
				${vo.comName}<br/>
				<span>이메일 : ${vo.comEmail}</span><br/>
				<span>전화번호 : ${vo.comTel}</span><br/>
				
				</div>
			</div> 
		</div>
		<div id="deTail">
			${vo.proContent}
		</div>
