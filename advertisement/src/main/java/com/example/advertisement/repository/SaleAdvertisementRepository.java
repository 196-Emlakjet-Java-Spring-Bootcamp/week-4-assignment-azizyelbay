package com.example.advertisement.repository;

import com.example.advertisement.model.SaleAdvertisement;
import com.example.advertisement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleAdvertisementRepository extends JpaRepository<SaleAdvertisement, Long> {
    @Query(nativeQuery=true, value="SELECT * FROM sale_advertisements ORDER BY created_at DESC")
    List<SaleAdvertisement> findByCreatedAtDESC();

    @Query(nativeQuery=true, value="SELECT * FROM sale_advertisements ORDER BY created_at ASC")
    List<SaleAdvertisement> findByCreatedAtASC();

    List<SaleAdvertisement> findSaleAdvertisementsByPriceBetween(int startPrice, int endPrice);

    List<SaleAdvertisement> findSaleAdvertisementsByTitleContainingIgnoreCase(String title);

    List<SaleAdvertisement> findSaleAdvertisementsByDetailMessageContainingIgnoreCase(String description);
}
