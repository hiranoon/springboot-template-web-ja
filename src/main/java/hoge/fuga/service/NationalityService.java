package hoge.fuga.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoge.fuga.domain.Nationality;
import hoge.fuga.repository.NationalityRepository;

/**
 * 国籍の Service クラス.
 * @author hirano
 */
@Service
@Transactional
public class NationalityService {

    /** {@link Nationality} の Repository クラス */
    @Autowired
    NationalityRepository nationalityRepository;

    /**
     * {@link Nationality} を全件検索します.
     * @return {@link Nationality} の Domain クラスのリスト
     */
    public List<Nationality> findAll() {
        return nationalityRepository.findAll();
    }
}
