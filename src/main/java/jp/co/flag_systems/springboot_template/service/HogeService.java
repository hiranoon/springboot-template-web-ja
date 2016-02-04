package jp.co.flag_systems.springboot_template.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.flag_systems.springboot_template.domain.Hoge;
import jp.co.flag_systems.springboot_template.repository.HogeRepository;

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

    /**
     * Hoge を全件検索します.
     * @return すべての Hoge の Domain クラスのリスト
     */
    public List<Hoge> findAll() {
        return hogeRepository.findAll();
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
     * @return 作成した Hoge の Domain クラス
     */
    public Hoge create(Hoge hoge) {
        return hogeRepository.save(hoge);
    }

    /**
     * Hoge を更新します.
     * @param hoge Hoge の Domain クラス
     * @return 更新した Hoge の Domain クラス
     */
    public Hoge update(Hoge hoge) {
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
