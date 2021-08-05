<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../header.jsp" %>

<div class="container">
	<table class="table">
		<tr>
			<th>상품명</th>
			<th>가격</th>
			<th>구매일자</th>
			<th>환불</th>
		</tr>
		<c:forEach var="c" items="${lists}">
		<tr>
			<td>${c.cl_title}</td>
			<td>${c.price}</td>
			<td>${c.paydate}</td>
			<td><button onclick="refundbtn('${c.tid}','${c.price}','${c.re_seq}')">환불</button></td>
		</tr>
		</c:forEach>
	</table>
	<h3>${lists}</h3>
</div>

</body>
<script type="text/javascript">
function refundbtn(tid, price, re_seq){
	console.log(tid);
	console.log(price);
	$.ajax({
		url:'./coupon/refund',
		method: 'POST',
		headers: headers,
		dataType:'json',
		data: "tid="+tid+"&price="+price+"&re_seq="+re_seq,
		success:function(data){
			location.reload();
		},
		error:function(error){
			console.log(error);
		}
	})
}


</script>
</html>