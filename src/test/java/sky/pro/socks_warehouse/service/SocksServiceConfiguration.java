package sky.pro.socks_warehouse.service;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import sky.pro.socks_warehouse.mappers.SocksMapper;
import sky.pro.socks_warehouse.repository.CustomRepository;
import sky.pro.socks_warehouse.repository.SocksRepository;
import sky.pro.socks_warehouse.repository.imp.CustomRepositoryImp;
import sky.pro.socks_warehouse.service.imp.SockServiceImp;

@TestConfiguration
public class SocksServiceConfiguration {

    @Bean
    public SocksService socksServiceTest(SocksRepository socksRepositoryTest, CustomRepository custom,
                                         SocksMapper socksMapperTest) {
        return new SockServiceImp(socksRepositoryTest, custom, socksMapperTest);
    }

    @Bean("custom")
    public CustomRepository customRepository() {
        return new CustomRepositoryImp();
    }

    @Bean
    public SocksMapper socksMapperTest() {
        return Mappers.getMapper(SocksMapper.class);
    }
}
