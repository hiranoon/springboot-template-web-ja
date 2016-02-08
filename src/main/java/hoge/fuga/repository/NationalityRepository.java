package hoge.fuga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoge.fuga.domain.Nationality;

/**
 * {@link Nationality} の Repository クラス.
 * @author hirano
 */
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
}
