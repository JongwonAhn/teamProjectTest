<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>FAQ 목록</title>
</head>
<body>
<h1>FAQ 목록</h1>
<c:if test="${not empty loginUser and loginUser.power == 1}">
<p><a href='add'>새 글</a></p>
</c:if>
<table border='1'>
<thead>
<tr>
<th>번호</th> <th>제목</th> <th>작성자</th> <th>등록일</th>
</tr>
</thead>
<tbody>

<c:forEach items="${list}" var="f">
<tr> 
  <td>${f.no}</td> 
  <td><a href='detail?no=${f.no}'>${f.title}</a></td>
  <td>관리자</td>
  <td>${f.date}</td>
</tr>
</c:forEach>
</tbody>
</table>

<form action='list' method='get'>
<input type='search' name='keyword' value='${param.keyword}'> 
<button>검색</button>
</form>

</body>
</html>