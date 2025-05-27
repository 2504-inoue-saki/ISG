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
     * ログイン画面表示（鈴木）
     */
    @GetMapping("/login")
    //リクエストパラメータの取得
    public ModelAndView loginContent() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login");
        return mav;
    }

    /*
     * ログイン処理（鈴木）
     */
    @PostMapping("/loginProcess")
    //リクエストパラメータの取得
    public ModelAndView loginContent(@Valid @ModelAttribute("loginUser") UserForm requestLogin, BindingResult result) {
        ModelAndView mav = new ModelAndView();

        //リクエストパラメータの入力チェック
        if(result.hasErrors()){
            //エラーメッセージをセット
            mav.addObject("errorMessage", E0001);
            // 画面遷移先を指定
            mav.setViewName("/login");
            return mav;
        }

        // 入力されたアカウントとパスワードが存在するか確認しに行く
        UserForm loginUser = userService.findLoginUser(requestLogin);

        //アカウントが無い場合またはユーザーが停止している場合はエラーメッセージを今の画面に表示
        if(loginData == null || loginData.getIsStopped() == 1){
            ModelAndView mav = new ModelAndView();
          
            mav.addObject("errorMessage",E0003);
            // 画面遷移先を指定
            mav.setViewName("/login");
            return mav;
        }

        //無事にアカウントがあった場合はログイン情報を保持＆ホーム画面へリダイレクト
        session.setAttribute("loginUser", loginUser);
        return new ModelAndView("redirect:/");
    }
}