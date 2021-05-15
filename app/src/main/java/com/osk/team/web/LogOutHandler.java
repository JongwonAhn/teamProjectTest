package com.osk.team.web;

import com.osk.team.domain.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
@WebServlet("/logout")
public class LogOutHandler extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Member member = (Member) request.getSession().getAttribute("loginUser");
        if (member == null) {
            out.println("로그인 하지 않았습니다!");
            return;
        }

        request.getSession().invalidate();

        out.printf("%s 님 안녕히 가세요!\n", member.getName());

        response.sendRedirect("login");    // 다시 로그인 화면으로 가기
    }
}