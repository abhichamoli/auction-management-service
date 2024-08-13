package com.test.auctionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AuctionSlotsResponse extends BaseResponse{
    List<AuctionSlotResponse> auctionSlots;
}
