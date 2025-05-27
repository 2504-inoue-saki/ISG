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
        saveUserForm.setIsStopped(result.getIsStopped());
        saveUserForm.setCreatedDate(result.getCreatedDate());
        saveUserForm.setUpdatedDate(result.getUpdatedDate());
        return saveUserForm;
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
//        if(!reqUser.getPassword().isBlank()) {
//            user.setPassword(passwordEncoder.encode(reqUser.getPassword()));
//        } else {
//            user.setPassword(editUser(reqUser.getId()).getPassword());
//        }
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
        if(results.get(0) != null){
            List<UserForm> users = setUserForm(results);
            return users.get(0);
        }else{
            return null;
        }
    }
}
