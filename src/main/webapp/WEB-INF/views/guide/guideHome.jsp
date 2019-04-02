<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사이트 이용안내</title>
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/guide.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="guide-wrap">
	<ul class="guide-menu">
		<li><input type="button" value="리워드 펀딩이란" name="reward-info"></li>
		<li><input type="button" value="펀딩과 쇼핑의 차이" name="funding-diff"></li>
		<li><input type="button" value="서포터즈란" name="suporter-intro"></li>
		<li><input type="button" value="프로젝트 참여하기(서포터즈)" name="surporter-guide"></li>
		<li><input type="button" value="프로젝트 개설하기(메이커)" name="maker-guide"></li>
		<li><input type="button" value="주문/결제/배송관련" name="order-payment"></li>
		<li><input type="button" value="교환/반품/AS관련" name="after-service"></li>
	</ul>
	<div class="guide-content"></div>
</div>
</body>
<script>
	$(function(){
		$('.guide-wrap').ready(function(){
			var filename = $('.guide-menu input[type="button"]').first().attr('name');
			getContent(filename);
		});
		
		$('input[type="button"]').click(function(){
			var filename = $(this).attr('name');
			getContent(filename);
		});
		
		function getContent(filename){
			var url = 'txt/'+filename+'.txt';
			$.get(url, function(data, status){
				if(status=='success'){
					$('.guide-content').html(data);
				}
			});
		}
	});
</script>
</html>