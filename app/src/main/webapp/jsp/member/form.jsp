<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>새 회원 등록</title>
</head>
<body>
<h1>새 회원 등록</h1>
<form action="add" method="post" enctype="multipart/form-data">

  이름: <input type="text" name="name"><br>
  비밀번호: <input type="password" name="password"><br>
  이메일: <input type="email" name="email"><br>
  생년월일: <input type='date' name='birth'><br>
  전화번호: <input type='tel' name='tel'><br>
  성별: <input type='number' name='gender'><br>
  사진: <input type="file" name="photo"><br>
  탈퇴여부: <input type="number" name="status"><br>
  관리자권한: <input type="number" name="power"><br>
  제제횟수: <input type="number" name="count"><br>

  <input type="submit" value="등록">
</form>
</body>
</html>