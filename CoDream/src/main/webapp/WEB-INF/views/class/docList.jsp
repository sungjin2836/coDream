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
	<div style="width:300px;display:inline;float:left;">
		<div><h2><a href="/class/classMain?cl_seq=${cDto.cl_seq}">${cDto.cl_title}</a></h2></div>
			<ul>
				<li><a href="/class/docList?cl_seq=${cDto.cl_seq}">강의 자료</a></li>
				<li><a href="/class/memoList?cl_seq=${cDto.cl_seq}">필기 공유 게시판</a></li>
				<li><a href="">출결</a></li>
			</ul>
	</div>
	<div style="width:800px;display:inline;float:right;">
		<h2><a href="/class/docList?cl_seq=${cDto.cl_seq}">강의 자료</a></h2>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>강의 제목</th>
					<th>작성자</th>
					<th>날짜</th>
				</tr>
				<c:forEach items="${list}" var="dto">
					<tr>
						<!--  [doc_seq=3, cl_seq=1, doc_title=DUMMYD003, doc_content=null, author=DUMMY003, file_gid=0, regdate=Wed Jul 14 15:04:47 KST 2021] -->
						<td><a href="/class/docDetail?doc_seq=${dto.doc_seq}">${dto.doc_title}</a><c:if test="${dto.file_gid ne 0}">&nbsp;<span class="glyphicon glyphicon-floppy-disk"></span></c:if></td>
						<td>${dto.author}</td>
						<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>

<%@include file="../footer.jsp" %>
</body>
</html>