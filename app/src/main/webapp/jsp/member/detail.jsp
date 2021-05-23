<%@page import="com.osk.team.domain.Member"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.osk.team.domain.Board"%>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <title>회원</title>
</head>
<body>
<h1>회원 상세정보</h1>
<jsp:useBean id="member" type="com.osk.team.domain.Member" scope="request"/>
<%
  if (member != null) {
    pageContext.setAttribute("photo80x80Url",
            member.getPhoto() != null ?
                    "../upload/" + member.getPhoto() + "_80x80.jpg" : "../images/person_80x80.jpg");
    pageContext.setAttribute("photoUrl",
            member.getPhoto() != null ?
                    "../upload/" + member.getPhoto() : "");
%>

<form action='update' method='post' enctype='multipart/form-data'>
  <table border='1'>
    <tbody>
    <tr>
      <th>번호</th>
      <td><input type='text' name='no' value='${member.no}' readonly></td></tr>
    <tr>
      <th>이름</th>
      <td><input name='name' type='text' value='${member.name}'></td></tr>
    <tr>
      <th>이메일</th>
      <td><input name='email' type='email' value='${member.email}'></td></tr>
    <tr>
      <th>암호</th>
      <td><input name='password' type='password'></td></tr>
    <tr>
      <th>생년월일</th>
      <td>${member.birth}</td></tr>
    <tr>
      <th>전화번호</th>
      <td><input name='tel' type='tel' value='${member.tel}'></td></tr>
    <tr>
      <th>성별</th>
      <td><input type='no' name='gender' value='${member.gender}'></td></tr>
    <tr>
      <th>사진</th>
      <td><a href='${photoUrl}'>
        <img src='${photo80x80Url}'></a><br>
        <input name='photo' type='file'></td></tr>
    <tr>
      <th>탈퇴여부</th>
      <td><input type='no' name='status' value='${member.status}'></td></tr>
    <tr>
      <th>관리자권한</th>
      <td><input type='no' name='power' value='${member.power}'></td></tr>
    <tr>
      <th>제제횟수</th>
      <td><input type='no' name='count' value='${member.count}'></td></tr>
    </tbody>
    <tfoot>
    <tr><td colspan='2'>
      <input type='submit' value='변경'> <a href='delete?no=${member.no}'>삭제</a>
    </td></tr>
    </tfoot>
  </table>
</form>

<%} else {%>
<p>해당 번호의 회원이 없습니다.</p>
<%}%>

<p><a href='list'>목록</a></p>
</body>
</html>
</html>