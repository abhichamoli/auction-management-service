package com.test.auctionservice.service;

import com.test.auctionservice.dto.BaseResponse;
import com.test.auctionservice.dto.BidRequest;
import com.test.auctionservice.dto.ProductAuctionResponse;
import com.test.auctionservice.entity.ProductAuction;
import com.test.auctionservice.exception.AuctionServiceException;

import java.util.List;

public interface AuctionService {

    void mapProductToAuctionSlot(String productUUID, Long slotId) throws AuctionServiceException;

    BaseResponse acceptBid(String auctionUUID, BidRequest bidRequest) throws AuctionServiceException;

    void deleteProductFromAuctionSlot(Long auctionId) throws AuctionServiceException;

    List<ProductAuctionResponse> findAllAuctions();

    ProductAuctionResponse findProductAuction(Long auctionId) throws AuctionServiceException;

    List<ProductAuction> findEndedAuctions();

    List<ProductAuctionResponse> getProuductAuctionsForSlotId(Long slotId);
}
