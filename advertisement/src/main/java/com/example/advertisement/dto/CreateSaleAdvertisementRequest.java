package com.example.advertisement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSaleAdvertisementRequest {
    private String title;
    private int price;
    private String detailMessage;
    private Long userId;
}
