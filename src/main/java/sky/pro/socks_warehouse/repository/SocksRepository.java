package sky.pro.socks_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sky.pro.socks_warehouse.model.Socks;

/**
 * Репозиторий для манипулирования сущностями
 */
@Repository
public interface SocksRepository extends JpaRepository<Socks, Socks.SocksId>{

    /**
     * Определение количество строк в таблице
     * @return - количество строк в таблице
     */
    @Query(value = "select count (*) from socks", nativeQuery = true)
    long count();
}
