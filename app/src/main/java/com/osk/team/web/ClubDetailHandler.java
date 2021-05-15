package com.osk.team.web;

import com.osk.team.domain.Club;
import com.osk.team.domain.Member;
import com.osk.team.service.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
@WebServlet("/club/detail")
public class ClubDetailHandler extends HttpServlet {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>클럽</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>클럽 정보</h1>");


        try {
            int no = Integer.parseInt(request.getParameter("no"));

            Club c = clubService.get(no);
            if (c == null) {
                out.println("<p>해당 번호의 클럽이 없습니다.</p>");
                out.println("</body>");
                out.println("</html>");
                return;
            }
            out.println("<form action='update' method='post' enctype='multipart/form-data'>");
            out.println("<table border='1'>");
            out.println("<tbody>");
            out.printf("<tr><th>번호</th>"
                    + " <td><input type='text' name='no' value='%d' readonly></td></tr>\n", c.getNo());
            out.printf("<tr><th>방장</th> <td>%s</td></tr>\n", c.getWriter().getName());
            out.printf("<tr><th>도착지</th>"
                    + " <td><input name='title' type='text' value='%s'></td></tr>\n", c.getArrive());
            out.printf("<tr><th>가는날</th> <td>%s</td></tr>\n", formatter.format(c.getStartDate()));
            out.printf("<tr><th>오는날</th> <td>%s</td></tr>\n", formatter.format(c.getEndDate()));
            out.printf("<tr><th>제목</th> <td>%s</td></tr>\n", c.getTitle());
            out.printf("<tr><th>내용</th> <td>%s</td></tr>\n", c.getContent());
            out.printf("<tr><th>인원수</th> <td>%s</td></tr>\n", c.getTotal());

            //사진 이상하게 출력됨 확인바람!
            out.printf("<tr><th>사진</th> <td>"
                            + "<a href='%s''%s''%s'><img src='%s''%s''%s'></a><br>"
                            + "<input name='photo1' type='file'></td></tr>\n"
                            + "<input name='photo2' type='file'></td></tr>\n"
                            + "<input name='photo3' type='file'></td></tr>\n",
                    c.getPhoto1() != null ? "../upload/" + c.getPhoto1() : "",
                    c.getPhoto1() != null ? "../upload/" + c.getPhoto1() + "_254*178.jpg" : null,
                    c.getPhoto2() != null ? "../upload/" + c.getPhoto2() : "",
                    c.getPhoto2() != null ? "../upload/" + c.getPhoto2() + "_254*178.jpg" : null,
                    c.getPhoto3() != null ? "../upload/" + c.getPhoto3() : "",
                    c.getPhoto3() != null ? "../upload/" + c.getPhoto3() + "_254*178.jpg" : null
            );

            out.println("</tbody>");

            Member loginUser = (Member) request.getSession().getAttribute("loginUser");
            if (loginUser != null && c.getWriter().getNo() == loginUser.getNo()) {
                out.println("<tfoot>");
                out.println("<tr><td colspan='2'>");
                out.println("<input type='submit' value='변경'> "
                        + "<a href='delete?no=" + c.getNo() + "'>삭제</a> ");
                out.println("</td></tr>");
                out.println("</tfoot>");
            }

            out.println("</table>");
            out.println("</form>");


        } catch (Exception e) {
            StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter);
            e.printStackTrace(printWriter);
            out.printf("<pre>%s</pre>\n", strWriter.toString());
        }
        out.println("<p><a href='list'>목록</a></p>");

        out.println("</body>");
        out.println("</html>");
    }
}
