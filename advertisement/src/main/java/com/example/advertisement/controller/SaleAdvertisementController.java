package com.example.advertisement.controller;
import com.example.advertisement.dto.CreateSaleAdvertisementRequest;
import com.example.advertisement.dto.SaleAdvertisementDto;
import com.example.advertisement.service.SaleAdvertisementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/getByCreatedAtDESC")
    public ResponseEntity<List<SaleAdvertisementDto>> findSaleAdvertisementsCreatedAtDESC(){
        return ResponseEntity.ok(saleAdvertisementService.findSaleAdvertisementsCreatedAtDESC());
    }

    @GetMapping("/getByCreatedAtASC")
    public ResponseEntity<List<SaleAdvertisementDto>> findSaleAdvertisementsCreatedAtASC(){
        return ResponseEntity.ok(saleAdvertisementService.findSaleAdvertisementsCreatedAtASC());
    }

    // ../v1/sale-advertisement
    // ../v1/sale-advertisement?title={title}
    // request param parse optional value
    @GetMapping("/getByTitleContaining")
    public ResponseEntity<List<SaleAdvertisementDto>> findSaleAdvertisementsByTitleContainingIgnoreCase(@RequestParam String title){
        return ResponseEntity.ok(saleAdvertisementService.findSaleAdvertisementsByTitleContainingIgnoreCase(title));
    }

    // ../v1/sale-advertisement
    // ../v1/sale-advertisement?detailMessage={detailMessage}
    // request param parse optional value
    @GetMapping("/getByTitleDetailMessageContaining")
    public ResponseEntity<List<SaleAdvertisementDto>> findSaleAdvertisementsByDetailMessageContainingIgnoreCase(@RequestParam String detailMessage){
        return ResponseEntity.ok(saleAdvertisementService.findSaleAdvertisementsByDetailMessageContainingIgnoreCase(detailMessage));
    }

    @GetMapping("/getPriceBetween")
    public ResponseEntity<List<SaleAdvertisementDto>> findSaleAdvertisementsByPriceBetween(@RequestParam(name = "startPrice") int startPrice, @RequestParam("endPrice") int endPrice){
        return ResponseEntity.ok(saleAdvertisementService.findSaleAdvertisementsByPriceBetween(startPrice,endPrice));
    }

    @GetMapping("/findLastSaleAdvertisementsOfEachUser")
    public ResponseEntity<List<SaleAdvertisementDto>> findLastSaleAdvertisementsOfEachUser(){
        return ResponseEntity.ok(saleAdvertisementService.findLastSaleAdvertisementsOfEachUser());
    }
}
