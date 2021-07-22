<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8;");
%>
<!DOCTYPE html>
<html>
<head>
<sec:csrfMetaTags />
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script type="text/javascript">
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	$.ajax({
		type: "post",
		url: "/getName",
		headers: headers,
		async: true,
		success: function(msg) {
			console.log(msg);
			if (msg != null) {
				$('#userName').html(msg+"님 환영합니다.");
			} else {
				
			}
		},
		error: function() {
			alert("잘못된 요청입니다.");
		}
	});
</script>
</head>
<header>
<div>
	<h1 style="display: inline">
		<a href="/">로고</a>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		  <button class="btn btn-danger" onclick="location.href='/admin/memberList'">관리자 페이지</button>
		</sec:authorize>
	</h1>
	<sec:authorize access="isAnonymous()">
		<div style="display: inline; float:right; margin : 8px;">
			<button class="btn btn-info" onclick="location.href='/member/login'">Sign in</button>
			<button class="btn btn-primary" onclick="location.href='/member/agree'">Sign up</button>
		</div>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<div style="display: inline; float:right; margin : 8px; color : white;">
			<div style="display: inline; color:black;" id = "userName"></div>
			<button class="btn btn-info" onclick="location.href='/myInfo'">내 정보</button>
			<button class="btn btn-danger" onclick="location.href='/logout'">로그아웃</button>
		</div>
	</sec:authorize>
	
</div>
</header>
</html>