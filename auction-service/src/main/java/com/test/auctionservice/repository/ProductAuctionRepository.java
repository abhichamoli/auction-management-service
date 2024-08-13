package com.test.auctionservice.repository;

import com.test.auctionservice.common.ProductAuctionStatusEnum;
import com.test.auctionservice.entity.ProductAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductAuctionRepository extends JpaRepository<ProductAuction, Long> {

    Optional<ProductAuction> findByAuctionUUID(String auctionUUID);

    List<ProductAuction> findByStatus(ProductAuctionStatusEnum status);

    @Query("SELECT pa FROM ProductAuction pa JOIN pa.auctionSlot as WHERE as.startTime >= :now AND pa.status = :status")
    List<ProductAuction> findStartedAuctions(LocalDateTime now, ProductAuctionStatusEnum status);

    @Query(value = "SELECT pa FROM ProductAuction pa JOIN pa.auctionSlot as WHERE as.slotId = :slotId")
    List<ProductAuction> findByAuctionSlot(Long slotId);

}
