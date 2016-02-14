package hoge.fuga.common.security;

import org.springframework.security.core.authority.AuthorityUtils;

import hoge.fuga.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザー情報を保持する Model クラス.
 * <p>
 * Spring Security の {@link org.springframework.security.core.userdetails.User} の拡張クラスです.
 * </p>
 * @author hirano
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDetails extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    /** ユーザー(users)テーブルの Entity クラス. */
    private final User user;

    /**
     * コンストラクタ.
     * @param user {@link User} オブジェクト.
     */
    public UserDetails(User user) {
        super(user.getUsername(),
              user.getEncryptedPassword(),
              AuthorityUtils.createAuthorityList("ROLE_USER")); // ロールは固定とします.
        this.user = user;
    }
}
