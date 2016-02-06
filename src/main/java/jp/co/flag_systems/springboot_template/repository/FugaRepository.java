package jp.co.flag_systems.springboot_template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.flag_systems.springboot_template.domain.Fuga;

/**
 * Fuga の Repository クラス.
 * @author hirano
 */
public interface FugaRepository extends JpaRepository<Fuga, Integer> {
}
