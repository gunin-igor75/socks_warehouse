package sky.pro.socks_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sky.pro.socks_warehouse.model.Socks;
import sky.pro.socks_warehouse.model.SocksId;

@Repository
public interface SocksRepository extends JpaRepository<Socks, SocksId>{

    @Query(value = "select count (*) from socks", nativeQuery = true)
    long count();
}
