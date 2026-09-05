package com.spring.filter.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

//@Component
public class ResponseBodyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);

        chain.doFilter(request, responseWrapper);

        byte[] responseBody = responseWrapper.getContentAsByteArray();
        String responseBodyString = new String(responseBody);

        String modifiedResponseBodyString =
               """
               {
                    "status": "success",
                    "data": %s
               }
                """.formatted(responseBodyString);

        responseWrapper.resetBuffer();
        responseWrapper.getWriter().write(modifiedResponseBodyString);
        responseWrapper.copyBodyToResponse();
    }
}
