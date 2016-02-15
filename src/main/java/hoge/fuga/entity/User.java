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

    /**
     * 登録日時に現在日時をセットします.
     * {@link @PrePersist} アノテーションにより登録時に自動実行されます.
     */
    @PrePersist
    void touchCreatedAt() {
        setCreatedAt(LocalDateTime.now());
    }

    /**
     * 更新日時に現在日時をセットします.
     * {@link @PreUpdate} アノテーションにより更新時に自動実行されます.
     */
    @PreUpdate
    void touchUpdatedAt() {
        setUpdatedAt(LocalDateTime.now());
    }
}
