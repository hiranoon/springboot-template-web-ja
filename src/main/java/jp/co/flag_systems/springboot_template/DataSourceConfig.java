package jp.co.flag_systems.springboot_template;

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
        String url = System.getenv("DATABASE_URL"); // Heroku で定義されています。
        DataSourceBuilder factory;
        if (url != null) {
System.out.println("############################");
System.out.println("1:" + System.getenv("DATABASE_URL"));
System.out.println("2:" + System.getenv("JDBC_DATABASE_URL"));
System.out.println("############################");
            // JDBC_DATABASE_URL の設定を利用します。
            factory = DataSourceBuilder
                    .create(this.properties.getClassLoader())
                    .url("jdbc:" + url);
        } else {
            // プロパティファイルを利用します。
            factory = DataSourceBuilder
                    .create(this.properties.getClassLoader())
                    .url(this.properties.getUrl())
                    .username(this.properties.getUsername())
                    .password(this.properties.getPassword());
        }
        return factory.build();
//
//
//        return DataSourceBuilder.create().build();
    }

}
