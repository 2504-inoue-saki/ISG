package com.example.ISG.controller;

import com.example.ISG.controller.form.BranchForm;
import com.example.ISG.controller.form.DepartmentForm;
import com.example.ISG.controller.form.UserForm;
import com.example.ISG.dto.UserBranchDepartment;
import com.example.ISG.service.BranchService;
import com.example.ISG.service.DepartmentService;
import com.example.ISG.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;

@Controller
public class UserManageController {
    @Autowired
    UserService userService;

    @Autowired
    BranchService branchService;

    @Autowired
    DepartmentService departmentService;

    /*
     * ユーザ管理画面表示処理
     */
    @GetMapping("/userAdmin")
    public ModelAndView adminView(LoginController loginUser) { // ここを元に戻す
        ModelAndView mav = new ModelAndView();
        mav.setViewName("userAdmin");

        // ユーザー情報を取得してモデルに追加
        List<UserBranchDepartment> userData = userService.findUserWithBranchWithDepartment();
        mav.addObject("users", userData);

        // ログインユーザーIDをモデルに追加
        // loginUserがnullでないことを確認
        if (loginUser != null) {
            // mav.addObject("loginUserId", loginUser.getUserId());
            mav.addObject("loginUserId", -1);
        }

        return mav;
    }

    /*
     * ユーザ復活・停止処理
     */
    @PutMapping("/update-isStopped/{id}")
    public ModelAndView changeStatus(@PathVariable Integer id,
                                     @ModelAttribute("isStopped") short isStopped) {
        userService.saveIsStopped(id, isStopped);
        return new ModelAndView("redirect:/userAdmin");
    }

}
