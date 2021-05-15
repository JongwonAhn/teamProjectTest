package com.osk.team.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextChain)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String servletPath = httpRequest.getServletPath().toLowerCase();

        if (servletPath.endsWith("add") ||
                servletPath.endsWith("update") ||
                servletPath.endsWith("search") ||
                servletPath.endsWith("delete")) {

            if (httpRequest.getSession().getAttribute("loginUser") == null) {
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().println("로그인 해야 합니다!");
                return;
            }
        }
        nextChain.doFilter(request, response);
    }
}