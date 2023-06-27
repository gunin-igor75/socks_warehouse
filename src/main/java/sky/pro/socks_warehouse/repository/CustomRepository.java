package sky.pro.socks_warehouse.repository;

/**
 * Кастомный репозиторий для динамического запроса
 */
public interface CustomRepository {

    /**
     * Получение количество носков
     * @param color - цвет носков
     * @param operation - операция выбора носков
     * @param cottonPart - процент содержания хлопка
     * @return - количество носков
     */
    Long getCountPartSocks(String color, String operation, Integer cottonPart);

}
