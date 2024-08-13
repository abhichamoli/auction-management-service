package com.test.auctionservice.controller;

import com.test.auctionservice.common.TestHelper;
import com.test.auctionservice.dto.BaseResponse;
import com.test.auctionservice.dto.ProductAuctionResponse;
import com.test.auctionservice.exception.AuctionServiceException;
import com.test.auctionservice.service.AuctionService;
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

import java.util.List;
import java.util.Objects;

import static com.test.auctionservice.common.TestConstants.PRODUCT_UUID;
import static com.test.auctionservice.common.TestConstants.SLOT_ID;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AuctionControllerTest {

    @InjectMocks
    private AuctionController auctionController;

    @Mock
    private AuctionService auctionService;

    @Test
    public void testAddProductInSlot() throws AuctionServiceException {
        Mockito.doNothing().when(auctionService).mapProductToAuctionSlot(PRODUCT_UUID, SLOT_ID);

        ResponseEntity<BaseResponse> responseEntity =  auctionController.addProductInAuctionSlot(PRODUCT_UUID, SLOT_ID);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(String.format(Constants.SUCCESS_MSG_PRODUCT_MAPPED_FOR_A_AUCTION_SLOT, PRODUCT_UUID, SLOT_ID), Objects.requireNonNull(responseEntity.getBody()).getResponseMessage());
    }

    @Test
    public void testGetAllProductAuctions() throws AuctionServiceException {
        List<ProductAuctionResponse> productAuctionResponseList = List.of(TestHelper.createProductAuctionResponse());
        Mockito.when(auctionService.findAllAuctions()).thenReturn(productAuctionResponseList);

        ResponseEntity<BaseResponse> responseEntity = auctionController.getAllProductAuctions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(Constants.SUCCESS_MSG_PRODDUCT_AUCTION_LIST_FETCHED_SUCCESFULLY, Objects.requireNonNull(responseEntity.getBody()).getResponseMessage());;
    }

}
