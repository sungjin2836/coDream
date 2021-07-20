<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 강의 개설</title>
<script type="text/javascript">
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("./jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}
 
 function jusoCallBack(roadFullAddr){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	document.classform.address.value = roadFullAddr;
}
 
</script>

</head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
<h2>신규 강의 개설</h2>
<form action="/board/classInput" method="post" name="classform">

	<div>강의 제목</div>
	<div><input type="text" class="form-control" name="cl_title" placeholder="20글자 이하로 작성해주세요"></div>

	<table>
		<tr>
			<th>수강료</th>
			<td><input type="number" class="form-control" name="price"></td>
			<td>원</td>
		</tr>
		<tr>
			<th>수강 정원</th>
			<td><input type="number" class="form-control" name="maxmember"></td>
			<td>명</td>
		</tr>
		<tr>
			<th>강의 기간</th>
			<td><input type="date" class="form-control" name="startday"></td>
			<td><input type="date" class="form-control" name="endday"></td>
		</tr>
	</table>
		
	<div>강의 시간표</div>
	<div>선택한 요일의 시간이 반영됩니다.</div>
	<table class="table">
		<tr>
			<th>요일</th>
			<th><input type="checkbox" name="day" value="일"> 일</th>
			<th><input type="checkbox" name="day" value="월"> 월</th>
			<th><input type="checkbox" name="day" value="화"> 화</th>
			<th><input type="checkbox" name="day" value="수"> 수</th>
			<th><input type="checkbox" name="day" value="목"> 목</th>
			<th><input type="checkbox" name="day" value="금"> 금</th>
			<th><input type="checkbox" name="day" value="토"> 토</th>
		</tr>
		<tr>
			<th>시작 시간</th>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
		</tr>
		<tr>
			<th>종료 시간</th>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
			<td><input type="time" class="form-control"></td>
		</tr>
	</table>
	
	<div>강의 설명</div>
	<textarea name="cl_content" class="form-control" rows="" cols="" style="resize:vertical;"></textarea>
	
	<div>강의장 주소</div>
	<input type="button" class="btn" value="주소 검색" onclick="goPopup()">
	<input type="text" class="form-control" name="address" placeholder="주소 검색을 해주세요" readonly="readonly">
	
</form>
</div>

</body>
</html>