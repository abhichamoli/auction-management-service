package com.test.auctionservice.entity;

import com.test.auctionservice.common.ProductAuctionStatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "auctionSlot")
@EqualsAndHashCode(exclude = "auctionSlot")
public class ProductAuction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuctionId")
    private Long auctionId;

    @Column(name = "ProductUUID")
    private String productUUID;

    @Column(name = "AuctionUUID")
    private String auctionUUID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SlotId")
    private AuctionSlot auctionSlot;

    @Column(name = "BasePrice")
    private BigDecimal basePrice;

    @Column(name = "HighestBid")
    private BigDecimal highestbid;

    @Column(name = "HighestBidderId")
    private String highestBidderId;

    @Enumerated(EnumType.STRING)
    private ProductAuctionStatusEnum status;

    @Column(name = "ProductName")
    private String productName;
}
