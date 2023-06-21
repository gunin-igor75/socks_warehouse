package sky.pro.socks_warehouse.controller;

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
public class SocksController {

    private final SocksService socksService;

    @PostMapping("/income")
    public void income(@Valid @RequestBody  SocksCreate socksCreate) {
        socksService.createSocks(socksCreate);
    }

    @PostMapping("/outcome")
    public void outcome(@Valid @RequestBody SocksCreate socksCreate) {
        socksService.deleteSocks(socksCreate);
    }

    @GetMapping
    public ResponseEntity<Long> getSocks(@RequestParam(value = "color")  String color,
                                            @RequestParam(value = "operation") String operation,
                                            @RequestParam(value = "cottonPart")int cottonPart) {

        SocksCount socksCount = new SocksCount(color, operation, cottonPart);
        Long partSocks = socksService.getPartSocks(socksCount);
        return ResponseEntity.ok().body(partSocks);
    }
}
