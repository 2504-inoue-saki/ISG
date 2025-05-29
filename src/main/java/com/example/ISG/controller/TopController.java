package com.example.ISG.controller;

import com.example.ISG.controller.form.*;
import com.example.ISG.repository.entity.Comment;
import com.example.ISG.repository.entity.Message;
import com.example.ISG.service.CommentService;
import com.example.ISG.service.MessageService;
import com.example.ISG.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TopController {

    @Autowired
    MessageService messageService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;
    //エラー文を表示するため
    @Autowired
    HttpSession session;

    @GetMapping
    public ModelAndView top(@RequestParam(name = "start", required = false) String start,
                            @RequestParam(name = "end", required = false) String end, String category) throws ParseException {
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得した値を入れる箱（contentData）をつくってサービスに渡しています
        List<MessageForm> contentData = messageService.findAllMessage(start, end, category);
        List<CommentForm> commentData = commentService.findAllComment();

        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null){
            mav.addObject("loginUser",loginUser);
        }

        Object errorMessage = session.getAttribute("errorMessages");
        if (errorMessage != null) {
            mav.addObject("errorMessages", errorMessage);
            session.removeAttribute("errorMessages");
        }
        // 画面遷移先を指定 「現在のURL」/top へ画面遷移することを指定します。
        mav.setViewName("/top");
        // 投稿データオブジェクトを先ほどのcontentDataをModelAndView型の変数mavへ格納します。
        mav.addObject("contents", contentData);
        mav.addObject("comments", commentData);
        mav.addObject("commentForm", new CommentForm());
        mav.addObject("start", start);
        mav.addObject("end", end);


        /* 変数mavを戻り値として返します。 */
        return mav;
    }

    /*
     *コメント投稿
     */
    @PostMapping("/comment")
    public ModelAndView addComment(@Validated @ModelAttribute("commentForm") CommentForm commentForm, BindingResult result, MessageForm message
    ) throws ParseException {
        //formにエラーがあれば条件分岐
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("/top");
            //エラーの入ったfoamを引数なしでおくる（topに似た処理）
            List<MessageForm> contentData = messageService.findAllMessage(null, null, null);
            List<CommentForm> commentData = commentService.findAllComment();

            mav.addObject("contents", contentData);
            mav.addObject("comments", commentData);
            // エラーがある form を渡す（コメントフォーム）
            mav.addObject("commentForm", commentForm);
            return mav;
        }

        // 返信をテーブルに格納
        commentService.saveComment(commentForm);
//        レポートIDに対応した投稿を取得//
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     *コメント削除処理
     */
    @DeleteMapping("/deleteComment/{id}")
    public ModelAndView deleteComment(@PathVariable Integer id) {
        // 投稿をテーブルに格納
        commentService.deleteComment(id);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
}
