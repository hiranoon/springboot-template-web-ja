package hoge.fuga.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hoge.fuga.domain.Player;
import hoge.fuga.domain.Nationality;
import hoge.fuga.repository.NationalityRepository;
import hoge.fuga.repository.PlayerRepository;

/**
 * 選手の Service クラス.
 * @author hirano
 */
@Service
@Transactional
public class PlayerService {

    /** {@link Player} の Repository クラス */
    @Autowired
    PlayerRepository playerRepository;

    /** {@link Nationality} の Repository クラス */
    @Autowired
    NationalityRepository nationarityRepository;

    /**
     * {@link Player} をページング指定条件にしたがって検索します.
     * @param pageable ページング指定条件
     * @return ページ指定された {@link Player} の Domain クラスのリスト
     */
    public Page<Player> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    /**
     * {@link Player} を ID で1件検索します.
     * @param id ID
     * @return {@link Player} の Domain クラス
     */
    public Player findOne(Integer id) {
        return playerRepository.findOne(id);
    }

    /**
     * {@link Player} を作成します.
     * @param player {@link Player} の Domain クラス
     * @param nationarityId 国籍ID
     * @return 作成した {@link Player} の Domain クラス
     */
    public Player create(Player player, Integer nationarityId) {
        player.setNationality(nationarityRepository.findOne(nationarityId));
        return playerRepository.save(player);
    }

    /**
     * {@link Player} を更新します.
     * @param player {@link Player} の Domain クラス
     * @param nationarityId 国籍ID
     * @return 更新した {@link Player} の Domain クラス
     */
    public Player update(Player player, Integer nationarityId) {
        player.setNationality(nationarityRepository.findOne(nationarityId));
        return playerRepository.save(player);
    }

    /**
     * {@link Player} を ID で1件削除します.
     * @param id ID
     */
    public void delete(Integer id) {
        playerRepository.delete(id);
    }
}