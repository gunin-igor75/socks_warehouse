package sky.pro.socks_warehouse.exception_nandler;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Обертка для ошибок")
public class ErrorResponse {

    @Schema(description = "сообщение")
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
