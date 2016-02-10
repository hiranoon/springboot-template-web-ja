package hoge.fuga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoge.fuga.domain.Player;

/**
 * {@link Player} の Repository クラス.
 * @author hirano
 */
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    /**
     * 背番号が使われている件数を取得します.
     * @param squadNumber 背番号
     * @return 背番号が使われている件数
     */
    public int countBySquadNumber(Integer squadNumber);
}
