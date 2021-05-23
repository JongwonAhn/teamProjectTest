<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>FAQ 상세</title>
</head>
<body>
<h1>FAQ 상세보기</h1>

<form action='update' method='post'>
<table border='1'>
  <tbody>
    <tr><th>번호</th> <td><input type='text' name='no' value='${faq.no}' readonly></td></tr>
    <tr><th>제목</th> <td><input name='title' type='text' value='${faq.title}'></td></tr>
    <tr><th>내용</th> <td><textarea name='content' rows='10' cols='60'>${faq.content}</textarea></td></tr>
    <tr><th>작성자</th> <td>관리자</td></tr>
    <tr><th>등록일</th> <td>${faq.date}</td></tr>
  </tbody>

  <c:if test="${not empty loginUser and loginUser.power == 1}">
  <tfoot>
  <tr>
    <td colspan='2'>
      <input type='submit' value='변경'><a href='delete?no=${faq.no}'>삭제</a>
    </td>
  </tr>
  </tfoot>
  </c:if>
      
</table>
</form>

<p><a href='list'>목록</a></p>
</body>
</html>