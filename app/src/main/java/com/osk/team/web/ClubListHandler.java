package com.osk.team.web;


import com.osk.team.domain.Club;
import com.osk.team.service.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/club/list")
public class ClubListHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Club List</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Club List</h1>");

        out.println("<p><a href='club.html'>클럽생성</a></p>");

        try {

            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>번호</th> <th>도착지</th> <th>가는날</th> <th>오는날</th> <th>테마</th> <th>인원수</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            List<Club> clubs = clubService.listOfMember();

            for (Club c : clubs) {
                out.printf("<tr>"
                                + " <td><a href='detail?no=%1$d'>%d</a></td>"
                                + " <td>%s</td>"
                                + " <td>%s</td>"
                                + " <td>%s</td>"
                                + " <td>%s</td>"
                                + " <td>%d</td> </tr>\n",
                        c.getNo(),
                        c.getArrive(),
                        c.getStartDate(),
                        c.getEndDate(),
                        c.getTheme(),
                        c.getTotal());
            }
            out.println("</tbody>");
            out.println("</table>");

            out.println("<form action='search' method='get'>");
            out.println("<input type='text' name='keyword'> ");
            out.println("<button>검색</button>");
            out.println("</form>");


        } catch (Exception e) {
            // 상세 오류 내용을 StringWriter로 출력한다.
            StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter);
            e.printStackTrace(printWriter);

            // StringWriter 에 들어 있는 출력 내용을 꺼내 클라이언트로 보낸다.
            out.printf("<pre>%s</pre>\n", strWriter.toString());
        }

        out.println("</body>");
        out.println("</html>");
    }

}
