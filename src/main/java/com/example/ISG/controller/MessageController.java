package com.example.ISG.controller;

import com.example.ISG.constfolder.ErrorMessage;
import com.example.ISG.controller.form.MessageForm;
import com.example.ISG.controller.form.UserForm;
import com.example.ISG.service.MessageService;
import com.example.ISG.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {
    //インスタンスの注入
    @Autowired
    MessageService messageService;
    @Autowired
    HttpSession session;

    // エラーメッセージ
    private static final String E0001 = "アカウントを入力してください";
    private static final String E0002 = "パスワードを入力してください";
    private static final String E0003 = "ログインに失敗しました";

    /*
     * 新規投稿画面表示（鈴木）
     */
    @GetMapping("/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login");
        return mav;
    }

    /*
     * 新規投稿登録処理（鈴木）
     */
    @PostMapping("/add")
    //リクエストパラメータの取得
    public ModelAndView addContent(@Valid @ModelAttribute("message") MessageForm messageForm, BindingResult result) {
        ModelAndView mav = new ModelAndView();

        //リクエストパラメータの入力チェック
//        if(result.hasErrors()){
//            //エラーメッセージをセット
//            mav.addObject("errorMessage", ErrorMessage.E0001);
//            // 画面遷移先を指定
//            mav.setViewName("/login");
//            return mav;
//        }

        //今の時間をセット
        LocalDateTime now = LocalDateTime.now();
        messageForm.setCreatedDate(now);
        messageForm.setUpdatedDate(messageForm.getCreatedDate());

        //投稿者IDのセット
        UserForm loginUser = (UserForm) session.getAttribute("loginUser");
        messageForm.setUserId(loginUser.getId());

        // 入力された情報を登録しに行く
        messageService.addMessage(messageForm);

        //ホーム画面へリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 投稿削除処理
     */
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable Integer id) {
        // 投稿をテーブルに格納
        messageService.deleteMessage(id);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
}
