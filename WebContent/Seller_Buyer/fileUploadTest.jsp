<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>File Upload:</h3>
	Select a file to upload:
	<br />
	<form action="<%=request.getContextPath()%>/FileUploadController" method="post" enctype="multipart/form-data">
		<input type="file" name="img" size="50" /> <br /> 
		<input type="submit" value="Upload File" />
	</form>
</body>
</html>