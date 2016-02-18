package hoge.fuga.common.security;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Spring Security に関連するユーティリティクラスです.
 * @author hirano
 */
public class SecurityUtils {

    /**
     * 隠蔽されたコンストラクタ.
     */
    private SecurityUtils() {
    }

    /**
     * 認証済みの {@link UserDetails} を取得します.
     * <p>
     * {@link UserDetails} は Controller クラスのメソッド引数に、下記のように定義して取得できます.
     * <pre>
     * String method(@AuthenticationPrincipal UserDetails userDetails ...) { ... }
     * </pre>
     * ただし、Service クラスなどでは、 Controller から順次引数で渡していく必要があります.
     * これを避けるために当該メソッドを用意しています.
     * </p>
     * @return 認証済みの {@link UserDetails}. 認証されていない場合は empty() を返却します.
     */
    public static Optional<UserDetails> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 認証されていない場合は empty() を返却します.
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            return Optional.empty();
        }
        // UserDetails を返却します.
        return Optional.of((UserDetails)authentication.getPrincipal());
    }
}
