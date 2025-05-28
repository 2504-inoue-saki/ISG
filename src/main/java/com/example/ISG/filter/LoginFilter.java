package com.example.ISG.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        System.out.println("Hello, MyFilter!");
        chain.doFilter(request, response);
//        return topとエラーメッセージを表示
    }

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void destroy() {
    }
}