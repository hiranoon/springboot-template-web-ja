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
 * 国籍(nationalities)テーブルの Entity クラス.
 * @author hirano
 */
@Entity
@Table(name = "nationalities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nationality {
    /** ID. */
    @Id
    private Integer id;

    /** 名称. */
    @Column(nullable = false)
    private String name;

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
