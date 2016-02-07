package jp.co.flag_systems.springboot_template.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.flag_systems.springboot_template.domain.Fuga;
import jp.co.flag_systems.springboot_template.repository.FugaRepository;

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
