package com.example.ISG.controller;

import com.example.ISG.controller.form.CommentForm;
import com.example.ISG.controller.form.MessageForm;
import com.example.ISG.repository.entity.Message;
import com.example.ISG.service.CommentService;
import com.example.ISG.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@Controller
public class TopController {

    @Autowired
    MessageService messageService;

    @Autowired
    CommentService commentService;

    @GetMapping
    public ModelAndView top(@RequestParam(name="start", required=false) String start,
                            @RequestParam(name="end", required=false) String end,String category) throws ParseException {
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得した値を入れる箱（contentData）をつくってサービスに渡しています
        List<MessageForm> contentData = messageService.findAllMessage(start ,end);
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
}
