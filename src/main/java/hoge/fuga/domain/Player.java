package hoge.fuga.domain;

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
 * 選手(players)テーブルの Domain クラス.
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
    // 【解説】
    // FetchType には以下の2種類があります.
    // LAZY  : 必要になったタイミングで Nationality の問い合わせを行います.
    // EAGER : 予め Nationality の問い合わせを行っておきます.
    // 問い合わせるタイミングの違いだけで、結合するか、キャッシュを利用するか、の違いはありません.
    @ManyToOne(fetch = FetchType.LAZY)
    // 【解説】
    // 外部キー(結合元のカラム名)を指定しています.
    @JoinColumn(nullable = false, name = "nationality_id")
    private Nationality nationality;

    /** 登録日時. */
    private LocalDateTime createdAt;

    /** 更新日時. */
    private LocalDateTime updatedAt;

    /** バージョン. */
    @Version
    private Integer version;

    // 【解説】
    // 当該プロジェクトでは、 CodeConsts に定義されたコードの名称を取得するには以下のように行います.
    // Domain クラス(当該クラス)のプロパティはコードIDを保持し、コード名称を取得するメソッドを別途用意します.
    // "get + コードのプロパティ名 + Name()" という命名にします.
    // getter ですので、 html(thymeleaf) 側では、
    // <td th:text="${player.positionCodeName}">ゴールキーパー</td>
    // のような形で参照することができます.
    // コードIDからコード名称の変換には、 CodeUtils#getCodeName を利用します.
    /**
     * ポジションコードからポジション名称を取得して返却します.
     * @return ポジション名称
     */
    public String getPositionCodeName() {
        return CodeUtils.getCodeName(CodeConsts.PositionCode.class, getPositionCode());
    }

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
