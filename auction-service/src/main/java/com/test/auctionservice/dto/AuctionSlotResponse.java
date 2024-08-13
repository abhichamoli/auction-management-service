package com.test.auctionservice.dto;

import com.test.auctionservice.common.SlotStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AuctionSlotResponse extends BaseResponse{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SlotStatusEnum status;
    private Long slotId;
}
