package sky.pro.socks_warehouse.service;

import sky.pro.socks_warehouse.dto.SocksCount;
import sky.pro.socks_warehouse.dto.SocksCreate;

/**
 * Интерфейс, определяющий методы бизнес-логики приложения
 */
public interface SocksService {

    /**
     * Создание и изменение партии носков в базе данных
     * @param socksCreate - сущность получаямая с фронта
     */
    void createSocks(SocksCreate socksCreate);

    /**
     * Уменьшение партии носков
     * @param socksCreate - сущность получаямая с фронта
     */
    void reduceQuantitySocks(SocksCreate socksCreate);

    /**
     * Получение количества носков
     * @param socksCount - сущность получаямая с фронта
     * @return - количество носков
     */
    Long getQuantitySocks(SocksCount socksCount);
}
