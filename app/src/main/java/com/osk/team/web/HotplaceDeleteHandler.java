package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Hotplace;
import com.osk.team.domain.Member;
import com.osk.team.service.HotplaceService;

@SuppressWarnings("serial")
@WebServlet("/hotplace/delete")
public class HotplaceDeleteHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HotplaceService hotplaceService =
        (HotplaceService) request.getServletContext().getAttribute("hotplaceService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>핫플레이스 삭제</title>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Hotplace oldHotplace = hotplaceService.get(no);
      if (oldHotplace == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      if (loginUser.getPower() == 0) {
        if (oldHotplace.getNo() != loginUser.getNo()) {
          throw new Exception("삭제 권한이 없습니다!");
        }
      } else if (loginUser.getPower() == 1) {
        out.print("관리자");
      }

      hotplaceService.delete(no);

      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>핫플레이스 삭제</h1>");
      out.println("<p>핫플레이스를 삭제하였습니다.</p>");

    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

      out.println("</head>");
      out.println("<body>");
      out.println("<h1>게시글 삭제 오류</h1>");
      out.printf("<p>%s</p>\n", e.getMessage());
      out.printf("<pre>%s</pre>\n", strWriter.toString());
      out.println("<p><a href='list'>목록</a></p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}

