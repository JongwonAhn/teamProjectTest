<%@page import="com.osk.team.domain.Member"%>
<%@page import="java.util.List"%>
<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
        trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <title>회원</title>
</head>
<body>
<h1>회원리스트</h1>
<p><a href='add'>새 회원</a></p>
<table border='1'>
  <thead>
  <tr>
    <th>번호</th> <th>이름</th> <th>이메일</th> <th>사진</th>
    <th>생년월일</th> <th>전화번호</th><th>성별</th>
    <th>탈퇴여부</th> <th>관리자권한</th> <th>재재횟수</th>
  </tr>
  </thead>
  <tbody>
  <jsp:useBean id="list" type="List<Member>" scope="request"/>
  <%
    for (Member m : list) {
      pageContext.setAttribute("m", m);
      pageContext.setAttribute("photoUrl",
              m.getPhoto() != null ? "../upload/" + m.getPhoto() + "_30x30.jpg" : "../images/person_30x30.jpg");
  %>
  <tr>
    <td>${m.no}</td>
    <td><a href='detail?no=${m.no}'>${m.name}</a></td>

    <td>${m.email}</td>
    <td>${m.birth}</td>
    <td>${m.tel}</td>
    <td>${m.gender}</td>
    <td><img src='${photoUrl}'></td>
    <td>${m.status}</td>
    <td>${m.power}</td>
    <td>${m.count}</td> </tr>
  <%}%>
  </tbody>
</table>

<form action='list' method='get'>
  <input type='search' name='keyword' value='${param.keyword}'>
  <button>검색</button>
</form>
</body>
</html>