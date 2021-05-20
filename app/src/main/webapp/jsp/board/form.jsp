<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 이위치x  <meta http-equiv='Refresh' content='1;url=list'> -->
<title>새 게시글</title>
</head>
<body>
<h1>새 게시글</h1>
<form action="add" method="post" enctype="multipart/form-data">
제목: <input type='text' name='title'><br>
내용: <textarea name='content' rows='10' cols='60'></textarea><br>
사진1: <input type='file' name='photo1'><br>
사진2: <input type='file' name='photo2'><br>
사진3: <input type='file' name='photo3'><br>
<input type='submit' value='등록'>
<p><a href='list'>목록</a></p>
</form>
</body>
</html>