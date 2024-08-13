package com.test.auctionservice.controller;

import com.test.auctionservice.common.AuctionServiceHelper;
import com.test.auctionservice.common.AuctionServiceValidationUtil;
import com.test.auctionservice.dto.AddSlotRequest;
import com.test.auctionservice.dto.AuctionSlotResponse;
import com.test.auctionservice.dto.AuctionSlotsResponse;
import com.test.auctionservice.dto.BaseResponse;
import com.test.auctionservice.exception.AuctionServiceException;
import com.test.auctionservice.service.AuctionSlotService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class AuctionSlotController {

    @Autowired
    AuctionSlotService auctionSlotService;

    @PostMapping
    @Operation(summary = "Add a Auction Slot for bidding")
    public ResponseEntity<BaseResponse> addAuctionSlot(@RequestBody AddSlotRequest addSlotRequest) throws AuctionServiceException {
        AuctionServiceValidationUtil.validateAddSlotRequest(addSlotRequest);
        auctionSlotService.addAuctionSlot(addSlotRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse(HttpStatus.CREATED.value(), Constants.SUCCESS_MSG_AUCTION_SLOT_ADDED));
    }

    @GetMapping
    @Operation(summary = "Get all available Auction Slots")
    public ResponseEntity<BaseResponse> getAllAuctionSlots(){
        List<AuctionSlotResponse> auctionSlotResponseList = auctionSlotService.getAllAuctionSlots();
        AuctionSlotsResponse auctionSlotsResponse = new AuctionSlotsResponse(auctionSlotResponseList);
        AuctionServiceHelper.addSuccessMessageAndResponseCode(auctionSlotsResponse, Constants.AUCTION_SLOTS_FETCHETED_SUCCESSFULLY);
        return ResponseEntity.ok().body(auctionSlotsResponse);
    }

    @GetMapping("/{slotId}")
    @Operation(summary = "Get Slot details for a particular SlotId")
    public ResponseEntity<BaseResponse> getAuctionSlot(@PathVariable Long slotId) throws AuctionServiceException {
        AuctionSlotResponse auctionSlotResponse = auctionSlotService.getAuctionSlot(slotId);
        AuctionServiceHelper.addSuccessMessageAndResponseCode(auctionSlotResponse, Constants.AUCTION_SLOT_FETCHETED_SUCCESSFULLY);
        return ResponseEntity.ok().body(auctionSlotResponse);
    }

    @Hidden
    @DeleteMapping("/{slotId}")
    public ResponseEntity<BaseResponse> deleteAuctionSlot(@PathVariable Long slotId) throws AuctionServiceException{
        auctionSlotService.deleteAuctionSlot(slotId);
        return ResponseEntity.ok().body(new BaseResponse(HttpStatus.OK.value(), String.format(Constants.SUCCESS_MSG_AUCTION_SLOT_SUCCESSFULLY_DELETED, slotId)));
    }

    @PutMapping("/{slotId}")
    @Operation(summary = "Edit Slot details for a particular SlotId")
    public ResponseEntity<BaseResponse> editAuctionSlot(@PathVariable Long slotId, @RequestBody AddSlotRequest addSlotRequest) throws AuctionServiceException{
        auctionSlotService.editAuctionSlot(slotId, addSlotRequest);
        return ResponseEntity.ok().body(new BaseResponse(HttpStatus.OK.value(), String.format(Constants.SUCCESS_MSG_AUCTION_SLOT_EDIT_SUCCESSFULLY_FOR_SLOT_ID, slotId)));
    }

}
