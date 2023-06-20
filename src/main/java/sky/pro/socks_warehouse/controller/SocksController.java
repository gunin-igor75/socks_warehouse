package sky.pro.socks_warehouse.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sky.pro.socks_warehouse.dto.SocksCreate;
import sky.pro.socks_warehouse.validation.ValidationSocksColors;
import sky.pro.socks_warehouse.validation.ValidationSocksOperations;

@RestController
@RequestMapping("/api/socks")
@Validated
public class SocksController {

    @PostMapping("/income")
    public ResponseEntity<?> income(@RequestBody @Valid SocksCreate socksCreate) {
        return null;
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> outcome() {
        return null;
    }

    @GetMapping
    public ResponseEntity<Integer> getSocks(@RequestParam(value = "color") @ValidationSocksColors(valueColors =
            {"black", "red", "yellow"}, message = "") String color,
                                            @RequestParam(value = "operation") @ValidationSocksOperations(valueOperations =
                                                    {"moreThan", "lessThan", "equal"}, message = "") String operation,
                                            @RequestParam(value = "cottonPart") @Min(value = 0,
                                                    message = "Минимальное целое число должно быть не меньше 0")
                                            @Max(value = 100, message = "Максимальное число должно быть не больше 100")
                                            int cottonPart) {

        return null;
    }
}
