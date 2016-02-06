package jp.co.flag_systems.springboot_template.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hoges テーブルの Domain クラス.
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

    /**
     * 国籍.
     */
    @Column(nullable = false)
    private Integer fugaId;
}
