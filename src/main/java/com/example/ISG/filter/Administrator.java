//package com.example.ISG.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {"/setting", "/edit"})
//public class Administrator {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//
//        //ServletRequestをHttpServletRequestに型変更
//        HttpServletRequest httpRequest = (HttpServletRequest)request;
//        HttpServletResponse httpResponse = (HttpServletResponse)response;
//
//        //セッション獲得
//        HttpSession session = httpRequest.getSession();
//
//        //ログインしていない場合 ログイン情報のヌルチェック
//        if (session.getAttribute("loginUser") == null) {
//            //ここでエラーメッセージも用意する
//            String errorMessage = "ログインしてください！！";
//            session.setAttribute("errorMessages", errorMessage);
//            //ログイン画面表示したい→ログインサーブレットのdoGetを実行すれば表示されるからリダイレクト
//            httpResponse.sendRedirect("./login");
//        } else {
//            // サーブレットを実行
//            chain.doFilter(request, response);
//        }
//    }
//
//    @Override
//    public void init(FilterConfig config) {
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
