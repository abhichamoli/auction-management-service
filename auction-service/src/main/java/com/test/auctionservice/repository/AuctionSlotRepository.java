package com.test.auctionservice.repository;

import com.test.auctionservice.common.SlotStatusEnum;
import com.test.auctionservice.entity.AuctionSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionSlotRepository extends JpaRepository<AuctionSlot, Long> {

    @Query("SELECT s FROM AuctionSlot s WHERE s.status = :status AND :now BETWEEN s.startTime AND s.endTime")
    List<AuctionSlot> findOpenSlots(LocalDateTime now, SlotStatusEnum status);

    @Query("SELECT s FROM AuctionSlot s WHERE s.status = :status AND :now > s.endTime")
    List<AuctionSlot> findEndedSlots(LocalDateTime now, SlotStatusEnum status);
}
