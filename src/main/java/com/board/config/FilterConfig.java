package com.board.config;

import com.board.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<SessionFilter> sessionFilter() {
        FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SessionFilter());
        registrationBean.addUrlPatterns("/posts/*"); // 필터를 적용할 URL 패턴 지정
        registrationBean.addUrlPatterns("/boardHome");
        return registrationBean;
    }
}
