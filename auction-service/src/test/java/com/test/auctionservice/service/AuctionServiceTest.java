package com.test.auctionservice.service;

import com.test.auctionservice.client.ProductManagerClient;
import com.test.auctionservice.common.TestHelper;
import com.test.auctionservice.dto.BaseResponse;
import com.test.auctionservice.dto.BidRequest;
import com.test.auctionservice.entity.ProductAuction;
import com.test.auctionservice.exception.AuctionServiceException;
import com.test.auctionservice.exception.BadRequestException;
import com.test.auctionservice.exception.ResourceNotFoundException;
import com.test.auctionservice.repository.AuctionSlotRepository;
import com.test.auctionservice.repository.ProductAuctionRepository;
import com.test.auctionservice.common.Constants;
import com.test.auctionservice.common.TestConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AuctionServiceTest {

    @InjectMocks
    AuctionServiceImpl auctionService;

    @Mock
    ProductAuctionRepository productAuctionRepository;

    @Mock
    ProductManagerClient productManagerClient;

    @Mock
    AuctionSlotRepository auctionSlotRepository;

    @Test
    public void testMapToAuctionSlot() throws AuctionServiceException {
        Mockito.when(productManagerClient.getProduct(TestConstants.PRODUCT_UUID)).thenReturn(TestHelper.createProductResposne());
        Mockito.when(auctionSlotRepository.findById(TestConstants.SLOT_ID)).thenReturn(Optional.of(TestHelper.createAuctionSlot()));

        auctionService.mapProductToAuctionSlot(TestConstants.PRODUCT_UUID, TestConstants.SLOT_ID);
        Mockito.verify(productAuctionRepository, Mockito.times(1)).save(Mockito.any(ProductAuction.class));
    }

    @Test
    public void testMapToAuctionSlotWhenSlotIDNotFound() throws AuctionServiceException {
        Mockito.when(productManagerClient.getProduct(TestConstants.PRODUCT_UUID)).thenReturn(TestHelper.createProductResposne());
        Mockito.when(auctionSlotRepository.findById(TestConstants.SLOT_ID)).thenReturn(Optional.ofNullable(null));

        Assert.assertThrows(ResourceNotFoundException.class, () -> auctionService.mapProductToAuctionSlot(TestConstants.PRODUCT_UUID, TestConstants.SLOT_ID));

    }

    @Test
    public void testAcceptBid() throws AuctionServiceException {
        Mockito.when(productAuctionRepository.findByAuctionUUID(TestConstants.AUCTION_UUID)).thenReturn(Optional.ofNullable(TestHelper.createProductAuction()));

        BaseResponse baseResponse = auctionService.acceptBid(TestConstants.AUCTION_UUID, TestHelper.createBidRequest());

        Assert.assertEquals(String.format(Constants.SUCCESSFULLY_PLACED_BID_FOR_AUCTION_UUID, TestConstants.AUCTION_UUID), baseResponse.getResponseMessage());
        Mockito.verify(productAuctionRepository, Mockito.times(1)).save(Mockito.any(ProductAuction.class));
    }

    @Test
    public void testAcceptBidForInvalidBet() throws AuctionServiceException {
        Mockito.when(productAuctionRepository.findByAuctionUUID(TestConstants.AUCTION_UUID)).thenReturn(Optional.ofNullable(TestHelper.createProductAuction()));
        BidRequest bidRequest = TestHelper.createBidRequest();
        bidRequest.setBidAmount(new BigDecimal(1));

        auctionService.acceptBid(TestConstants.AUCTION_UUID, TestHelper.createBidRequest());

        Assert.assertThrows(BadRequestException.class, () -> auctionService.acceptBid(TestConstants.PRODUCT_UUID, bidRequest));
    }

}
