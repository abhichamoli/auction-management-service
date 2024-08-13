package com.test.auctionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class AddSlotRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
