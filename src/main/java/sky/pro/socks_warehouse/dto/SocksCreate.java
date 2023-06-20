package sky.pro.socks_warehouse.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import sky.pro.socks_warehouse.validation.ValidationSocksColors;

public class SocksCreate {

    @ValidationSocksColors(valueColors = {"black", "red", "yellow"})
    private String color;

    @NotNull
    @Min(value = 0, message = "Минимальное целое число должно быть не меньше 0")
    @Max(value = 100, message = "Максимальное число должно быть не больше 100")
    private Integer cottonPart;

    @NotNull
    @Min(value = 0, message = "Минимальное целое число должно быть не меньше 0")
    @Max(value = Integer.MAX_VALUE, message = "Максимальное число должно быть не больше Integer.MAX_VALUE")
    private Integer quantity;
}
