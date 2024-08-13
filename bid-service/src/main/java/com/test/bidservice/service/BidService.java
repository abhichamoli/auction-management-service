package com.test.bidservice.service;

import com.test.bidservice.dto.BidRequest;
import com.test.bidservice.dto.BidResponse;
import com.test.bidservice.exception.BidServiceException;

import java.util.List;

public interface BidService {

    void placeBid(String auctionUUID, BidRequest bidRequest) throws BidServiceException;

    List<BidResponse> getBids();

}
