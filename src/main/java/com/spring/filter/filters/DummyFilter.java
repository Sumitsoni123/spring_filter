package com.spring.filter.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class DummyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Dummy filter implementation

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletRequest httpServletResponse = (HttpServletRequest) response;

        String requestURI = httpServletRequest.getRequestURI();

        if(!requestURI.contains("/api")) {
            System.out.println("DummyFilter: Request URI does not contain /api, skipping filter logic.");
            // moving to the next filter in the chain without executing any logic
            chain.doFilter(request, response);
        }
        // do something with the request and response in current filter
        System.out.println("DummyFilter: Request URI - " + requestURI);
        // now moving to next filter in the chain
        chain.doFilter(request, response);
    }
}
