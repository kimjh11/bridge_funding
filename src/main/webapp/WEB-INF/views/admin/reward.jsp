<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<html>
<head>
<title>리워드 관리 페이지</title>
<!-- 커스텀 CSS -->
<link href="<%=request.getContextPath()%>/css/reward.css"
	rel="stylesheet" />
<link rel="styleSheet"
	href="<%=request.getContextPath()%>/css/adminModule.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<div class="wrap width-auto">
		<div class="admin-update-wrap">
			<h3>리워드 관리</h3>
			<div class="admin-content"></div>
			
			<c:forEach var="list" items="${list}">
				<table class='cr-list-table'>
					<tbody>
						<tr>
							<th>
								<div class='cr-list-img'>
									<img class='cr-list-img' src="/bridge/ckstorage/${list.proImg}"
										onerror="this.src='<%=request.getContextPath() %>/img/profile/noImg.png'" />
								</div>
								<div class='cr-list-cat'>${list.userMail}</div>
								<div class='cr-list-proname'>${list.proName}</div>
							</th>
							<th>
								<div class='cr-list-last'>목표펀딩금액</div>
								<div>${list.proGoal}</div>
							</th>
							<th>
								<div class='cr-list-pro'>프로젝트기간</div>
								<c:if test="${list.proDate ne 15}">
									<div>${list.proDate}개월</div>
								</c:if>
								<c:if test="${list.proDate eq 15}">
									<div>${list.proDate}일</div>
								</c:if>
							</th>
							<th>
								<div class='cr-list-review'>
									<!--                                 프로젝트코드                     카테고리코드                 프로젝트번호            페이지번호  -->
									<a href="javascript:detailReview('${list.proCode}','${list.cateCode}','${list.proNum}','${vo.num}')">미리보기</a>
								</div>
							</th>
						</tr>
					</tbody>
					<div hidden="hidden">${list.proCode}</div>
					<div hidden="hidden">${list.cateCode}</div>
					<div hidden="hidden">${list.proNum}</div>
					<div hidden="hidden">${list.writeFinish}</div>
					<div hidden="hidden">${vo.num}</div>
				</table>
			</c:forEach>
			<form method="post" action="/bridge/adminDetailPage" id="frm">
				<input type="hidden" name="proCode" id="proCode" /> 
				<input type="hidden" name="cateCode" id="cateCode" />
				<input type="hidden" name="proNum" id="proNum" />
				<input type="hidden" name="num" id="num" />
			</form>
			<script>
				function detailReview(proCode, cateCode, proNum, num) {
					$("#proCode").val(proCode);
					$("#cateCode").val(cateCode);
					$("#proNum").val(proNum);
					$("#num").val(num);
					$("#frm").submit();
				}
			</script>
		</div>
	</div>
		
	<!-- 페이징 start -->
		<div id="pageBtn">
			<ul class="pagination justify-content-center">

				<!-- 이전 페이지 start -->
				<c:if test="${vo.num == 1}">
					<li class="page-item disabled"><a class="page-link" href="#">＜</a></li>
				</c:if>
				<c:if test="${vo.num > 1}">
					<li class="page-item"><a class="page-link"
						href="<%=request.getContextPath()%>/adminReward?num=${vo.num-1}">＜</a></li>
				</c:if>
				<!-- 이전 페이지 end -->

				<!-- 페이지 수 start -->
				<!--                             1                    1       +       5-1         -->
				<c:forEach var="i" begin="${vo.startPage}" end="${vo.startPage + vo.pageNumCount-1}">
					<c:if test="${i<=vo.totalPage}">
						<c:choose>
							<c:when test="${ i == vo.num }">
								<li class="page-item active"><a class="page-link"
									href="<%=request.getContextPath()%>/adminReward?num=${i}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link"
									href="<%=request.getContextPath()%>/adminReward?num=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				<!-- 페이지 수 end -->

				<!-- 다음 페이지 start  -->
				<c:if test="${vo.num == vo.totalPage}">
					<li class="page-item disabled"><a class="page-link" href="#">＞</a></li>
				</c:if>
				<c:if test="${vo.num < vo.totalPage}">
					<li class="page-item"><a class="page-link"
						href="<%=request.getContextPath()%>/adminReward?num=${vo.num+1}">＞</a></li>
				</c:if>
				<!-- 다음 페이지 end -->
			</ul>
		</div>
		<!-- 페이징 end -->
</body>
</html>