package com.example.advertisement.dto.converter;

import com.example.advertisement.dto.SaleAdvertisementDto;
import com.example.advertisement.model.SaleAdvertisement;
import org.springframework.stereotype.Component;

@Component
public class SaleAdvertisementDtoConverter {
    public SaleAdvertisementDto convert(SaleAdvertisement from) {
        return new SaleAdvertisementDto(from.getId(),
                from.getTitle(),
                from.getPrice(),
                from.getDetailMessage(),
                from.getUser().getId()
        );
    }
}
