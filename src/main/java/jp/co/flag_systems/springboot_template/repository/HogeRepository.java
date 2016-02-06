package jp.co.flag_systems.springboot_template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.flag_systems.springboot_template.domain.Hoge;

/**
 * Hoge の Repository クラス.
 * @author hirano
 */
public interface HogeRepository extends JpaRepository<Hoge, Integer> {
}
