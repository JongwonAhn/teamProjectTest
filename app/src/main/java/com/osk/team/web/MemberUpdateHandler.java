package com.osk.team.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.osk.team.domain.Member;
import com.osk.team.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateHandler extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>회원 정보 변경</title>");
        try {
            out.println("<meta http-equiv='Refresh' content='1;url=list'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>회원 정보 변경</h1>");

            int no = Integer.parseInt(request.getParameter("no"));

//            Member oldMember = memberService.get(no);
//            if (oldMember == null) {
//                out.println("<p>해당하는 회원정보가 없습니다.</p>");
//                return;
//            }

            Member member = new Member();
            member.setName(request.getParameter("mname"));
//            member.setMemail(request.getParameter("memail"));
            member.setPassword(request.getParameter("mpassword"));
//            member.setMphoto(request.getParameter("mphoto"));
//            member.setMtel(request.getIntHeader("mtel"));

            memberService.update(member);

            out.println("<p>회원정보 변경을 완료했습니다.</p>");

        } catch (Exception e) {
            StringWriter strWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(strWriter);
            e.printStackTrace(printWriter);
            out.println(strWriter.toString());

            out.println("</head>");
            out.println("<body>");
            out.println("<h1>회원정보 변경 오류!</h1>");
            out.printf("<pre>%s</pre>\n", strWriter.toString());
            out.println("<p><a href='list'>회원 목록</a></p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}