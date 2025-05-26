package com.example.ISG.controller;

import com.example.ISG.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

public class UserController {
@Autowired UserService userService;
    /*
     * ユーザ管理画面表示処理
     */
    @GetMapping("/userAdmin")
    public ModelAndView adminView(@AuthenticationPrincipal LoginUserDetails loginUser) {
        ModelAndView mav = new ModelAndView();
        // 画面遷移先を指定
        mav.setViewName("userAdmin");
        List<UserBranchDepartment> userDate = userService.findUserWithBranchWithDepartment();
        mav.addObject("users", userDate);
        // ログインユーザーデータオブジェクトを保管
        mav.addObject("loginUserId", loginUser.getUserId());
        return mav;
    }

    /*
     * ユーザ復活・停止処理
     */
    @PutMapping("/update-isStopped/{id}")
    public ModelAndView updateStatus(@PathVariable Integer id,
                                     @ModelAttribute("isStopped") short isStopped) {
        userService.saveIsStopped(id, isStopped);
        return new ModelAndView("redirect:/userAdmin");
    }
}
