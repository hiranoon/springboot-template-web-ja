package hoge.fuga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoge.fuga.domain.Player;
import hoge.fuga.domain.User;
import hoge.fuga.repository.UserRepository;

/**
 * ユーザーの Service クラス.
 * @author hirano
 */
@Service
@Transactional
public class LoginUserService implements UserDetailsService {

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
        return new LoginUser(user);
    }

}
