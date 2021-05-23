<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>할인정보 상세</title>
</head>
<body>
<h1>할인정보 상세보기</h1>

<c:if test="${not empty discount.photo}">
  <c:set var="photo80x80Url">../upload/${discount.photo}_80x80.jpg</c:set>
  <c:set var="photoUrl">../upload/${discount.photo}</c:set>
</c:if>

<form action='update' method='post' enctype='multipart/form-data'>
<table border='1'>
  <tbody>
    <tr><th>번호</th> <td><input type='text' name='no' value='${discount.no}' readonly></td></tr>
    <tr><th>제목</th> <td><input name='title' type='text' value='${discount.title}'></td></tr>
    <tr><th>내용</th> <td><textarea name='content' rows='10' cols='60'>${discount.content}</textarea></td></tr>
    <tr><th>작성자</th> <td>관리자</td></tr>
    <tr><th>등록일</th> <td>${discount.date}</td></tr>
    <tr><th>조회수</th> <td>${discount.count}</td></tr>
    
    <tr><th>사진</th> 
  <td><a href='${photoUrl}'>
  <img src='${photo80x80Url}'></a><br>
  <input name='photo' type='file'></td></tr>
</tbody>

  <c:if test="${not empty loginUser and loginUser.power == 1}">
  <tfoot>
  <tr>
    <td colspan='2'>
      <input type='submit' value='변경'><a href='delete?no=${discount.no}'>삭제</a>
    </td>
  </tr>
  </tfoot>
  </c:if>
      
</table>
</form>

<p><a href='list'>목록</a></p>
</body>
</html>