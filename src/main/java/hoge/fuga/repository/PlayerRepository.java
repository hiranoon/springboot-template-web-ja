package hoge.fuga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoge.fuga.entity.Player;

/**
 * {@link Player} の Repository クラス.
 * @author hirano
 */
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    /**
     * 指定した背番号の件数を取得します.
     * @param squadNumber 背番号
     * @return 指定した背番号の件数
     */
    public int countBySquadNumber(Integer squadNumber);
}
