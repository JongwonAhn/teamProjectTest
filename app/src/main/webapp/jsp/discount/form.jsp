<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 할인정보</title>
</head>
<body>
<h1>새 할인정보</h1>
<form action="add" method="post" enctype="multipart/form-data">
제목: <input type="text" name="title"><br>
내용: <textarea name="content" rows="10" cols="60"></textarea><br>
사진: <input type="file" name="photo"><br>

<input type="submit" value="등록">

</form>
</body>
</html>