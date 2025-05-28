package com.example.ISG.service;

import com.example.ISG.controller.form.UserForm;
import com.example.ISG.repository.UserRepository;
import com.example.ISG.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return (UserForm) setUserForm(results);
    }

    private List<UserForm> setUserForm(List<User> results) {
        List<UserForm> users = new ArrayList<>();

        for (User value : results) {
            UserForm user = new UserForm();
            user.setId(value.getId());
            user.setAccount(value.getAccount());
            user.setPassword(value.getPassword());
            user.setName(value.getName());
            user.setBranchId(value.getBranchId());
            user.setDepartmentId(value.getDepartmentId());
            user.setIsStopped(value.getIsStopped());
            user.setCreatedDate(value.getCreatedDate());
            user.setUpdatedDate(value.getUpdatedDate());
            users.add(user);
        }
        return users;
    }

    /*
     * ユーザー登録処理（鈴木）
     */
    public void addUser(UserForm userForm) {
        //引数の型をForm→Entityに変換する用メソッド
        User user = setUserEntity(userForm);
        //ユーザー情報を登録
        userRepository.save(user);
    }

    public void saveIsStopped(Integer id, short isStopped) {
        userRepository.saveStatus(isStopped, id);
    }

    /*
     * レコード保存
     * 新規作成・編集どちらでも使用
     */
    public void saveUser(UserForm reqUser) {
        User saveUser = setUserEntity(reqUser);
        userRepository.save(saveUser);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private User setUserEntity(UserForm reqUser) {
        User user = new User();
        user.setId(reqUser.getId());
        user.setAccount(reqUser.getAccount());
        // パスワードの変換処理？
        if(!reqUser.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(reqUser.getPassword()));
        } else {
            user.setPassword(editUser(reqUser.getId()).getPassword());
        }
        user.setName(reqUser.getName());
        user.setBranchId(reqUser.getBranchId());
        user.setDepartmentId(reqUser.getDepartmentId());
        user.setIsStopped(reqUser.getIsStopped());
        return user;
    }

    /*
     * レコード1件取得
     */
    public UserForm editUser(int id) {
        List<User> results = new ArrayList<>();
        results.add(userRepository.findById(id).orElse(null));
        if (results.get(0) != null) {
            List<UserForm> users = setUserForm(results);
            return users.get(0);
        } else {
            return null;
        }
    }

    /*
     * アカウント重複チェック
     */
    public boolean existsUserByAccount(String account, Integer id) {
        if (id == null) {
            // IDがnullの場合（新規登録時）は、単にアカウントが存在するかチェック
            return userRepository.existsByAccount(account);
        } else {
            // IDが指定されている場合（更新時）は、そのID以外のユーザーでアカウントが存在するかチェック
            return userRepository.existsByAccountAndIdNot(account, id);
        }
    }
}
