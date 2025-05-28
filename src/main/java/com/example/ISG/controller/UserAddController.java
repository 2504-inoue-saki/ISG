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

import java.time.LocalDateTime;
import java.util.List;

import static com.example.ISG.constfolder.ErrorMessage.E0001;

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

//        //チェック系
//        if(result.hasErrors()){
//
//            //リクエスト情報をセット
//            mav.addObject("addUser",addUser);
//            //エラーメッセージをセット
//            mav.addObject("errorMessage", E0001);
//            // 画面遷移先を指定
//            mav.setViewName("/userAdd");
//            return mav;
//        }

        //今の時間をセット
        LocalDateTime now = LocalDateTime.now();
        addUser.setCreatedDate(now);
        addUser.setUpdatedDate(addUser.getCreatedDate());

        //String型の中身チェック（この後intにしたいから）

        //型変更 String→int してaddUserにセット
        addUser.setBranchId(Integer.parseInt(addUser.getStringBranchId()));
        addUser.setDepartmentId(Integer.parseInt(addUser.getStringDepartmentId()));

        //入力された情報を登録しに行く
        userService.addUser(addUser);
        //ユーザー管理画面へリダイレクト
        return new ModelAndView("redirect:/???");
    }
}

