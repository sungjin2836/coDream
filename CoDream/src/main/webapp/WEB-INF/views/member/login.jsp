<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 약관 동의</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
<form action="/login" method="post">
	<security:csrfInput/>
	아이디 : <input type="text" name="id"><br>
	비밀번호 : <input type="password" name="password"><br>
	<input type="checkbox" name="remember-me" id="autoLogin">자동 로그인<br>
	<input type="submit" value="로그인"><br>
</form>
</div>
</body>
<script type="text/javascript">
</script>
</html>