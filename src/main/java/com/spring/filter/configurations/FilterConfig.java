package com.spring.filter.configurations;

import com.spring.filter.filters.DummyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This is another way to config filter without using @Component on DummyFilter Class
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<DummyFilter> requestFilter() {
        FilterRegistrationBean<DummyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DummyFilter());
        registrationBean.addUrlPatterns("/api/*"); // Apply filter to specific URL patterns
        registrationBean.setOrder(1); // Set the order of the filter
        return registrationBean;
    }
}
