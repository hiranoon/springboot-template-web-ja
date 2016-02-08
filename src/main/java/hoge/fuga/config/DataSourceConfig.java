package hoge.fuga.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * データソースに関する設定クラス.
 * @author hirano
 */
@Configuration
public class DataSourceConfig {

    /** DataSourceに関するプロパティ設定. */
    @Autowired
    DataSourceProperties properties;

    /**
     * DataSource 設定.
     * @return {@link DataSource} オブジェクト
     */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        // 環境変数 JDBC_DATABASE_URL は、Heroku で定義されています。以下の様な形式です。
        // jdbc:mysql://<host>:<port>/<dbname>?user=<username>&password=<password>
        String url = System.getenv("JDBC_DATABASE_URL");
        DataSourceBuilder factory;
        if (url != null) {
            // JDBC_DATABASE_URL の設定を利用します。
            // 日本語文字化けが発生しないよう文字エンコーディング指定を追加します。
            factory = DataSourceBuilder
                    .create(this.properties.getClassLoader())
                    .url(url + "&characterEncoding=utf8");
        } else {
            // JDBC_DATABASE_URL が設定されていないため Heroku 以外の環境です。
            // プロパティファイルを利用します。
            factory = DataSourceBuilder
                    .create(this.properties.getClassLoader())
                    .url(this.properties.getUrl())
                    .username(this.properties.getUsername())
                    .password(this.properties.getPassword());
        }
        return factory.build();
    }

}
