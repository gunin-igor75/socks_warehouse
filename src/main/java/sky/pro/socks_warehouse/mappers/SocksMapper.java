package sky.pro.socks_warehouse.mappers;

import org.mapstruct.Mapper;
import sky.pro.socks_warehouse.dto.SocksCreate;
import sky.pro.socks_warehouse.model.SocksId;

@Mapper(componentModel = "spring")
public interface SocksMapper {
    SocksId toSocksId(SocksCreate socksCreate);

}
