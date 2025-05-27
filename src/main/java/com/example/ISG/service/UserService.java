package com.example.ISG.service;

import com.example.ISG.controller.form.UserForm;
import com.example.ISG.repository.UserRepository;
import com.example.ISG.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    /*
     * ログイン処理（鈴木）
     */
    public UserForm findLoginUser(UserForm loginUser) {
        // パスワード暗号化
        String encPassword = passwordEncoder.encode(loginUser.getPassword());

        //DBへのselect処理
        List<User> results = userRepository.findByAccountAndPassword(loginUser.getAccount(), encPassword);
        //DBから取得したresultsの型をEntity→Formに変換する用メソッド
        UserForm saveUserForm = setUserForm(results);
        return saveUserForm;
    }

    private UserForm setUserForm(List<User> results){
        UserForm saveUserForm = new UserForm();
        User result = results.get(0);
        saveUserForm.setAccount(result.getAccount());
        saveUserForm.setPassword(result.getPassword());
        saveUserForm.setName(result.getName());
        saveUserForm.setBranchId(result.getBranchId());
        saveUserForm.setDepartmentId(result.getDepartmentId());
        saveUserForm.setIsStoppedId(result.getIsStoppedId());
        saveUserForm.setCreatedDate(result.getCreatedDate());
        saveUserForm.setUpdatedDate(result.getUpdatedDate());
        return saveUserForm;
    }


















}
