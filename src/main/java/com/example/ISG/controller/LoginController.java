package com.example.ISG.controller;

import com.example.ISG.controller.form.UserForm;
import com.example.ISG.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {
    //インスタンスの注入
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    // エラーメッセージ
    private static final String E0003 = "ログインに失敗しました";

    /*
     * ログイン画面処理（鈴木）
     */
    @PostMapping("/login")
    //リクエストパラメータの取得
    public ModelAndView newContent(@ModelAttribute("loginUser") UserForm loginUser) {
        //リクエストパラメータの有無チェック

        // 入力された情報がDBにあるか確認しにいく
        UserForm loginData = userService.findLoginUser(loginUser);

        //アカウントが無い場合またはユーザーが停止している場合はエラーメッセージを今の画面に表示
        if(loginData == null || loginData.getIsStopped() == 1){
            ModelAndView mav = new ModelAndView();
            mav.addObject("errorMessage",E0003);
            return mav;
        }

        //無事にアカウントがあった場合はログイン情報を保持＆ホーム画面へリダイレクト

        return new ModelAndView("redirect:/");
    }
}