package hoge.fuga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoge.fuga.domain.Hoge;

/**
 * Hoge の Repository クラス.
 * @author hirano
 */
public interface HogeRepository extends JpaRepository<Hoge, Integer> {
}
