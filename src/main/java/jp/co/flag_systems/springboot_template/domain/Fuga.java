package jp.co.flag_systems.springboot_template.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * fugas テーブルの Domain クラス.
 * このサンプルプログラムでは、国籍のデータを表します.
 * @author hirano
 */
@Entity
@Table(name = "fugas")
//TODO リクエスト毎ではなくアプリケーションスコープでキャッシュできないか？
@Cacheable // キャッシュを有効にする
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fuga {
    /**
     * ID.
     */
    @Id
    private Integer id;

    /**
     * 名前.
     */
    @Column(nullable = false)
    private String name;
}
