package hoge.fuga.domain;

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
 * 国籍(nationalities)テーブルの Domain クラス.
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
