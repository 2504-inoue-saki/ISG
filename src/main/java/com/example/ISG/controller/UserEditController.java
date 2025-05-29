package com.example.ISG.controller;

import com.example.ISG.controller.form.BranchForm;
import com.example.ISG.controller.form.DepartmentForm;
import com.example.ISG.controller.form.UserForm;
import com.example.ISG.service.BranchService;
import com.example.ISG.service.DepartmentService;
import com.example.ISG.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserEditController {
    @Autowired
    UserService userService;

    @Autowired
    BranchService branchService;

    @Autowired
    DepartmentService departmentService;

    /*
     * ユーザ編集画面表示処理
     */
    @GetMapping({"/user/edit/", "/user/edit/{id}"})
    public ModelAndView editUser(@PathVariable(required = false) String id,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        UserForm userForm = null;
        if (RequestContextUtils.getInputFlashMap(request) != null) {
            userForm = (UserForm) RequestContextUtils.getInputFlashMap(request).get("formModel");
        } else {
            //URLチェック
            if (!StringUtils.isBlank(id) && id.matches("^[0-9]*$")) {
                int intId = Integer.parseInt(id);
                // 編集するユーザを取得
                userForm = userService.editUser(intId);
            }
        }
        if (userForm == null) {
            redirectAttributes.addFlashAttribute("errorMessages", "不正なパラメータが入力されました");
            return new ModelAndView("redirect:/userAdmin");
        }
        // 支社情報を全件取得
        List<BranchForm> branchData = branchService.findAllBranch();
        // 部署情報を全件取得
        List<DepartmentForm> departmentData = departmentService.findAllDepartment();
        // 画面遷移先を指定
        mav.setViewName("edit");
        // ログインユーザーデータオブジェクトを保持
        //mav.addObject("loginUserId", loginUser.getUserId());
        // 支社・部署データオブジェクトを保持
        mav.addObject("branches", branchData);
        mav.addObject("departments", departmentData);
        // 編集対象のユーザをセット
        mav.addObject("userForm", userForm);
        return mav;
    }

    /*
     * ユーザ編集処理
     */
//    @PutMapping("/user/update/{id}")
//    public ModelAndView updateUser(@PathVariable Integer id,
//                                   @ModelAttribute("formModel") @Validated UserForm userForm,
//                                   BindingResult result,
//                                   RedirectAttributes redirectAttributes) {
//        // UrlParameterのidを更新するentityにセット
//        userForm.setId(id);
//        List<String> errorMessages = new ArrayList<>();
//        if (result.hasErrors()) {
//            for (FieldError error : result.getFieldErrors()) {
//                errorMessages.add(error.getDefaultMessage());
//            }
//        }
//        if(!userForm.getAccount().isBlank()) {
//            // アカウントの半角英数字かつ文字数チェック
//            if(!userForm.getAccount().matches("^[a-zA-Z0-9]{6,20}$")) {
//                errorMessages.add(E0014);
//            }
//        }
//        if(!userForm.getPassword().isBlank()) {
//            // パスワードの半角文字かつ文字数チェック
//            if(!userForm.getPassword().matches("^[!-~]{6,20}$")) {
//                errorMessages.add(E0017);
//            }
//        }
//        // パスワードと確認用パスワードの一致チェック
//        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
//            errorMessages.add(E0018);
//        }
//        // 支社IDと部署IDの妥当性チェック
//        if (userForm.getBranchId() != null && userForm.getDepartmentId() != null) {
//            if (userForm.getBranchId() == 1) {
//                if (userForm.getDepartmentId() != 1 && userForm.getDepartmentId() != 2) {
//                    errorMessages.add(E0023);
//                }
//            } else {
//                if (userForm.getDepartmentId() != 3 && userForm.getDepartmentId() != 4) {
//                    errorMessages.add(E0023);
//                }
//            }
//        }
//        // アカウント重複チェック
//        if (userService.existsUserByAccount(userForm.getAccount(),userForm.getId())) {
//            errorMessages.add(E0015);
//        }
//        if(!errorMessages.isEmpty()){
//            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
//            redirectAttributes.addFlashAttribute("formModel", userForm);
//            return new ModelAndView("redirect:/user/edit/" + id);
//        }
//        // 編集したユーザを更新
//        userService.saveUser(userForm);
//        // rootへリダイレクト
//        return new ModelAndView("redirect:/userAdmin");
//    }
}
