package com.spring.filter.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Long startTime = System.currentTimeMillis();
        System.out.println("Request received at: " + startTime);
        System.out.println("Request URL: " + httpRequest.getRequestURL());

        String requestId = UUID.randomUUID().toString();
        // before passing the request to the next filter or servlet, set the requestId in the response header
        // because the response is not yet committed, we can set the header here
        httpResponse.setHeader("requestId", requestId);

        try {
            chain.doFilter(request, response);
        } finally {
            Long endTime = System.currentTimeMillis();
            System.out.println("Response Status: " + httpResponse.getStatus());
            System.out.println("Response sent at: " + endTime);
            System.out.println("Request processing time: " + (endTime - startTime) + " ms");
        }
    }
}
