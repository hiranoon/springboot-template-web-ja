package hoge.fuga.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoge.fuga.domain.User;
import hoge.fuga.repository.UserRepository;

/**
 * ユーザーの Service クラス.
 * @author hirano
 */
@Service
@Transactional
public class UserService {

    /** {@link User} の Repository クラス */
    @Autowired
    UserRepository userRepository;

    /** HTTP リクエスト. */
    @Autowired
    private HttpServletRequest request;

    /**
     * ログイン成功時に {@link User} を更新します.
     * @param user {@link User} の Domain クラス
     * @return 更新した {@link User} の Domain クラス
     */
    public User updateForAuthenticationSuccess(User user) {
        // ユーザー情報をセットします.
        user.setSignInCount(user.getSignInCount() + 1);     // ログイン回数をインクリメントします.
        user.setCurrentSignInAt(LocalDateTime.now());                       // 最新ログイン日時
        user.setCurrentSignInIp(request.getRemoteAddr());   // 最新ログインIPアドレス
        user.setFailedAttempts(0);                          // 認証失敗回数をリセットします.
        // 更新します.
        return update(user);
    }

    /**
     * ログイン失敗時に {@link User} を更新します.
     * <p>
     * 存在しないユーザー名の場合は何も行いません.
     * </p>
     * @param username ユーザー名
     * @return 更新した {@link User} の Domain クラス
     */
    public User updateForAuthenticationFailureBadCredentials(String username) {
        // ユーザーを取得します.
        User user = userRepository.findOne(username);
        // 取得できなかった場合は処理をしません.
        if (user == null) {
            return null;
        }
        // ユーザー情報をセットします.
        user.setFailedAttempts(user.getFailedAttempts() + 1);   // 認証失敗回数をインクリメントします.
        // 更新します.
        return update(user);
    }

    /**
     * {@link User} を更新します.
     * @param user {@link User} の Domain クラス
     * @return 更新した {@link User} の Domain クラス
     */
    private User update(User user) {
        // ユーザー情報をセットします.
        user.setUpdatedAt(LocalDateTime.now());
        // 更新します.
        return userRepository.save(user);
    }
}
