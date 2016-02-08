package hoge.fuga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoge.fuga.domain.Player;

/**
 * {@link Player} の Repository クラス.
 * @author hirano
 */
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
