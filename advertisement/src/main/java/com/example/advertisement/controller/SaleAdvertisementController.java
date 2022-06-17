package com.example.advertisement.controller;

import com.example.advertisement.dto.CreateSaleAdvertisementRequest;
import com.example.advertisement.dto.CreateUserRequest;
import com.example.advertisement.dto.SaleAdvertisementDto;
import com.example.advertisement.dto.UserDto;
import com.example.advertisement.service.SaleAdvertisementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sale-advertisement")
public class SaleAdvertisementController {
    private final SaleAdvertisementService saleAdvertisementService;

    public SaleAdvertisementController(SaleAdvertisementService saleAdvertisementService) {
        this.saleAdvertisementService = saleAdvertisementService;
    }

    @PostMapping
    public ResponseEntity<SaleAdvertisementDto> createSaleAdvertisement(@RequestBody CreateSaleAdvertisementRequest request){
        return ResponseEntity.ok(saleAdvertisementService.createSaleAdvertisement(request));
    }

    @PostMapping("/random-advertisement")
    public ResponseEntity<String> createRandomAdvertisements(){
        return ResponseEntity.ok(saleAdvertisementService.createRandomSaleAdvertisement());
    }
}
