<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="container">
		<h2>회원 정보 보기</h2>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>아이디</th>
					<th>제목</th>
					<th>상태</th>
				</tr>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.userid}</td>
						<td><a href="/regteacherDetail?te_seq=${dto.te_seq}">${dto.te_title}</a></td>
						<td>${dto.te_admit}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3">
						<button onclick="location.href='/regteacherForm'">강사 신청</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>