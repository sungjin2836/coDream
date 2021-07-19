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
		<c:forEach var="cl" items="${cList}">
			<div class="row">
				<h3><a href="/board/classDetail?cl_seq=${cl.cl_seq}">${cl.cl_title}</a></h3>
				<p>
					<c:forEach var="hash" items="${cl.hashList}">
						#${hash["value"]}&nbsp;
					</c:forEach>
				</p>
				<p>${fn:substring(cl.cl_content,0,40)}...</p>
				<p>${cl.teacher}</p>
				<p>${cl.startday} ~ ${cl.endday}</p>
				<p>${cl.price}￦</p>
			</div>
		</c:forEach>
	</div>

</body>
</html>