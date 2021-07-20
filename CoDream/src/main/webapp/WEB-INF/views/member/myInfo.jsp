<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
</head>
<body>
<%@include file="../header.jsp" %>
<div class="container">
	<h2>나의 정보 보기</h2>
	<table class="table table-hover">
		<tbody>
			<tr>
				<th>아이디</th>
				<td>${dto.id}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${dto.name}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${dto.address}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${dto.phone}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${dto.email}</td>
			</tr>
			<tr>
				<th>자기소개</th>
				<td>${dto.resume}</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${dto.regdate}</td>
			</tr>
			<tr>
				<th>마케팅 수신 여부</th>
				<td>${dto.adrecieve}</td>
			</tr>
			<tr>
				<td colspan="2"><button onclick="location.href='/modifyForm'">수정</button> </td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>