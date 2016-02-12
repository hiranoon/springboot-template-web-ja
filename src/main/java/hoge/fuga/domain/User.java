package hoge.fuga.domain;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー(users)テーブルの Domain クラス.
 * @author hirano
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /** ユーザー名. */
    @Id
    private String username;

    /** 暗号化済パスワード. */
    @Column(nullable = false)
    private String encryptedPassword;

    /** ログイン回数. */
    @Column(nullable = false)
    private Integer signInCount;

    /** 最新ログイン日時. */
    private LocalTime currentSignInAt;

    /** 最新ログアウト日時. */
    private LocalTime lastSignInAt;

    /** 最新ログインIPアドレス. */
    private String currentSignInIp;

    /** 最新ログアウトIPアドレス. */
    private String lastSignInIp;

    /** 登録日時. */
    private LocalTime createdAt;

    /** 更新日時. */
    private LocalTime updatedAt;
}
