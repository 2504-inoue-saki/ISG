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
            bean.addUrlPatterns("/new","/add","/delete/*","/comment","/deleteComment/*","/update-isStopped/*");
//            bean.setOrder();　フィルターの順番
            return bean;
        }

}

