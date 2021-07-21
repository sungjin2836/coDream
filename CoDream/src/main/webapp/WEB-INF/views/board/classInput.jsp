<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 강의 개설</title>
<script type="text/javascript" src='https://www.google.com/recaptcha/api.js'></script>
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
			<td><input type="number" class="form-control" name="price" step="100"></td>
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
			<th><input type="checkbox" name="day" value="일" onclick="daycheck()"> 일</th>
			<th><input type="checkbox" name="day" value="월" onclick="daycheck()"> 월</th>
			<th><input type="checkbox" name="day" value="화" onclick="daycheck()"> 화</th>
			<th><input type="checkbox" name="day" value="수" onclick="daycheck()"> 수</th>
			<th><input type="checkbox" name="day" value="목" onclick="daycheck()"> 목</th>
			<th><input type="checkbox" name="day" value="금" onclick="daycheck()"> 금</th>
			<th><input type="checkbox" name="day" value="토" onclick="daycheck()"> 토</th>
		</tr>
		<tr>
			<th>시작 시간</th>
			<td><input type="time" class="form-control" name="starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="starttime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="starttime" readonly="readonly"></td>
		</tr>
		<tr>
			<th>종료 시간</th>
			<td><input type="time" class="form-control" name="endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="endtime" readonly="readonly"></td>
			<td><input type="time" class="form-control" name="endtime" readonly="readonly"></td>
		</tr>
	</table>
	
	<div>강의 설명</div>
	<textarea name="cl_content" class="form-control" rows="" cols="" style="resize:vertical;"></textarea>
	
	<div>강의장 주소</div>
	<input type="button" class="btn" value="주소 검색" onclick="goPopup()">
	<input type="text" class="form-control" name="address" placeholder="주소 검색을 해주세요" readonly="readonly"> 
	
	<div>연관 태그</div>
	<input type="text" class="form-control" name="hash" placeholder="두 개 이상의 태그는 띄어쓰기로 구분됩니다.">
	
	<input type="button" class="btn btn-primary btn-block btn-lg" value="강의등록" onclick="classInput()">
	
</form>
	<!-- google reCaptcha -->
<!-- 	<div class="g-recaptcha" data-sitekey="6LdWD44bAAAAAHQYVc7qAGmEiVMk1RD4UpNIiR4h"></div> -->
	
</div>
</body>
<script type="text/javascript">
window.onload = function() {
	var days = document.getElementsByName("day");
	for(var i = 0; i < days.length; i ++) {
		days[i].onclick = function() {
			for(var j = 0; j < days.length; j ++) {
				swapro(j, days[j].checked);
			}
		}
	}
}

function swapro(i, chk) {
	var starttimes = document.getElementsByName("starttime");
	var endtimes = document.getElementsByName("endtime");

	starttimes[i].readOnly = !chk;
	endtimes[i].readOnly = !chk;
}

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("./jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
} 

function classInput() {
	
	// 리캡챠 통과 여부 확인
// 	if (grecaptcha.getResponse() == "")
// 	{
// 		alert("먼저 자동봇 여부 확인을 진행해주세요.");
// 		return false;
//     }
	
	
	// 유효값 체크
	
	// 모든 값이 제대로 들어가있는지
	// 강의제목 40자 이내인지
	console.log(cl_title.value.length);
	if(cl_title.value.length <= 0 || cl_title.value.length > 20) {
		alert("강의 제목은 1자리 이상 20자 이하여야 합니다.");
		return false;
	}	
	
	// 강의 기간 시작일이 강의 기간 종료일보다 크지 않은지
	var startday = document.getElementsByName("startday")[0];
	var endday = document.getElementsByName("endday")[0];
	if(startday.value > endday.value){
		alert("종료일은 시작일보다 전일 수 없습니다.")
		startday.focus();
		return false;
	}
	
	// 강의 요일로 지정했지만, 시간이 입력되지 않았을 때
	var days = document.getElementsByName("day");
	var starttimes = document.getElementsByName("starttime");
	var endtimes = document.getElementsByName("endtime");
	
	for(var i = 0; i < days.length; i ++) {
		if(days[i].checked == true) { // 지정한 요일의
			// 강의시간을 체크
			if(starttimes[i].value == "") {
				alert("시작시간을 지정해주세요.");
				starttimes[i].focus();
				return false;
			}
			if(endtimes[i].value == ""){
				alert("종료시간을 지정해주세요.");
				endtimes[i].focus();
				return false;
			}
		}
	} 
	
	// 강의 설명이 4천자를 넘어가지 않는지
	
	
	// 
	
}

function dayCheck() {
	// 만약, 요일 체크박스를 선택할 경우 해당 라인의 input이 활성화됨
	
}

</script>

</html>