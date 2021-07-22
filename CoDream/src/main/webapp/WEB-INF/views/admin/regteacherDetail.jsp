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
		<h2>신청정보 확인</h2>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>아이디</th>
					<td>${dto.userid}</td>

				</tr>
				<tr>
					<th>제목</th>
					<td>${dto.te_title}</td>
				</tr>
				<tr>
					<th>상태</th>
					<td>${dto.te_admit}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${dto.te_content}</td>
				</tr>
				<tr>
					<th rowspan="${fn:length(list) + 1}">첨부파일</th>
				</tr>
				<c:forEach items="${list}" var="fDto">
					<tr>
					<td><a href="/file/download?filename=${fDto.filename}">${fDto.origname}.${fDto.extension}</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="2">
						<button id='accept' onclick="location.href='/regteacherModify?te_seq=${dto.te_seq}&te_admit=승인'">승인</button>
						<button id='reject' onclick="location.href='/regteacherModify?te_seq=${dto.te_seq}&te_admit=반려'">반려</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>