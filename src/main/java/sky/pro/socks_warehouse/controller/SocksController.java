package sky.pro.socks_warehouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.socks_warehouse.dto.SocksCount;
import sky.pro.socks_warehouse.dto.SocksCreate;
import sky.pro.socks_warehouse.service.SocksService;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
@Tag(name = "SocksController", description = "Приход и расход носков на складе")
public class SocksController {

    private final SocksService socksService;

    @Operation(
            summary = "Сохранение партии носков",
            description = "Сохраняет партию носков базу данных"
    )
    @PostMapping("/income")
    public void income(@Valid @RequestBody SocksCreate socksCreate) {
        socksService.createSocks(socksCreate);
    }

    @Operation(
            summary = "Отпуск партии носков",
            description = "Уменьшает количество носков в базе данных"
    )
    @PostMapping("/outcome")
    public void outcome(@Valid @RequestBody SocksCreate socksCreate) {
        socksService.reduceQuantitySocks(socksCreate);
    }

    @Operation(
            summary = "Получение количества носков",
            description = "Выводит количество носков в зависимости от запроса"
    )
    @GetMapping
    public ResponseEntity<Long> getSocks(@RequestParam(value = "color") @Parameter(description =
            "цвет носков") String color,
                                         @RequestParam(value = "operation") @Parameter(description =
                                                 "оперции по поучению коичества носков") String operation,
                                         @RequestParam(value = "cottonPart") @Parameter(description =
                                                 "содержание хлопка") int cottonPart) {

        SocksCount socksCount = new SocksCount(color, operation, cottonPart);
        Long partSocks = socksService.getQuantitySocks(socksCount);
        return ResponseEntity.ok().body(partSocks);
    }
}
