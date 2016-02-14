package hoge.fuga.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import hoge.fuga.entity.User;
import hoge.fuga.service.UserService;

/**
 * ログイン成功時の Listener クラス.
 * @author hirano
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    /** ユーザーの Service クラス. */
    @Autowired
    private UserService userService;

    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    // ログイン成功時の処理です.
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        // ユーザー情報を取得します.
        User user = ((UserDetails)event.getAuthentication().getPrincipal()).getUser();
        // ユーザー情報を更新します.
        userService.updateForAuthenticationSuccess(user);
    }

}
