<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 상세</title>
</head>
<body>
<h1>게시글 상세보기1</h1>

<c:if test="${not empty board}">

<form action='update' method='post'>
<table border='1'>
<tbody>
<tr><th>번호</th> <td><input type='text' name='no' value='${board.no}' readonly></td></tr>
<tr><th>제목</th> <td><input name='title' type='text' value='${board.title}'></td></tr>
<tr><th>내용</th> <td><textarea name='content' rows='10' cols='60'>${board.content}</textarea></td></tr>
<tr><th>작성자</th> <td>${board.writer.name}</td></tr>
<tr><th>등록일</th> <td>${board.registeredDate}</td></tr>
<tr><th>조회수</th> <td>${board.viewCount}</td></tr>
</tbody>

<c:if test="${not empty loginUser and loginUser.no == board.writer.no or loginUser.power == 1 }">

<tfoot>
<tr>
  <td colspan='2'>
    <input type='submit' value='변경'><a href='delete?no=${board.no}'>삭제</a>
  </td>
</tr>
</tfoot>
</c:if>

</table>
</form>
</c:if>

<p><a href='list'>목록</a></p>
</body>
</html>