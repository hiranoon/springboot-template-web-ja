package hoge.fuga.domain;

import java.sql.Timestamp;

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
    private Timestamp currentSignInAt;

    /** 最新ログアウト日時. */
    private Timestamp lastSignInAt;

    /** 最新ログインIPアドレス. */
    private String currentSignInIp;

    /** 最新ログアウトIPアドレス. */
    private String lastSignInIp;

    /** 認証失敗回数. */
    @Column(nullable = false)
    private Integer failedAttempts;

    /** アカウントロック日時. */
    private Timestamp lockedAt;

    /** 登録日時. */
    private Timestamp createdAt;

    /** 更新日時. */
    private Timestamp updatedAt;
}
