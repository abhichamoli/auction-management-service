package com.test.bidservice.service;

import com.test.bidservice.client.AuctionServiceClient;
import com.test.bidservice.dto.BaseResponse;
import com.test.bidservice.dto.BidRequest;
import com.test.bidservice.dto.BidResponse;
import com.test.bidservice.entity.Bid;
import com.test.bidservice.exception.BidServiceException;
import com.test.bidservice.repository.BidRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BidServiceImpl implements BidService {

    @Autowired
    AuctionServiceClient auctionServiceClient;

    @Autowired
    BidRepository bidRepository;

    @Override
    public void placeBid(String auctionUUID, BidRequest bidRequest) throws BidServiceException {
        BaseResponse baseResponse = auctionServiceClient.placeBid(auctionUUID, bidRequest);
        Bid bid = createBid(auctionUUID, bidRequest);
        bidRepository.save(bid);
    }

    @Override
    public List<BidResponse> getBids() {
        List<Bid> bids = bidRepository.findAll();
        return bids.stream().map(this::createBidResponse).toList();
    }

    private Bid createBid(String auctionUUID, BidRequest bidRequest) {
        return Bid.builder()
                .userId(bidRequest.getUserId())
                .bidAmount(bidRequest.getBidAmount())
                .auctionUUID(auctionUUID)
                .bidTime(bidRequest.getBidTime())
                .build();
    }

    private BidResponse createBidResponse(Bid bid){
        return BidResponse.builder()
                .userId(bid.getUserId())
                .bidAmount(bid.getBidAmount())
                .auctionUUID(bid.getAuctionUUID())
                .bidTime(bid.getBidTime())
                .build();
    }


}
