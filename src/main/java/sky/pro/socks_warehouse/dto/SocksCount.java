package sky.pro.socks_warehouse.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import sky.pro.socks_warehouse.validation.SelfValidated;
import sky.pro.socks_warehouse.validation.ValidationSocksColors;
import sky.pro.socks_warehouse.validation.ValidationSocksOperations;

public class SocksCount extends SelfValidated {

    @ValidationSocksColors(valueColors = {"black", "red", "yellow"})
    private final String color;


    @ValidationSocksOperations(valueOperations ={"moreThan", "lessThan", "equal"})
    private final String operation;
    @NotNull
    @Min(value = 0, message = "Минимальное целое число должно быть не меньше 0")
    @Max(value = 100, message = "Максимальное число должно быть не больше 100")
    private final Integer cottonPart;


    public SocksCount(String color, String operation, Integer cottonPart) {
        this.color = color;
        this.operation = operation;
        this.cottonPart = cottonPart;
        validateSelf();
    }

    public String getColor() {
        return color;
    }

    public String getOperation() {
        return operation;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }
}
