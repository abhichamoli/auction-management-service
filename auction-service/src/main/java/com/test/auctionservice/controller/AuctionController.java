package com.test.auctionservice.controller;

import com.test.auctionservice.common.AuctionServiceHelper;
import com.test.auctionservice.dto.BaseResponse;
import com.test.auctionservice.dto.BidRequest;
import com.test.auctionservice.dto.ProductAuctionResponse;
import com.test.auctionservice.dto.ProductAuctionsReponse;
import com.test.auctionservice.exception.AuctionServiceException;
import com.test.auctionservice.service.AuctionService;
import com.test.auctionservice.common.Constants;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    @PostMapping("/productUUID/{productUUID}/slots/{slotId}")
    @Operation(summary = "Map product with given UUID for a particular Auction Slot")
    public ResponseEntity<BaseResponse> addProductInAuctionSlot(@PathVariable String productUUID, @PathVariable Long slotId) throws AuctionServiceException {
        auctionService.mapProductToAuctionSlot(productUUID, slotId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse(HttpStatus.CREATED.value(), String.format(Constants.SUCCESS_MSG_PRODUCT_MAPPED_FOR_A_AUCTION_SLOT, productUUID, slotId)));
    }

    @GetMapping
    @Operation(summary = "Get all available Product Auctions")
    public ResponseEntity<BaseResponse> getAllProductAuctions() {
        List<ProductAuctionResponse> productAuctionResponseList = auctionService.findAllAuctions();
        ProductAuctionsReponse productAuctionsReponse = new ProductAuctionsReponse(productAuctionResponseList);
        AuctionServiceHelper.addSuccessMessageAndResponseCode(productAuctionsReponse, Constants.SUCCESS_MSG_PRODDUCT_AUCTION_LIST_FETCHED_SUCCESFULLY);
        return ResponseEntity.ok().body(productAuctionsReponse);
    }

    @GetMapping("/{auctionId}")
    @Hidden
    public ResponseEntity<BaseResponse> getProductAuction(@PathVariable Long auctionId) throws AuctionServiceException {
        ProductAuctionResponse productAuctionResponse = auctionService.findProductAuction(auctionId);
        AuctionServiceHelper.addSuccessMessageAndResponseCode(productAuctionResponse, Constants.SUCCESS_MSG_PRODDUCT_AUCTION_FETCHED_SUCCESFULLY);
        return ResponseEntity.ok().body(productAuctionResponse);
    }

    @DeleteMapping("/{auctionId}")
    @Hidden
    public ResponseEntity<BaseResponse> removeProductFromAuctionSlot(@PathVariable Long auctionId) throws AuctionServiceException {
        auctionService.deleteProductFromAuctionSlot(auctionId);
        return ResponseEntity.ok().body(new BaseResponse(HttpStatus.OK.value(), String.format(Constants.SUCCESS_MSG_PRODUCT_DELETED_SUCCESSFULLY_FROM_AUCTION_SLOT, auctionId)));
    }

    @PostMapping("/auctionUUID/{auctionUUID}/bid")
    @Operation(summary = "Place bid for a particular Auction UUID")
    public ResponseEntity<BaseResponse> acceptBet(@PathVariable String auctionUUID, @RequestBody BidRequest bidRequest) throws AuctionServiceException {
        BaseResponse baseResponse = auctionService.acceptBid(auctionUUID, bidRequest);
        return ResponseEntity.ok().body(baseResponse);
    }

    @GetMapping("/slotId/{slotId}")
    @Operation(summary = "Get all the auction for a given Slot Id")
    public ResponseEntity<BaseResponse> getProductAuctionsForSlotId(@PathVariable Long slotId){
        List<ProductAuctionResponse> productAuctionResponseList = auctionService.getProuductAuctionsForSlotId(slotId);
        ProductAuctionsReponse productAuctionsReponse = new ProductAuctionsReponse(productAuctionResponseList);
        AuctionServiceHelper.addSuccessMessageAndResponseCode(productAuctionsReponse, Constants.SUCCESS_MSG_PRODDUCT_AUCTION_LIST_FETCHED_SUCCESFULLY);
        return ResponseEntity.ok().body(productAuctionsReponse);
    }

}
