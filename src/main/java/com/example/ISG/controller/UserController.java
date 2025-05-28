package com.example.ISG.controller;

import com.example.ISG.controller.form.BranchForm;
import com.example.ISG.controller.form.DepartmentForm;
import com.example.ISG.controller.form.UserForm;
import com.example.ISG.dto.UserBranchDepartment;
import com.example.ISG.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class UserController {
@Autowired UserService userService;
    /*
     * ユーザ管理画面表示処理
     */
    @GetMapping("/userAdmin")
    public ModelAndView adminView() { // ここを元に戻す
        ModelAndView mav = new ModelAndView();
        mav.setViewName("userAdmin");

        // ユーザー情報を取得してモデルに追加
        List<UserBranchDepartment> userData = userService.findUserWithBranchWithDepartment();
        mav.addObject("users", userData);

        // ログインユーザーIDをモデルに追加
        // loginUserがnullでないことを確認
        //if (loginUser != null) {
            // mav.addObject("loginUserId", loginUser.getUserId());
            mav.addObject("loginUserId", -1);
        //}

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

        /*
     * ユーザ編集画面表示処理
     */
//    @GetMapping({"/user/edit/", "/user/edit/{id}"})
//    public ModelAndView editUser(@PathVariable(required = false) String id,
//                                 @AuthenticationPrincipal LoginUserDetails loginUser,
//                                 RedirectAttributes redirectAttributes,
//                                 HttpServletRequest request) {
//        ModelAndView mav = new ModelAndView();
//        UserForm userForm = null;
//        if(RequestContextUtils.getInputFlashMap(request) != null) {
//            userForm = (UserForm) RequestContextUtils.getInputFlashMap(request).get("formModel");
//        } else {
//            //URLチェック
//            if (!StringUtils.isBlank(id) && id.matches("^[0-9]*$")) {
//                int intId = Integer.parseInt(id);
//                // 編集するユーザを取得
//                userForm = userService.editUser(intId);
//            }
//        }
//        if (userForm == null) {
//            redirectAttributes.addFlashAttribute("errorMessages","不正なパラメータが入力されました");
//            return new ModelAndView("redirect:/admin");
//        }
//        // 支社情報を全件取得
//        List<BranchForm> branchDate = branchService.findAllBranch();
//        // 部署情報を全件取得
//        List<DepartmentForm> departmentDate = departmentService.findAllDepartment();
//        // 画面遷移先を指定
//        mav.setViewName("edit");
//        // ログインユーザーデータオブジェクトを保持
//        mav.addObject("loginUserId", loginUser.getUserId());
//        // 支社・部署データオブジェクトを保持
//        mav.addObject("branches", branchDate);
//        mav.addObject("departments", departmentDate);
//        // 編集対象のユーザをセット
//        mav.addObject("formModel", userForm);
//        return mav;
//    }
}
