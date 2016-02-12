package hoge.fuga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security に関する設定クラス.
 * @author hirano
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
     */
    // セキュリティ設定を無視する等の全体設定をします.
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静的リソースに対してはセキュリティ設定を無視します.
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/js/**");
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    // 認可の設定、ログイン・ログアウトの設定をします.
    protected void configure(HttpSecurity http) throws Exception {
        // 認可の設定をします.
        http.authorizeRequests()
            .antMatchers("/users/login").permitAll() // ログイン画面はアクセス可能にします.
            .anyRequest().authenticated();     // 他は認可が必要にします.
        // ログインフォームに関する設定をします.
        http.formLogin()
            .loginProcessingUrl("/authenticate")    // ログイン処理のパス
            .loginPage("/users/login")              // ログインフォームのパス
            .failureUrl("/users/login?error")       // 認証失敗時の遷移先
            .defaultSuccessUrl("/players", true)    // 認証成功時の遷移先
            .usernameParameter("username")          // パラメータ名(username)
            .passwordParameter("password");         // パラメータ名(password)
        // ログアウトに関する設定をします.
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))   // ログアウト処理のパス
            .logoutSuccessUrl("/login/");                                   // ログアウト後の遷移先
    }

    /**
     * 認証に関する設定をするクラス.
     * @author hirano
     */
    @Configuration
    static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

        /** Core interface which loads user-specific data. */
        @Autowired
        UserDetailsService userDetailsService;

        /**
         * パスワードハッシュ化アルゴリズムを設定します.
         * @return パスワードハッシュ化アルゴリズム
         */
        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter#init(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
         */
        // 認証に関する設定をします.
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService) // 認証ユーザーの取得方法
                .passwordEncoder(passwordEncoder());    // パスワード照合時のアルゴリズム
        }
    }
}
