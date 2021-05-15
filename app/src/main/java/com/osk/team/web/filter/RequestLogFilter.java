package com.osk.team.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class RequestLogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        System.out.printf("[%s:%d] %s\n",
                httpRequest.getRemoteAddr(), httpRequest.getRemotePort(), httpRequest.getServletPath());

        nextChain.doFilter(request, response);
    }
}
