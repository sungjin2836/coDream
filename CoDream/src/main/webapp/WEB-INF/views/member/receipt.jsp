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
	<div style="width:300px;display:inline;float:left;">
		<h3><a href=/mypage/myInfo>마이 페이지</a></h3>
		<ul>
			<li><a href=/mypage/myInfo>나의 회원 정보</a></li>
			<li><a href="/mypage/regteacher">강사 등록 신청/현황</a></li>
			<li><a href="/receipt">강의 구매 내역</a></li>
		</ul>
	</div>

	<div style="width:800px;display:inline;float:right;">
	<table class="table">
		<tr>
			<th>상품명</th>
			<th>가격</th>
			<th>구매일자</th>
			<th>환불</th>
		</tr>
		<c:if test="${lists.size() eq 0}">
			<tr>
				<td colspan="4">최근 구매내역이 존재하지 않습니다.</td>	
			</tr>
		</c:if>
		<c:forEach var="c" items="${lists}">
		<tr>
			<td>${c.cl_title}</td>
			<td>${c.price}</td>
			<td>${c.paydate}</td>
			<td><button onclick="refundbtn('${c.tid}','${c.price}','${c.re_seq}')">환불</button></td>
		</tr>
		</c:forEach>
	</table>
	</div>
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