package sky.pro.socks_warehouse.mappers;

import org.mapstruct.Mapper;
import sky.pro.socks_warehouse.dto.SocksCreate;
import sky.pro.socks_warehouse.model.Socks;

/**
 * Интерфейс для преобразований входных данных
 */
@Mapper(componentModel = "spring")
public interface SocksMapper {

    /**
     * Преобраование {@code SocksCreate} в {@code SocksId}
     * @param socksCreate - сущность поступаемая с фронта
     * @return - индентификатор для носков в базе данных
     */
    Socks.SocksId toSocksId(SocksCreate socksCreate);

}
