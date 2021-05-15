package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Hotplace;
import com.osk.team.service.HotplaceService;

@SuppressWarnings("serial")
@WebServlet("/hotplace/list")
public class HotplaceListHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HotplaceService hotplaceService = (HotplaceService) request.getServletContext().getAttribute("hotplaceService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>핫플레이스 목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>핫플레이스 목록</h1>");

    out.println("<p><a href='add'>새 글</a></p>");

    try {

      List<Hotplace> hotplaces = hotplaceService.list();

      out.println("<table border='1'>");
      out.println("<thead>");
      out.println("<tr>");
      out.println("<th>번호</th> <th>제목</th> <th>내용</th> <th>조회수</th> <th>등록일</th> <th>주소</th> <th>사진</th> ");
      out.println("</tr>");
      out.println("</thead>");
      out.println("<tbody>");

      for (Hotplace h : hotplaces) {
        out.printf("<tr>"
                        + " <td>%d</td>"
                        + " <td><a href='hotplace.html'>%s</a></td>"
                        + " <td>%s</td>"
                        + " <td>%s</td>"
                        + " <td>%d</td>"
                        + " <td>%s</td>"
                        + " <td>%s</td> </tr>\n",
                h.getNo(),
                h.getTitle(),
                h.getContent(),
                h.getDate(),
                h.getCount(),
                h.getAddress(),
                h.getPhoto());
      }

      out.println("</tbody>");
      out.println("</table>");

      out.println("<form action='search' method='get'>");
      out.println("<input type='text' name='keyword'>");
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