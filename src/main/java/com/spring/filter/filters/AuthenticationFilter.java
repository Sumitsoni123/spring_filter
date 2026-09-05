package com.spring.filter.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1) // Set the order of the filter, lower numbers have higher priority
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Perform authentication logic here
        System.out.println("Authentication Filter: Checking authentication...");

        String token = httpRequest.getHeader("token");
        if (token == null || !token.equals("12345")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: Invalid or missing token");
            return;
        }

        // If authentication is successful, continue the filter chain
        chain.doFilter(request, response);

        System.out.println("Authentication Filter: Authentication successful, proceeding with request.");
    }
}
