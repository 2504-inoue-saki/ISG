package com.example.ISG.service;

import com.example.ISG.controller.form.CommentForm;
import com.example.ISG.repository.CommentRepository;
import com.example.ISG.repository.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    /*
     * レコード全件取得処理
     */
    public List<CommentForm> findAllComment() {
//        findAllで実行されている処理はSQL文の「select * from report;」のようなもの
        //ennity型
        List<Comment> results = commentRepository.findAllByOrderByUpdatedDateDesc();
//        setReportFormメソッドでEntity→Formに詰め直して、Controllerに戻しています。
//        これはEntityはデータアクセス時の入れ物、FormはViewへの入出力時に使用する入れ物と役割を分けているためです
        List<CommentForm> comments = setCommentForm(results);
        return comments;
    }

    /*
     * DBから取得したデータをformに設定
     */
    private List<CommentForm> setCommentForm(List<Comment> results) {
//        ビューに返すためにはフォームに移し返さないといけない
        List<CommentForm> comments = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            CommentForm comment = new CommentForm();
            Comment result = results.get(i);
            comment.setId(result.getId());
            comment.setText(result.getText());
            comment.setUserId(Integer.parseInt(result.getUserId()));
            comment.setMessageId(Integer.parseInt(result.getMessageId()));
            comment.setCreatedDate(result.getCreatedDate());
            comment.setUpdatedDate(result.getUpdatedDate());
            comments.add(comment);
        }
        return comments;
    }

    /*
     * コメント投稿
     */
    public void saveComment(CommentForm commentForm) throws ParseException {
        Comment saveComment = setCommentEntity(commentForm);
        //saveメソッドはテーブルに新規投稿をinsert・updateするような処理
        commentRepository.save(saveComment);
        //戻り値はなし
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Comment setCommentEntity(CommentForm reqComment) throws ParseException {
        Comment comment = new Comment();
        comment.setId(reqComment.getId());
        comment.setText(reqComment.getText());
        comment.setUserId(String.valueOf(reqComment.getUserId()));
        comment.setMessageId(String.valueOf(reqComment.getMessageId()));
//        createdDateとupdatedDateはDBで勝手に追加される
        return comment;
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}
