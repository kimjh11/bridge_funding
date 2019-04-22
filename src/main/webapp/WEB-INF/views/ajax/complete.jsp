<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${chk==1 }">
	<script>
		alert("입력성공");
		location.href="/bridge/deTailPage?cateCode=${cateCode}&proCode=${proCode}";
	</script>
</c:if>
<c:if test="${chk !=0 }">
	<script>
		alert("실패");
	</script>
</c:if>