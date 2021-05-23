<%@page import="com.osk.team.service.QnaService"%>
<%@page import="com.osk.team.domain.Member"%>
<%@page import="com.osk.team.domain.Qna"%>
<%@page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>QnA 상세</title>
<jsp:useBean id="loginUser" class="com.osk.team.domain.Member" scope="session"/>
<% 
Qna qna = (Qna) request.getAttribute("qna");
if ( (loginUser.getPower() == 1) || (loginUser != null && qna.getWriter().getNo() == loginUser.getNo()) ) {
%>
</head>
<body>
<h1>QnA 게시글 상세보기(JSP + JSP 액션태그 + EL)</h1>
<form action='update' method='post'>
<table border='1'>
<tbody>
<tr><th>번호</th> <td><input type='text' name='no' value='${qna.no}' readonly></td></tr>
<tr><th>제목</th> <td><input name='title' type='text' value='${qna.title}'></td></tr>
<tr><th>내용</th> <td><textarea name='content' rows='10' cols='60'>${qna.content}</textarea></td></tr>
<tr><th>작성자</th> <td>${qna.writer.name}</td></tr>
<tr><th>등록일</th> <td>${qna.registeredDate}</td></tr>
<% 
if (loginUser.getPower() == 1 || qna.getAnswer() != null) {
%>
<tr><th>답변일</th> <td>${qna.answerDate}</td></tr>
<tr><th>답변내용</th> <td><textarea name='answer' rows='10' cols='60'>${qna.answer}</textarea></td></tr>

<%}%> 
</tbody>

        <tfoot>
        <tr><td colspan='2'>
        <% if ( (loginUser.getPower() == 1) || qna.getAnswer() == null ) {
        %>
        <input type='submit' value='변경'>
        <% }%>
           <a href='delete?no=${qna.no}'>삭제</a>
        </td></tr>
        </tfoot>

<%} else {%>
      <p>해당 게시자가 아닙니다.</p>
<%}%>
<p><a href='list'>목록</a></p>
</body>
</html>