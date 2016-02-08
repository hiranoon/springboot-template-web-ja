package hoge.fuga.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hoge.fuga.domain.Hoge;
import hoge.fuga.domain.Nationality;
import hoge.fuga.repository.NationalityRepository;
import hoge.fuga.repository.HogeRepository;

/**
 * Hoge の Service クラス.
 * @author hirano
 */
@Service
@Transactional
public class HogeService {
    /** Hoge の Repository クラス */
    @Autowired
    HogeRepository hogeRepository;

    /** {@link Nationality} の Repository クラス */
    @Autowired
    NationalityRepository nationarityRepository;

    /**
     * Hoge をページング指定条件にしたがって検索します.
     * @param pageable ページング指定条件
     * @return ページ指定された Hoge の Domain クラスのリスト
     */
    public Page<Hoge> findAll(Pageable pageable) {
        return hogeRepository.findAll(pageable);
    }

    /**
     * Hoge を id で1件検索します.
     * @param id ID
     * @return Hoge の Domain クラス
     */
    public Hoge findOne(Integer id) {
        return hogeRepository.findOne(id);
    }

    /**
     * Hoge を作成します.
     * @param hoge Hoge の Domain クラス
     * @param nationarityId 国籍ID
     * @return 作成した Hoge の Domain クラス
     */
    public Hoge create(Hoge hoge, Integer nationarityId) {
        hoge.setNationality(nationarityRepository.findOne(nationarityId));
        return hogeRepository.save(hoge);
    }

    /**
     * Hoge を更新します.
     * @param hoge Hoge の Domain クラス
     * @param nationarityId 国籍ID
     * @return 更新した Hoge の Domain クラス
     */
    public Hoge update(Hoge hoge, Integer nationarityId) {
        hoge.setNationality(nationarityRepository.findOne(nationarityId));
        return hogeRepository.save(hoge);
    }

    /**
     * Hoge を id で1件削除します.
     * @param id ID
     */
    public void delete(Integer id) {
        hogeRepository.delete(id);
    }
}
