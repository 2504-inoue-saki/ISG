package com.example.ISG.controller;

import com.example.ISG.controller.form.UserForm;
import com.example.ISG.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    private static final String E0001 = "アカウントを入力してください";
    private static final String E0002 = "パスワードを入力してください";
    private static final String E0003 = "ログインに失敗しました";

    /*
     * ログイン画面処理（鈴木）
     */
    @PostMapping("/login")
    //リクエストパラメータの取得
    public ModelAndView newContent(@Valid @ModelAttribute("loginUser") UserForm loginUser, BindingResult result) {
        //リクエストパラメータの入力チェック


        // 入力されたアカウントとパスワードが存在するか確認しに行く
        UserForm loginData = userService.findLoginUser(loginUser);

        //アカウントが無い場合またはユーザーが停止している場合はエラーメッセージを今の画面に表示
        if(loginData == null || loginData.getIsStoppedId() == 1){
            ModelAndView mav = new ModelAndView();
            mav.addObject("errorMessage",E0003);
            return mav;
        }

        //無事にアカウントがあった場合はログイン情報を保持＆ホーム画面へリダイレクト
        session.setAttribute("loginUsers", loginData);
        return new ModelAndView("redirect:/");
    }
}