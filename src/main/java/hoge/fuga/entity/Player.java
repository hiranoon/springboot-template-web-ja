package hoge.fuga.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

import hoge.fuga.common.code.CodeConsts;
import hoge.fuga.common.code.CodeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 選手(players)テーブルの Entity クラス.
 * @author hirano
 */
@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    /** ID. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 背番号. */
    @Column(nullable = false)
    private Integer squadNumber;

    /** 名前. */
    @Column(nullable = false)
    private String name;

    /** ポジションコード. */
    @Column(nullable = false)
    private String positionCode;

    /** 国籍. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "nationality_id") // 外部キー
    private Nationality nationality;

    /** 登録日時. */
    private LocalDateTime createdAt;

    /** 更新日時. */
    private LocalDateTime updatedAt;

    /** バージョン. */
    @Version
    private Integer version;

    /**
     * ポジションコードからポジション名称を取得して返却します.
     * @return ポジション名称
     */
    public String getPositionCodeName() {
        // CodeConsts に定義されたコードの名称を取得します.
        return CodeUtils.getCodeName(CodeConsts.PositionCode.class, getPositionCode());
    }

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
