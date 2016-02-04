package jp.co.flag_systems.springboot_template;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * データソースに関する設定クラス.
 * @author hirano
 */
@Configuration
public class DataSourceConfig {

    /**
     * DataSource 設定.
     * @return {@link DataSource} オブジェクト
     */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}
