package hoge.fuga.service;

import org.springframework.security.core.authority.AuthorityUtils;

import hoge.fuga.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ログインユーザー情報を保持する Model クラス.
 * <p>
 * Spring Security の {@link org.springframework.security.core.userdetails.User} の拡張クラスです.
 * </p>
 * @author hirano
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    /** ユーザー(users)テーブルの Domain クラス. */
    private final User user;

    /**
     * コンストラクタ.
     * @param user {@link User} オブジェクト.
     */
    public LoginUser(User user) {
        super(user.getUsername(),
              user.getEncryptedPassword(),
              AuthorityUtils.createAuthorityList("ROLE_USER")); // ロールは固定とします.
        this.user = user;
    }
}
