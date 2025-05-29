package com.example.ISG.filter;

import com.example.ISG.constfolder.ErrorMessage;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginFilter implements Filter {
    @Autowired
    HttpSession httpSession;

    //requestとresponseを引数として受け取り、チェーン内の次のフィルターまたはサーブレットにリクエストとレスポンスを渡す。
    //リクエストを型変換して、セッション情報を取得。
    // loggedInUserという属性が存在しなければ、セッションにエラーメッセージをセットし、ログインページにリダイレクトする。
    // （loggedInUserはログイン成功時にセッションに入れられている設定）
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse)response;

        httpSession = req.getSession(false);
        //ログインしていない場合セッションがNullかloginUserが存在しない
        if (httpSession != null && httpSession.getAttribute("loginUser") != null){
            chain.doFilter(req,res);
        } else {
            httpSession = req.getSession(true);
            //エラーメッセージをセット
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(ErrorMessage.E0024);
            httpSession.setAttribute("errorMessages", errorMessages);
            //トップにリダイレクト
            res.sendRedirect("/");
        }
    }

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void destroy() {
    }
}