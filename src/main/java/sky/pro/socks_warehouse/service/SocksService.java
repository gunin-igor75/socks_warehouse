package sky.pro.socks_warehouse.service;

import org.springframework.transaction.annotation.Transactional;
import sky.pro.socks_warehouse.dto.SocksCount;
import sky.pro.socks_warehouse.dto.SocksCreate;

public interface SocksService {


    @Transactional
    void createSocks(SocksCreate socksCreate);

    @Transactional
    void deleteSocks(SocksCreate socksCreate);

    @Transactional(readOnly = true)
    Long getPartSocks(SocksCount socksCount);
}
