package hoge.fuga.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hoge.fuga.entity.Player;
import hoge.fuga.entity.User;
import hoge.fuga.repository.UserRepository;

/**
 * {@link UserDetailsService} の実装クラス.
 * @author hirano
 */
@Service
public class UserDetailsServiceImple implements UserDetailsService {

    /** {@link Player} の Repository クラス */
    @Autowired
    UserRepository userRepository;

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ユーザーを検索します.
        User user = userRepository.findOne(username);
        // 存在しないユーザーの場合はエラーとします.
        if (user == null) {
            throw new UsernameNotFoundException("user not found.");
        }
        // ログインユーザーに詰め替えます.
        return new UserDetails(user);
    }
}
