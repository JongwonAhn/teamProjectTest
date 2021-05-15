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
import java.sql.Date;
import java.util.ArrayList;

@SuppressWarnings("serial")
@WebServlet("/club/update")
public class ClubUpdateHandler extends HttpServlet {
    //수정 코너에서 현재 인원들 탈퇴 기능 넣기

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClubService clubService = (ClubService) request.getServletContext().getAttribute("clubService");

        try {
            int no = Integer.parseInt(request.getParameter("no"));

            Club oldClub = clubService.get(no);

            if (oldClub == null) {
                throw new Exception("해당 번호의 클럽이 없습니다.");
            }

            Member loginUser = (Member) request.getSession().getAttribute("loginUser");
            if (oldClub.getWriter().getNo() != loginUser.getNo()) {
                throw new Exception("변경 권한이 없습니다!");
            }

            // 사용자에게서 변경할 데이터를 입력 받는다.
            Club c = new Club();
            c.setNo(no);
            c.setTitle(request.getParameter("title"));
            c.setContent(request.getParameter("content"));
            c.setWriter(loginUser);

            // ...&member=1&member=18&member=23
            String[] values = request.getParameterValues("member");
            ArrayList<Member> memberList = new ArrayList<>();
            if (values != null) {
                for (String value : values) {
                    Member member = new Member();
                    member.setNo(Integer.parseInt(value));
                    memberList.add(member);
                }
            }
            c.setMembers(memberList);

            // DBMS에게 프로젝트 변경을 요청한다.
            clubService.update(c);

            response.sendRedirect("list");

        } catch (Exception e) {
            StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter);
            e.printStackTrace(printWriter);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>클럽 변경</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>클럽 변경 오류</h1>");
            out.printf("<p>%s</p>\n", e.getMessage());
            out.printf("<pre>%s</pre>\n", strWriter.toString());
            out.println("<p><a href='list'>목록</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
