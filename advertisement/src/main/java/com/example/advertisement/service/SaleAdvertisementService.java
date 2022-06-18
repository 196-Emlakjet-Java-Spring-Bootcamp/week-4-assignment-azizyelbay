package com.example.advertisement.service;

import com.example.advertisement.dto.CreateSaleAdvertisementRequest;
import com.example.advertisement.dto.SaleAdvertisementDto;
import com.example.advertisement.dto.converter.SaleAdvertisementDtoConverter;
import com.example.advertisement.model.SaleAdvertisement;
import com.example.advertisement.model.User;
import com.example.advertisement.repository.SaleAdvertisementRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleAdvertisementService {
    private final SaleAdvertisementRepository saleAdvertisementRepository;
    private final UserService userService;
    private final SaleAdvertisementDtoConverter converter;
    private final KafkaTemplate <String, SaleAdvertisement> kafkaSaleAdvertisementTemplate;
    private static final String[] firstTitle = {"satilik", "kiralik", "temiz", "doktordan", "ihtiyactan"};
    private static final String[] secondTitle = {"ev","araba","villa","arsa"};
    private static final String[] deatilMessages = {"Ihtiyactan satilik","Asansorlu","Kombili","fiber altyapisi var","Guven emlak","aktas emlak", "Kalite guven bizim isimiz"};

    public SaleAdvertisementService(SaleAdvertisementRepository saleAdvertisementRepository, UserService userService, SaleAdvertisementDtoConverter converter, KafkaTemplate<String, SaleAdvertisement> kafkaSaleAdvertisementTemplate) {
        this.saleAdvertisementRepository = saleAdvertisementRepository;
        this.userService = userService;
        this.converter = converter;
        this.kafkaSaleAdvertisementTemplate = kafkaSaleAdvertisementTemplate;
    }

    public SaleAdvertisementDto createSaleAdvertisement(CreateSaleAdvertisementRequest createSaleAdvertisementRequest) {
        User user = userService.getUserById(createSaleAdvertisementRequest.getUserId());

        if(user == null){
            return null;
        }

        SaleAdvertisement saleAdvertisement = new SaleAdvertisement();
        saleAdvertisement.setTitle(createSaleAdvertisementRequest.getTitle());
        saleAdvertisement.setDetailMessage(createSaleAdvertisementRequest.getDetailMessage());
        saleAdvertisement.setPrice(createSaleAdvertisementRequest.getPrice());
        saleAdvertisement.setUser(user);

        return converter.convert(saleAdvertisementRepository.save(saleAdvertisement));
    }

    public String createRandomSaleAdvertisement() {

        Random random = new Random();

        int i = 0;
        while(i<50){

            SaleAdvertisement saleAdvertisement = new SaleAdvertisement();
            saleAdvertisement.setTitle(firstTitle[random.nextInt(firstTitle.length)] + " " + secondTitle[random.nextInt(secondTitle.length)]);
            saleAdvertisement.setPrice(random.nextInt(5000000));
            saleAdvertisement.setDetailMessage(deatilMessages[random.nextInt(deatilMessages.length)]);
            saleAdvertisement.setUser(userService.getRandomUser());

            kafkaSaleAdvertisementTemplate.send("sale-topic", saleAdvertisement);

            i++;
        }

        return "Random advertisements sent";
    }

    public List<SaleAdvertisementDto> findSaleAdvertisementsCreatedAtDESC() {
        return saleAdvertisementRepository.findByCreatedAtDESC()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<SaleAdvertisementDto> findSaleAdvertisementsCreatedAtASC() {
        return saleAdvertisementRepository.findByCreatedAtASC()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<SaleAdvertisementDto> findSaleAdvertisementsByTitleContainingIgnoreCase(String title) {
        return saleAdvertisementRepository.findSaleAdvertisementsByTitleContainingIgnoreCase(title)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<SaleAdvertisementDto> findSaleAdvertisementsByDetailMessageContainingIgnoreCase(String detailMessage) {
        return saleAdvertisementRepository.findSaleAdvertisementsByDetailMessageContainingIgnoreCase(detailMessage)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<SaleAdvertisementDto> findSaleAdvertisementsByPriceBetween(int startPrice, int endPrice) {
        return saleAdvertisementRepository.findSaleAdvertisementsByPriceBetween(startPrice, endPrice)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<SaleAdvertisementDto> findLastSaleAdvertisementsOfEachUser() {
        return saleAdvertisementRepository.findLastSaleAdvertisementsOfEachUser()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
