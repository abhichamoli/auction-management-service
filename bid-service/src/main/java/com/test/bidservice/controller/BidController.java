package com.test.bidservice.controller;

import com.test.bidservice.dto.BaseResponse;
import com.test.bidservice.dto.BidRequest;
import com.test.bidservice.dto.BidResponse;
import com.test.bidservice.dto.BidsResponse;
import com.test.bidservice.exception.BidServiceException;
import com.test.bidservice.service.BidService;
import com.test.bidservice.common.Constants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BidController {

    @Autowired
    BidService bidService;

    @PostMapping("/auctions/{auctionUUID}/bid")
    @Operation(summary = "Make bid for a particular Auction UUID")
    public ResponseEntity<BaseResponse> placeBid(@PathVariable String auctionUUID, @RequestBody BidRequest bidRequest) throws BidServiceException {
        bidService.placeBid(auctionUUID, bidRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse(HttpStatus.OK.value(), Constants.SUCCESS_MSG_PLACED_BET));
    }

    @GetMapping("/bids")
    @Operation(summary = "Get All Bids")
    public ResponseEntity<BaseResponse> getBids(){
        List<BidResponse> bidResponseList = bidService.getBids();
        BidsResponse bidsResponse = new BidsResponse(bidResponseList);
        bidsResponse.setResponseCode(HttpStatus.OK.value());
        bidsResponse.setResponseMessage(Constants.SUCCESS_MSG_BETS_FETCHED_SUCCESFULLY);
        return ResponseEntity.ok().body(bidsResponse);
    }
}
