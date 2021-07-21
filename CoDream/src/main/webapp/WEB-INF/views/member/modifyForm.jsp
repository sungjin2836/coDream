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
	<h2>나의 정보 보기</h2>
	<form action="/modifyInfo" method="post" onsubmit="return check()">
	<security:csrfInput/>
	<table class="table table-hover">
		<tbody>
			<tr>
				<th>
					아이디
				</th>
				<td>
					${dto.id}
				</td>
			</tr>
			<tr>
				<th>
					이름
				</th>
				<td>
					${dto.name}
				</td>
			</tr>
			<tr>
				<th>
					비밀번호
				</th>
				<td>
					<input type="password" id="pw" name="password" required="required" autocomplete="off">
				</td>
			</tr>
			<tr>
				<th>
					비밀번호 확인
				</th>
				<td>
					<input type="password" id="pwChk" required="required" autocomplete="off">
				</td>
			</tr>
			<tr>
				<th>
					핸드폰 번호
				</th>
				<td>
					<input type="text" id="phone" name="phone" required="required" autocomplete="off" value="${dto.phone}">
				</td>
			</tr>
			<tr>
				<th>
					이메일
				</th>
				<td>
					<input type="text" id="email" name="email" required="required" autocomplete="off" value="${dto.email}">
				</td>
			</tr>
			<tr>
				<th>
					주소
				</th>
				<td>
					<input type="text" id="address" name="address" required="required" autocomplete="off" value="${dto.address}">
				</td>
			</tr>
			<tr>
				<th>
					자기소개
				</th>
				<td>
					<textarea rows="10" cols="20" name="resume">${dto.resume}</textarea>
				</td>
			</tr>
			<tr>
				<th></th>
				<td><input type="checkbox" id="adrecieve" name="adrecieve"><label for="adrecive">마케팅 수신 동의</label></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정"></td>
			</tr>
		</tbody>
	</table>
	</form>
</div>
</body>
<script type="text/javascript">
function check(){
	var pw = document.getElementById('pw').value;
	var pwChk = document.getElementById('pwChk').value;
	
	if (pw != pwChk) {
		swal('비밀번호 오류', '비밀번호를 확인해주세요');
		return false;
	} else{
		return true;
	}
}

</script>

</html>