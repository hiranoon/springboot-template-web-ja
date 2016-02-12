package hoge.fuga.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import hoge.fuga.service.UserService;

/**
 * ログイン失敗時の Listener クラス.
 * @author hirano
 */
@Component
public class AuthenticationFailureBadCredentialsEventListener
        implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    /** ユーザーの Service クラス. */
    @Autowired
    private UserService userService;

    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        // ユーザー名を取得します.
        String username = event.getAuthentication().getName();
        // ユーザー情報を更新します.
        userService.updateForAuthenticationFailureBadCredentials(username);
    }
}
