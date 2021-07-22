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
</head>
<body>
<%@include file="../header.jsp" %>

<div class="container">
<table class="table" id="order">
	<tr>
		<th>구매 상품명</th>
		<td>CL_TITLE</td>
	</tr>
	<tr>
		<th>구매자명</th>
		<td>ADMIN</td>
	</tr>
	<tr>
		<th>금액</th>
		<td>20000</td>
	</tr>
</table>


	<div class="dropdown" style="width: 900px; height: 50px; display: inline-block;">
    <button style="width:700px; text-align:right;" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">20퍼할인쿠폰
    <span class="caret"></span></button>
	    <ul style="width:700px; text-align:middle" class="dropdown-menu">
	    <c:forEach var="c" items="${lists}">
	    <li><a onclick="couponresult('${c.discount}','${c.status}')">쿠폰명 : ${c.couponname} /
			
			<c:if test="${c.status eq 'P'}">
				할인정도 : ${c.discount}% /
			</c:if>
			<c:if test="${c.status eq 'D'}">
				할인정도 : ${c.discount}원 /
			</c:if>
			최대적용금액 : ${c.maxprice}원 /
			쿠폰종류 : ${c.status}
		</a></li>
		</c:forEach>
	    </ul>
 	 </div>
	    <div style="display: inline-block; height: 50px;"><h3 id="couponresult" style="text-align: right;"></h3></div>
 	 
	<table class="table">
		<tr>
			<th>쿠폰명</th>
			<th>할인</th>
			<th>최대적용금액</th>
			<th>종류</th>
		</tr>
		<c:forEach var="c" items="${lists}">
		<tr>
			<td>${c.couponname}</td>
			
			<c:if test="${c.status eq 'P'}">
				<td>${c.discount}%</td>
			</c:if>
			<c:if test="${c.status eq 'D'}">
				<td>${c.discount}원</td>
			</c:if>
			<td>${c.maxprice}원</td>
			<td>${c.status}</td>
		</tr>
		</c:forEach>
		
	</table>
	
	<button onclick="kakao()">kakaopay btn</button>
</div>


</body>
<script type="text/javascript">
	var price = document.getElementById("order").rows[2].cells[1].innerHTML;
	var status = document.getElementById("order").rows[2].cells[1].innerHTML;
	console.log(price);
	
	function couponresult(coupon, status){
		console.log(status);
		if(status == 'P'){
			var result = price *(100 - coupon) / 100;
//			alert(result);
			var html = result+"원";
			$('h3').empty();
			$('h3').append(html);
		}else if(status == 'D'){
			var result = price - coupon;
	//		alert(result);
			var html = result+"원";
			$('h3').empty();
			$('h3').append(html);
		}
	}
	
	
	function kakao(){
		var result = document.getElementById("couponresult").innerHTML;
		console.log(result);
		$.ajax({
			url:'./coupon/kakaopay',
			method: 'POST',
			dataType:'json',
			data:'result='+result,
			success:function(data){
				var kaka = JSON.parse(data.json);
				console.log(kaka.tid);			
				window.open(kaka.next_redirect_pc_url);
			},
			error:function(error){
				console.log(error);
			}
		})
}
</script>

</html>