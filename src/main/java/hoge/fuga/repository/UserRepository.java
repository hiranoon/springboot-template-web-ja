package hoge.fuga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoge.fuga.domain.User;

/**
 * {@link User} の Repository クラス.
 * @author hirano
 */
public interface UserRepository extends JpaRepository<User, String> {
}
