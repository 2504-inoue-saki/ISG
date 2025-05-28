package com.example.ISG.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginFilterConfig {

        @Bean
        public FilterRegistrationBean<LoginFilter> LoginFilter() {

            FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<>(new LoginFilter());
            bean.addUrlPatterns("/top/*");
//            bean.setOrder();
            return bean;
        }

}

