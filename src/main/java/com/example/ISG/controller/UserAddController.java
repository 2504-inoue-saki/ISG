package com.example.ISG.controller;

import com.example.ISG.controller.form.UserForm;
import com.example.ISG.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.ISG.constfolder.ErrorMessage.*;

@Controller
public class UserAddController {
    //インスタンスの注入
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    /*
     * ユーザー登録画面表示（鈴木）
     */
    @GetMapping("/userAdd")
    public ModelAndView userAddContent() {
        ModelAndView mav = new ModelAndView();
        UserForm addUser = new UserForm();
        mav.addObject("addUser", addUser);
        mav.setViewName("/userAdd");
        return mav;
    }

    /*
     * ユーザー登録処理（鈴木）
     */
    @PostMapping("/userAddProcess")
    //リクエストパラメータの取得
    public ModelAndView userAddProcessContent(@Valid @ModelAttribute("addUser") UserForm addUser, BindingResult result) {
        ModelAndView mav = new ModelAndView();

        //リクエストパラメータの入力＆文字数チェック＆半角文字チェック
        if(result.hasErrors()){
            //エラーメッセージを入れる用のリストを作っておく
            List<String> errorMessages = new ArrayList<String>();
            //result.getFieldErrors()はresultの持つ全エラーを要素にしたリスト→型はList<FieldError>
            //要素を1つ取り出してerrorに代入して処理→全ての要素が尽きるまで繰り返す
            for(FieldError error : result.getFieldErrors()){
                //error.getDefaultMessage()で取得したエラーメッセージをリストに追加
                errorMessages.add(error.getDefaultMessage());
            }
            //エラーメッセージが詰まったリストをviewに送る
            mav.addObject("errorMessages", errorMessages);
            // 画面遷移先を指定
            mav.setViewName("/userAdd");
            return mav;
        }

        //重複チェック

        //妥当性チェック①パスワードとパスワード（確認用）が一致しない場合
        if (!addUser.getPassword().equals(addUser.getCheckPassword())) { // equals() を使う
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(E0018); // "パスワードとパスワード（確認用）が一致しません"
            mav.addObject("errorMessages", errorMessages);
            mav.setViewName("/userAdd");
            return mav;
        }

        //妥当性チェック②支社と部署の組み合わせが不正の場合
//        if (addUser.getPassword() != addUser.getCheckPassword()){
//            //エラーメッセージを入れる用のリストを作っておく
//            List<String> errorMessages = new ArrayList<String>();
//            errorMessages.add(E0018);
//
//            //エラーメッセージが詰まったリストをviewに送る
//            mav.addObject("errorMessages", errorMessages);
//            // 画面遷移先を指定
//            mav.setViewName("/userAdd");
//            return mav;
//        }

        //今の時間をセット
        LocalDateTime now = LocalDateTime.now();
        addUser.setCreatedDate(now);
        addUser.setUpdatedDate(addUser.getCreatedDate());

        //String型の中身チェック（この後intにしたいから）

//        //型変更 String→int してaddUserにセット
//        addUser.setBranchId(Integer.parseInt(addUser.getStringBranchId()));
//        addUser.setDepartmentId(Integer.parseInt(addUser.getStringDepartmentId()));

        //入力された情報を登録しに行く
        userService.addUser(addUser);
        //ユーザー管理画面へリダイレクト
        return new ModelAndView("redirect:/");
    }
}

