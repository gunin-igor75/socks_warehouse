package sky.pro.socks_warehouse.exception_nandler;

/**
 * Кастомное исключение - отсутствие ресурса в базе данных
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
