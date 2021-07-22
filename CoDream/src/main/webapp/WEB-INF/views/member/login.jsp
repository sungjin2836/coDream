<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
	<form action="/login" method="post">
		<sec:csrfInput/>
		아이디 : <input type="text" name="id" autocomplete="off"><br>
		비밀번호 : <input type="password" name="password" autocomplete="off"><br>
		<input type="checkbox" name="remember-me" id="autoLogin">자동 로그인<br>
		<input type="submit" value="로그인"><br>
		${requestScope.loginFailMsg}
	</form>
	<div style="text-align: center">
		<a href="${naver_url}"><img style="width:200px" src="/images/naver_login.png"></a><br>
		<a href="${kakao_url}"><img style="width:200px" src="/images/kakao_login.png"></a><br>
		<a href="${google_url}"><img style="width:200px" src="/images/google_login.png"></a>
	</div>
</div>
</body>
<script type="text/javascript">
</script>
</html>