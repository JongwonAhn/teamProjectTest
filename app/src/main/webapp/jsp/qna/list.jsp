<%@page import="com.osk.team.domain.Qna"%>
<%@page import="java.util.List"%>
<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    
<!DOCTYPE html>
<html>
<head>
<title>QnA 목록</title>
</head>
<body>
<h1>QnA 목록</h1>
<p><a href='form.html'>QnA 작성</a></p>   
<table border='1'>
<thead>
<tr>
<th>번호</th> <th>제목</th> <th>작성자</th> <th>작성일</th>
</tr>
</thead>
<tbody>
<jsp:useBean id="list" type="List<Qna>" scope="request"/>

<% 
      for (Qna q : list) {
%>
        <tr>
            <td><%=q.getNo()%></td>
            <td><a href='detail?no=<%=q.getNo()%>'><%=q.getTitle()%></a></td>
            <td><%=q.getWriter().getName()%></td>
            <td><%=q.getRegisteredDate()%></td> 
            </tr>
<% 
}
%>
      </tbody>
      </table>
      <form action='update' method='get'>
      </form>
      </body>
      </html>