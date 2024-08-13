package com.test.auctionservice.service;

import com.test.auctionservice.dto.AddSlotRequest;
import com.test.auctionservice.dto.AuctionSlotResponse;
import com.test.auctionservice.entity.AuctionSlot;
import com.test.auctionservice.exception.AuctionServiceException;

import java.util.List;

public interface AuctionSlotService {
    void addAuctionSlot(AddSlotRequest addSlotRequest);
    void editAuctionSlot(Long slotId, AddSlotRequest addSlotRequest) throws AuctionServiceException;
    AuctionSlotResponse getAuctionSlot(Long slotId) throws AuctionServiceException;
    List<AuctionSlotResponse> getAllAuctionSlots();
    void deleteAuctionSlot(Long slotId) throws AuctionServiceException;
    List<AuctionSlot> getOpenAuctionSlots();
    List<AuctionSlot> getEndedAuctionSlots();

}
