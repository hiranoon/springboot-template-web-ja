package hoge.fuga.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー(users)テーブルの Entity クラス.
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
    private LocalDateTime currentSignInAt;

    /** 最新ログインIPアドレス. */
    private String currentSignInIp;

    /** 認証失敗回数. */
    @Column(nullable = false)
    private Integer failedAttempts;

    /** アカウントロック日時. */
    private LocalDateTime lockedAt;

    /** 登録日時. */
    private LocalDateTime createdAt;

    /** 更新日時. */
    private LocalDateTime updatedAt;

    /** バージョン. */
    @Version
    private Integer version;

    // 【解説】
    // 登録日時、更新日時を自動でセットするための機能です.
    // 各 Entity クラスに実装していますが、
    // これらのカラムを保持するのがルールとして決められるのであれば、
    // AbstractEntity のような親クラスに持ってもいいと思います.
    // Listner クラスを作って、 @EntityListeners(HogeLintener.class) と指定する方法もあります.
    // 参考) https://gist.github.com/php-coder/1391084

    /**
     * 登録日時に現在日時をセットします.
     */
    @PrePersist
    void touchCreatedAt() {
        setCreatedAt(LocalDateTime.now());
    }

    /**
     * 更新日時に現在日時をセットします.
     */
    @PreUpdate
    void touchUpdatedAt() {
        setUpdatedAt(LocalDateTime.now());
    }
}
