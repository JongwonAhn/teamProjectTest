<%@page import="org.apache.ibatis.reflection.SystemMetaObject" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page import="com.osk.team.domain.Qna" %>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>클럽 상세</title>
</head>
<body>
<h1>클럽 상세보기</h1>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

<c:if test="${not empty club}">
    <form action='update' method='post'>
        <table border='1'>
            번호: <input type='text' name='no' value='${club.no}' readonly><br>
            방장: <input type='text' name='writer' value='${club.writer.name}' readonly><br>
            팀원: <br>
            <th>팀원</th>
            <jsp:include page="/jsp/club/member_list.jsp"/>
            <c:forEach items="${member}" var="m">
                <c:forEach items="${clubMembers}" var="clubMember">
                    <c:if test="${m.no == clubMember.no}">
                        <c:set var="checked" value="checked"/>
                    </c:if>
                </c:forEach>
            </c:forEach>
            <button type="button" class="btn btn-primary" id="clubjoin">클럽 참여</button>

            <br>
            도착지: <input type='text' name='arrive' value='${club.arrive}' readonly><br>
            가는날: <input type='date' name='startDate' value='${club.startDate}' readonly><br>
            오는날: <input type='date' name='endDate' value='${club.endDate}' readonly><br>
            테마: <input name='theme' id="themeid" value='${club.theme}' readonly><br>
            제목: <input type='text' name='title' value='${club.title}'><br>
            내용: <textarea name='content' rows='10' cols='60'>${club.content}</textarea><br>
            인원수: ${club.total} 명 <br>
            <tr>
                <th>사진</th>
                <c:if test="${not empty club.photos}">
                <%System.out.println("11");%>
                <c:forEach items="${club.photos}" var="p">
                <%System.out.println("22");%>
                <c:set var="photo254x178Url">../upload/${p.name}_254x178.jpg</c:set>
                <%System.out.println("33");%>
                <td><img src='${photo254x178Url}'><br>
                            <%System.out.println("44");%>
                    </c:forEach>
                    </c:if>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan='2'>
                    <input type='submit' value='변경'>
                    <a href='delete?no=${club.no}'>삭제</a>
                </td>
            </tr>
            </tfoot>
        </table>
    </form>
</c:if>
<p><a href='list'>목록</a></p>
</form>
</body>
</html>