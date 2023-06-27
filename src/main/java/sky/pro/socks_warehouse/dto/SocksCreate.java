package sky.pro.socks_warehouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import sky.pro.socks_warehouse.validation.ValidationSocksColors;

/**
 * Сущность созданная на основе запросав прихода и расхода носков
 */

@Schema(description = "Сущность для сохранения носков на складе")
public class SocksCreate {

    /** Цвет носкоа*/
    @Schema(description = "Цвет носков")
    @ValidationSocksColors(valueColors = {"black", "red", "yellow"})
    private String color;

    /** Процент содержания хлопка в носках*/
    @Schema(description = "Содержание хлопка")
    @NotNull
    @Min(value = 0, message = "Минимальное целое число должно быть не меньше 0")
    @Max(value = 100, message = "Максимальное число должно быть не больше 100")
    private Integer cottonPart;

    /** Количество носков*/
    @Schema(description = "Количество носков")
    @NotNull
    @Min(value = 1, message = "Минимальное целое число должно быть не меньше 0")
    @Max(value = Integer.MAX_VALUE, message = "Максимальное число должно быть не больше Integer.MAX_VALUE")
    private Integer quantity;

    public SocksCreate() {
    }

    public SocksCreate(String color, Integer cottonPart, Integer quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
