package hoge.fuga.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hoge.fuga.domain.Fuga;
import hoge.fuga.repository.FugaRepository;

/**
 * Fuga の Service クラス.
 * @author hirano
 */
@Service
@Transactional
public class FugaService {

    /** Fuga の Repository クラス */
    @Autowired
    FugaRepository fugaRepository;

    /**
     * Fuga を全件検索します.
     * @return Fuga の Domain クラスのリスト
     */
    public List<Fuga> findAll() {
        return fugaRepository.findAll();
    }
}
