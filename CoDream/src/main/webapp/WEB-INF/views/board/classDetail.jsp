<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
${cDto}
	<div>
		<h2>${cDto.cl_title}</h2>
		<c:forEach var="hash" items="${cDto.hashList}">
			#${hash["value"]}&nbsp;
		</c:forEach>
	</div>
	
	<div>
	<p>${cDto.teacher}</p>
	
	<span><fmt:formatDate value="${cDto.startday}" pattern="yyyy년 MM월 dd일"/> ~ <fmt:formatDate value="${cDto.endday}" pattern="yyyy년 MM월 dd일"/></span>
	<span>${cDto.price}￦</span>
	<span>최대 수강인원 ${cDto.maxmember}명</span>
	<button class="btn btn-warning" onclick="location.href='#'">수강신청</button>
	</div>
	
	<h3>강의소개</h3>
	<div>${cDto.cl_content}</div>
	
	<h3>강의시간</h3>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>요일</th>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
		</thead>
		<tbody>
			<tr>
					<th>시작</th>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
			</tr>
			<tr>
					<th>종료</th>				
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>