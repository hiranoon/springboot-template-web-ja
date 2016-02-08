package hoge.fuga.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hoge.fuga.common.constant.CodeConsts;
import hoge.fuga.common.constant.CodeUtils;
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
    // LAZY  : 必要になったタイミングで Nationality の問い合わせを行います.
    // EAGER : 予め Nationality の問い合わせを行っておきます.
    // 問い合わせるタイミングの違いだけで、結合するか、キャッシュを利用するか、の違いはありません.
    @ManyToOne(fetch = FetchType.LAZY)
    // 外部キー(結合元のカラム名) ※結合先のカラム名が異なる場合は referencedColumnName の指定も必要
    @JoinColumn(nullable = false, name = "nationality_id")
    private Nationality nationality;

    // 一覧にコードの名称を出力したい場合などには、コード名称のメソッドを用意します.
    // "get + コードのプロパティ名 + Name()" という命名にします.
    // html(thymeleaf) 側では、
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
}
