package jp.co.flag_systems.springboot_template.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hoges テーブルの Domain クラス.
 * このサンプルプログラムでは、選手のデータを表します.
 * @author hirano
 */
@Entity
@Table(name = "hoges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hoge {
    /**
     * ID.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 背番号.
     */
    @Column(nullable = false)
    private Integer squadNumber;

    /**
     * 名前.
     */
    @Column(nullable = false)
    private String name;

    /**
     * ポジションコード.
     */
    @Column(nullable = false)
    private Integer positionCode;

//    /**
//     * 国籍.
//     */
//    @Column(nullable = false)
//    private Integer fugaId;

    /**
     * {@link Fuga}.
     */
    // LAZY  : 必要になったタイミングで Fuga の問い合わせを行います。
    // EAGER : 予め Fuga の問い合わせを行っておきます。
    // 問い合わせるタイミングの違いだけで、結合するか、キャッシュを利用するか、
    // の違いはありません。
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            nullable = false,
            name = "fuga_id",            // 外部キー(結合元のカラム名)
            referencedColumnName = "id") // 外部キー(結合先のカラム名) ※結合先と異なる場合にのみ指定が必要
    private Fuga fuga;
}
