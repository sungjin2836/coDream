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
<form action="/regteacher" method="POST" id="uploadForm">
	<sec:csrfInput/>
	<input type="hidden" name="file_gid" id="file_gid">
	<table>
		<tr>
			<th>
				작성자
			</th>
			<td>
				${dto.name}
			</td>
		</tr>
		<tr>
			<th>
				제목
			</th>
			<td>
				<input type="text" name="te_title" required="required"/>
			</td>
		</tr>
		<tr>
			<th>
				내용
			</th>
			<td>
				<textarea rows="10" cols="20" name="te_content"></textarea>
			</td>
		</tr>
		<tr>
			<th>
				첨부파일
			</th>
			<td>
				<input type="file" multiple="multiple" id="files"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button id='btnUpload'>확인</button>
			</td>
		</tr>
	</table>
    
</form>
</body>

<script type="text/javascript">
$('#btnUpload').on('click', function(event) {
    event.preventDefault();
    
    var form = $('#uploadForm')[0]
    var data = new FormData(form);
    console.log($("#files")[0].files);
    for(var i = 0; i< $("#files")[0].files.length ; i++) {
    	console.log($("#files")[0].files[i]);
    	data.append("files",$("#files")[0].files[i]);
    }
    
    
    $('#btnUpload').prop('disabled', true);
	
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/file/upload",
        data: data,
        processData: false,
        contentType: false,
        headers: headers,
        cache: false,
        timeout: 600000,
        success: function (e) {
        	$('#btnUpload').prop('disabled', false);
        	console.log(e);
        	$('#file_gid').val(e);
        	form.submit();
        },
        error: function (e) {
            $('#btnUpload').prop('disabled', false);
            alert('fail');
        }
    });
})
</script>
</html>