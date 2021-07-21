<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰등록</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../header.jsp" %>

<div class="container">
	<table class="table">
		<tr>
			<th>쿠폰번호</th>
			<th>쿠폰명</th>
			<th>할인</th>
			<th>최대적용금액</th>
		</tr>
		<c:forEach var="c" items="${lists}">
		<tr>
			<td>${c.coupon_seq}</td>
			<td>${c.couponname}</td>
			
			<c:if test="${c.status eq 'P'}">
				<td>${c.discount}%</td>
			</c:if>
			<c:if test="${c.status eq 'D'}">
				<td>${c.discount}원</td>
			</c:if>
			<td>${c.maxprice}원</td>
			<td><button onclick="giveCoupon(${c.coupon_seq})">지급</button></td>
		</tr>
		</c:forEach>
		
	</table>
	<h1>test1</h1>
</div>
</body>
<script type="text/javascript">

function giveCoupon(seq){
	alert(seq);
}

</script>

</html>