package sky.pro.socks_warehouse.repository;

public interface CustomRepository {
    Long getCountPartSocks(String color, String operation, Integer cottonPart);

}
