package hoge.fuga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hoge.fuga.entity.Nationality;

/**
 * {@link Nationality} の Repository クラス.
 * @author hirano
 */
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
    /**
     * 指定した国籍IDの件数を取得します.
     * @param id 国籍ID
     * @return 指定した国籍IDの件数
     */
    public int countById(Integer id);
}
