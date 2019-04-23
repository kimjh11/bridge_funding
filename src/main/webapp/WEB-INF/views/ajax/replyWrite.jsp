<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${cnt<0 }">
	<script>
		alert("댓글입력에 실패했습니다.");
	</script>
</c:if>
<c:if test="${cnt>0 }">
	<script>
		location.href="/bridge/deTailPage?cateCode=${cateCode}&proCode=${proCode}";
	</script>
</c:if>