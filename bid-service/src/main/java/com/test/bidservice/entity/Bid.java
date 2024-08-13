package com.test.bidservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "Bid")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidId")
    private Long bidId;

    @Column(name = "AuctionUUID")
    private String auctionUUID;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "BidAmount")
    private BigDecimal bidAmount;

    @Column(name = "BidTime")
    private LocalDateTime bidTime;

}
