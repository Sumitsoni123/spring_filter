package com.spring.filter.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("RequestFilter: Request received");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        BufferedReader reader = httpServletRequest.getReader();
        StringBuilder requestBody = new StringBuilder();

        String line = reader.readLine();
        while (line != null) {
            requestBody.append(line);
            line = reader.readLine();
        }
        // Once input stream is read, it cannot be read again. So, we need to wrap the request to allow multiple reads.
        // As a result, we will not be able to read the request body in the controller. To solve this, we can use ContentCachingRequestWrapper.
        System.out.println("RequestFilter: Request body - " + requestBody.toString());

        chain.doFilter(request, response);
        System.out.println("RequestFilter: Response sent");
    }
}
