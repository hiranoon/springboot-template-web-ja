package hoge.fuga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoge.fuga.domain.Fuga;

/**
 * Fuga の Repository クラス.
 * @author hirano
 */
public interface FugaRepository extends JpaRepository<Fuga, Integer> {
}
