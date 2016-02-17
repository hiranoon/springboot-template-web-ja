package hoge.fuga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.dialect.IDialect;

import hoge.fuga.common.token.TransactionTokenDialect;

/**
 * Thymeleaf に関する設定クラス.
 * @author hirano
 */
@Configuration
public class ThymeleafConfig {

    /** Main class for the execution of templates. */
    @Autowired
    public TemplateEngine templateEngine;

    /**
     * {@link IDialect} の実装クラスを追加します.
     * @return {@link IDialect} の実装クラスを追加した {@link TemplateEngine} オブジェクト
     */
    @Bean
    public TemplateEngine addDialect() {
        templateEngine.addDialect(new TransactionTokenDialect());   // Token に関する Dialect
        return templateEngine;
    }
}
