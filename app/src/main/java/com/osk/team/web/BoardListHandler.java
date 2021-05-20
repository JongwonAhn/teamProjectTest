package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osk.team.domain.Board;
import com.osk.team.service.BoardService;


@SuppressWarnings("serial")
@WebServlet("/board/list")
public class BoardListHandler extends HttpServlet {
  //*************************이 소스는 board관련 테스트소스 입니다
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    BoardService boardService = (BoardService) request.getServletContext().getAttribute("boardService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    // JSP가 게시글 목록을 출력할 때 사용할 데이터를 준비한다.  
    try {
      String keyword = request.getParameter("keyword");
      List<Board> boards = null;
      if (keyword != null && keyword.length() > 0) {
        boards = boardService.search(keyword);
      } else {
        boards = boardService.list();
      }
      //      System.out.println("11111"); // 출력 테스트
      // JSP가 사용할 수 있도록 ServletRequest 보관소에 저장한다.
      request.setAttribute("list", boards);

      // 목록 출력을 JSP에게 맡긴다.
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/jsp/board/list.jsp").include(request, response);
      //      System.out.println("222222"); // 출력 테스트 
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





