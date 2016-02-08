package hoge.fuga.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 国籍(nationalities)テーブルの Domain クラス.
 * @author hirano
 */
@Entity
@Table(name = "nationalities")
//TODO リクエスト毎ではなくアプリケーションスコープでキャッシュできないか？
@Cacheable // キャッシュを有効にする
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
}
