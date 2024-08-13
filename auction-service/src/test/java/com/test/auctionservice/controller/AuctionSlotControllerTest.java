package com.test.auctionservice.controller;

import com.test.auctionservice.common.TestHelper;
import com.test.auctionservice.dto.AddSlotRequest;
import com.test.auctionservice.dto.BaseResponse;
import com.test.auctionservice.exception.AuctionServiceException;
import com.test.auctionservice.service.AuctionSlotService;
import com.test.auctionservice.common.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static com.test.auctionservice.common.TestConstants.SLOT_ID;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuctionSlotControllerTest {

    @InjectMocks
    AuctionSlotController auctionSlotController;

    @Mock
    AuctionSlotService auctionSlotService;

    @Test
    public void testAddAuctionSlot() throws AuctionServiceException {
        AddSlotRequest addSlotRequest = TestHelper.createAddSlotRequest();
        Mockito.doNothing().when(auctionSlotService).addAuctionSlot(addSlotRequest);

        ResponseEntity<BaseResponse> responseEntity = auctionSlotController.addAuctionSlot(addSlotRequest);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assert.assertEquals(Constants.SUCCESS_MSG_AUCTION_SLOT_ADDED, Objects.requireNonNull(responseEntity.getBody()).getResponseMessage());
    }

    @Test
    public void testGetAuctionSlot() throws AuctionServiceException {
        Mockito.when(auctionSlotService.getAuctionSlot(SLOT_ID)).thenReturn(TestHelper.createAuctionSlotResponse());

        ResponseEntity<BaseResponse> responseEntity =  auctionSlotController.getAuctionSlot(SLOT_ID);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(Constants.AUCTION_SLOT_FETCHETED_SUCCESSFULLY, Objects.requireNonNull(responseEntity.getBody()).getResponseMessage());;
    }



}
