<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 관리페이지</title>
<link rel="styleSheet" href="<%=request.getContextPath() %>/css/adminModule.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="wrap width-auto">
	<div class="admin-update-wrap">
		<h3>카테고리 관리</h3>
		<div class="admin-content category">
			<form action="<%=request.getContextPath() %>/cateSubmit" method="post" id="cateSubmitFrm" enctype="multipart/form-data">
				<ul>
					<li>
						<span class="bold">카테고리명(한글)</span>
						<input type="text" name="cateName" />
					</li>
					<li>
						<span class="bold">카테고리코드(영문)</span>
						<input type="text" name="cateCode" />
					</li>
					<li>
						<span class="bold">카테고리이미지</span>
						<input type="file" name="cateImgFile" />
					</li>
					<li>
						<span class="bold">카테고리순위</span>
						<input type="text" name="cateRank" readonly/>
					</li>
					<li>
						<input type="submit" value="카테고리 등록" />
					</li>
				</ul>
			</form>
		</div>
	</div>
	<div class="admin-list-wrap">
		<h3>카테고리 목록</h3>
		<div class="admin-content">
			<input class="update-form-open" type="button" value="수정하기" />
			<ul class="list-head bold">
				<li class="col1">NO</li>
				<li class="col2">코드</li>
				<li class="col3">카테고리명</li>
				<li class="col3">이미지파일</li>
				<li class="col1">순서</li>
				<li class="col2">공개</li>
			</ul>
			<form method="post" action="./cateUpdate" id="cateUpdateFrm"><!-- addClass:cate-update, list-update-content=display:block  -->
			<c:forEach var="vo" items="${cateList }">
				<c:if test="${vo.cateCode!='none' }">
					<ul class="list-content">
						<li class="col1">${vo.cateNum }</li>
						<li class="col2">${vo.cateCode }</li>
						<li class="col3">${vo.cateName }</li>
						<li class="col3">
							<img src="<%=request.getContextPath() %>/img/category/${vo.cateImg}"/>
						</li>
						<li class="col1 cate-rank">
						<c:if test="${vo.cateRank==0 }">미정</c:if>
						<c:if test="${vo.cateRank!=0 }">${vo.cateRank }</c:if>
						</li>
						<%-- <c:if test="${vo.cateCode!='all' }"> --%>
							<li class="col2">
								<span class="open-toggle">${vo.cateOpen }</span>
							</li>
						<%-- </c:if> --%>
					</ul>
					<ul class="list-content update-all">
						<li class="col1">${vo.cateNum }</li>
						<li class="col2">${vo.cateCode }</li>
						<li class="col3">
							<input type="text" name="cateName" placeholder="${vo.cateName }"/>	
						</li>
						<li class="col3">
							<input type="file" name="cateImgFile"/>
						</li>
						<c:if test="${vo.cateCode=='none' || vo.cateCode=='all'}">
							<li class="col1">-</li>
							<li class="col2">-</li>
						</c:if>
						<c:if test="${vo.cateCode!='none' && vo.cateCode!='all'}">
						<li class="col1">
							<select class="rank-select" name="cateRank">
								<c:forEach items="${cateList }" varStatus="num" end="${fn:length(cateList) }">
								<option>${num.count }</option>
								</c:forEach>
							</select>
						</li>
						<li class="col2">
							${vo.cateOpen}
						</li>
						</c:if>
					</ul>
				</c:if>
			</c:forEach>
			<div class="update-btn-wrap">
				<input type="submit" value="확인"/>
				<input type="button" value="취소" />
			</div>
			</form>
		</div>
	</div>
</div>
<script>
$(function(){
	//카테고리 등록 순위세팅
	$('#cateSubmitFrm input[name="cateRank"]').val($('select[name="cateRank"] option:last').index()+1);
	//순서 selected
	var rank = $('.cate-rank').text();
	console.log(rank);
	var rankSelect = $('.rank-select option');
	console.log(rankSelect);
	if(rank=='미정'){
		//$(rankSelect).val('미정');
	}else{
		//$(rankSelect).val(rank).prop("selected", true);
	}
	
	//수정확인(submit)클릭시
	$('#cateUpdateFrm').submit(function(){
		var rank = $('input[name="cateRank"]').val();
		var cateName = $('input[name="cateName"]').val();
		var cateImg = $('input[name="cateImgFile"]').val();
		
		console.log('rank='+rank);
		console.log('name='+cateName);
		console.log('img='+cateImg);
		
		alert('실행');
		//text수정사항 없을경우
		if($('input[name="cateName"]').val==""
			&&$('input[name="cateImgFile"]').val==""){
			return false;
		}
	});
	
	//수정버튼(button) 클릭시
	$('.update-form-open').click(function(){
		var list = $('.list-content');//카테리스트
		var updateForm = $('.list-content.update-all');//업데이트 폼
		var updateBtn = $('.update-btn-wrap');//확인,취소버튼
		
		
		$(this).toggle();//수정하기 버튼 없애기
		$(updateBtn).css('display','block');//확인취소버튼 오픈
		$(list).css('display','none');//카테리스트 없애기
		$(updateForm).css('display','block');//업데이트폼 오픈
		
		
	});
	
	//수정취소클릭시
	$('.update-btn-wrap').click(function(){
		$('.update-form-open').css('display','block');
		$(this).toggle();
	});
	
	//공개여부 클릭시
	$('.open-toggle').click(function(){
		const open = $(this);
		if(confirm('상태를 변경하시겠습니까?')){
			const num = $(this).parent().siblings().eq(0).text();		
			const params = 'itemNum='+num+'&table=category';
			console.log(params);
			$.ajax({
				type : 'get',
				url : '/bridge/openToggle',
				data : params,
				contentType : 'application/json; charset=utf-8',
				success : function(result){
					console.log(open);
					if(result=='ok'){
						if(open.text()=='N'){
							open.text('Y');
						}else{
							open.text('N');
						}
					}
				},
				error : function(error){
					alert(error.responseText);
				}
			});
		}
	});
});
</script>
</body>
</html>