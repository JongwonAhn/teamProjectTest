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
@WebServlet("/club/search")
public class ClubSearchHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>클럽 검색</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("<h1>클럽 검색 결과 : %s</h1>\n", keyword);

        try {
            if (keyword == null || keyword.length() == 0) {
                throw new SearchException("검색어를 입력하세요.");
            }

            ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");
            List<Club> list = clubService.search(keyword);
            if (list.size() == 0) {
                throw new Exception("검색어에 해당하는 게시글이 없습니다.");
            }

            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>No.</th> <th>도착지</th> <th>가는날</th> <th>오는날</th> <th>테마</th> <th>인원</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            for (Club c : list) {
                out.printf("<tr>"
                                + " <td><a href='detail?no=%1$d'>%s</a></td>"
                                + " <td><a href='detail?arrive=%1$d'>%s</a></td>"
                                + " <td><a href='detail?startDate=%1$d'>%s</a></td>"
                                + " <td><a href='detail?endDate=%1$d'>%s</a></td>"
                                + " <td><a href='detail?theme=%1$d'>%s</a></td>"
                                + " <td><a href='detail?total=%1$d'>%s</a></td>\n",
                        c.getNo(),
                        c.getArrive(),
                        c.getStartDate(),
                        c.getEndDate(),
                        c.getTheme(),
                        c.getTotal());

                ///club/search?arrive=부산&startDate=2020-01-01&endDate=2020-01-02&theme=1박2일&total=5
            }
            out.println("</tbody>");
            out.println("</table>");

        }catch (SearchException e) {
            out.printf("<p>%s</p>\n", e.getMessage());

        } catch (Exception e) {
            StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter);
            e.printStackTrace(printWriter);

            out.printf("<pre>%s</pre>\n", strWriter.toString());
        }

        out.println("</body>");
        out.println("</html>");
    }
}
