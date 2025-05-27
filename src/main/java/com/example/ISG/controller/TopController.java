package com.example.ISG.controller;

import com.example.ISG.controller.form.*;
import com.example.ISG.repository.entity.Message;
import com.example.ISG.service.CommentService;
import com.example.ISG.service.MessageService;
import com.example.ISG.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.text.ParseException;
import java.util.List;

@Controller
public class TopController {

    @Autowired
    MessageService messageService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView top(@RequestParam(name="start", required=false) String start,
                            @RequestParam(name="end", required=false) String end,String category) throws ParseException {
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得した値を入れる箱（contentData）をつくってサービスに渡しています
        List<MessageForm> contentData = messageService.findAllMessage(start,end,category);
        List<CommentForm> commentData = commentService.findAllComment();

        // 画面遷移先を指定 「現在のURL」/top へ画面遷移することを指定します。
        mav.setViewName("/top");
        // 投稿データオブジェクトを先ほどのcontentDataをModelAndView型の変数mavへ格納します。
        mav.addObject("contents", contentData);
        mav.addObject("comments", commentData);
        mav.addObject("start",start);
        mav.addObject("end", end);


        /* 変数mavを戻り値として返します。 */
        return mav;
    }

    /*
     * ユーザ編集画面表示処理
     */
    @GetMapping({"/user/edit/", "/user/edit/{id}"})
    public ModelAndView editUser(@PathVariable(required = false) String id,
//                               @AuthenticationPrincipal LoginUserDetails loginUser,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        UserForm userForm = null;
        if(RequestContextUtils.getInputFlashMap(request) != null) {
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
            redirectAttributes.addFlashAttribute("errorMessages","不正なパラメータが入力されました");
            return new ModelAndView("redirect:/admin");
        }
        // 支社情報を全件取得
        List<BranchForm> branchDate = branchService.findAllBranch();
        // 部署情報を全件取得
        List<DepartmentForm> departmentDate = departmentService.findAllDepartment();
        // 画面遷移先を指定
        mav.setViewName("edit");
        // ログインユーザーデータオブジェクトを保持
        mav.addObject("loginUserId", loginUser.getUserId());
        // 支社・部署データオブジェクトを保持
        mav.addObject("branches", branchDate);
        mav.addObject("departments", departmentDate);
        // 編集対象のユーザをセット
        mav.addObject("formModel", userForm);
        return mav;
    }
}
