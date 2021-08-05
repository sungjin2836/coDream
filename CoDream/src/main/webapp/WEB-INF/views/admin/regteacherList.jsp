<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강사 신청자 조회</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div class="container">
	
	<div style="width:300px;display:inline;float:left;">
	<h3><a href="/admin/memberList">관리자 게시판</a></h3>
		<ul>
			<li><a href="/admin/memberList">회원 관리</a></li>
			<li><a href="/admin/regteacherList">강사 신청 대기</a></li>
		</ul>
	</div>
	<div style="width:800px;display:inline;float:right;">
		<h2><a href="/admin/regteacherList">강사 신청 대기</a></h2>
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
						<td><a href="/admin/regteacherDetail?te_seq=${dto.te_seq}">${dto.te_title}</a></td>
						<td>${dto.te_admit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
<%@include file="../footer.jsp" %>
</body>
</html>