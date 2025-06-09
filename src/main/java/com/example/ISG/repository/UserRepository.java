package com.example.ISG.repository;

import com.example.ISG.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /*
     * ログイン処理（鈴木）
     */
    public List<User> findByAccount(String account);

    // 新規登録時の重複チェック
    boolean existsByAccount(String account);
    // 更新時の重複チェック
    boolean existsByAccountAndIdNot(String account, int id);

    // ユーザを全件取得
    @Query("SELECT " +
            "u.id as id, " +
            "u.account as account, " +
            "u.name as name, " +
            "u.branchId, " +
            "u.departmentId, " +
            "u.isStopped as isStopped, " +
            "b.name, " +
            "d.name " +
            "FROM User u " +
            "INNER JOIN Branch b ON u.branchId = b.id " +
            "INNER JOIN Department d ON u.departmentId = d.id")
    public List<Object[]> findAllUser();

    // ユーザ停止状態（＝ステータス）の変更処理
    @Modifying
    @Query("UPDATE User u SET u.isStopped = :isStopped, u.updatedDate = CURRENT_TIMESTAMP WHERE u.id = :id")
    public void saveStatus(@Param("isStopped")short isStopped, @Param("id")Integer id);

}
