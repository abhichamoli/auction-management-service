package com.test.auctionservice.entity;

import com.test.auctionservice.common.SlotStatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "auctionSlot")
@EqualsAndHashCode(exclude = "auctionSlot")
public class AuctionSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SlotId")
    private Long slotId;

    @Column(name = "StartTime")
    private LocalDateTime startTime;

    @Column(name = "EndTime")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private SlotStatusEnum status;

    @OneToMany(mappedBy = "auctionSlot", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<ProductAuction> productAuctions;

}
