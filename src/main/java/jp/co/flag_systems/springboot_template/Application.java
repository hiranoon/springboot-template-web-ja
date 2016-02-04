package jp.co.flag_systems.springboot_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Bootアプリケーションのエンドポイントとなるクラス.
 * @author hirano
 */
@SpringBootApplication
public class Application {

    /**
     * エンドポイント.
     * @param args 起動引数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
