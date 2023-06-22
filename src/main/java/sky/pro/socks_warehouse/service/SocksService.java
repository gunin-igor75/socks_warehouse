package sky.pro.socks_warehouse.service;

import sky.pro.socks_warehouse.dto.SocksCount;
import sky.pro.socks_warehouse.dto.SocksCreate;

public interface SocksService {



    void createSocks(SocksCreate socksCreate);


    void reduceQuantitySocks(SocksCreate socksCreate);


    Long getQuantitySocks(SocksCount socksCount);
}
