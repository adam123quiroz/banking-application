package com.packtpub.bankingapplication.security.filter;

import com.packtpub.bankingapplication.security.domain.JwtUser;
import com.packtpub.bankingapplication.security.service.JwtService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/api/secure/*")
public class JwtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        final String authHeaderVal = httpRequest.getHeader(authHeader);
        if (null == authHeaderVal) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;

        }


        try {
            JwtUser jwtUser = jwtService.getUser(authHeaderVal);
            httpRequest.setAttribute("jwtUser", jwtUser);
        } catch (JwtException e) {
            httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;

        }


        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    public String getAuthHeader() {
        return authHeader;
    }

    public void setAuthHeader(String authHeader) {
        this.authHeader = authHeader;
    }

    @Autowired
    private JwtService jwtService;
    private String authHeader = "x-auth-token";
}
