package com.example.ISG.service;

import com.example.ISG.controller.form.MessageForm;
import com.example.ISG.repository.MessageRepository;
import com.example.ISG.repository.UserRepository;
import com.example.ISG.repository.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    /*
     * 新規投稿登録処理（鈴木）
     */
    public void addMessage(MessageForm messageForm){
        //型をForm→Entityに変換する用メソッド
        Message message = setMessage(messageForm);

        //登録処理
        messageRepository.save(message);
    }

    private Message setMessage(MessageForm messageForm){
        Message message = new Message();
        message.setId(messageForm.getId());
        message.setTitle(messageForm.getTitle());
        message.setText(messageForm.getText());
        message.setCategory(messageForm.getCategory());
        message.setUserId(messageForm.getUserId());
        message.setCreatedDate(messageForm.getCreatedDate());
        message.setUpdatedDate(messageForm.getUpdatedDate());
        return message;
    }
}
