package com.test.auctionservice.service;

import com.test.auctionservice.client.ProductManagerClient;
import com.test.auctionservice.common.ProductAuctionStatusEnum;
import com.test.auctionservice.dto.BaseResponse;
import com.test.auctionservice.dto.BidRequest;
import com.test.auctionservice.dto.ProductAuctionResponse;
import com.test.auctionservice.dto.ProductResponse;
import com.test.auctionservice.entity.AuctionSlot;
import com.test.auctionservice.entity.ProductAuction;
import com.test.auctionservice.exception.AuctionServiceException;
import com.test.auctionservice.exception.BadRequestException;
import com.test.auctionservice.exception.ResourceNotFoundException;
import com.test.auctionservice.repository.AuctionSlotRepository;
import com.test.auctionservice.repository.ProductAuctionRepository;
import com.test.auctionservice.common.Constants;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionSlotRepository auctionSlotRepository;

    @Autowired
    private ProductAuctionRepository productAuctionRepository;

    @Autowired
    private ProductManagerClient productManagerClient;

    @Override
    public void mapProductToAuctionSlot(String productUUID, Long slotId) throws AuctionServiceException {
        ProductResponse productResponse = productManagerClient.getProduct(productUUID);
        Optional<AuctionSlot> auctionSlot = auctionSlotRepository.findById(slotId);

        AuctionSlot auctionSlotValue = auctionSlot.orElseThrow(() -> {
            log.error("Auction slot not found for slotId: [{}]", slotId);
            return new ResourceNotFoundException(String.format(Constants.AUCTION_SLOT_NOT_FOUND_FOR_SLOT_ID, slotId));
        });

        ProductAuction productAuction = createProductAuction(productUUID, auctionSlotValue, productResponse);
        productAuctionRepository.save(productAuction);
    }

    @Override
    @Transactional
    public BaseResponse acceptBid(String auctionUUID, BidRequest bidRequest) throws AuctionServiceException {
        Optional<ProductAuction> productAuction = productAuctionRepository.findByAuctionUUID(auctionUUID);

        ProductAuction productAuctionValue = productAuction.orElseThrow(() -> {
            log.error("Product Auction not found for AuctionUUID: [{}] not found", auctionUUID);
            return new ResourceNotFoundException(String.format(Constants.PRODUCT_AUCTION_NOT_FOUND_FOR_AUCTION_UUID, auctionUUID));
        });

        verifyBid(productAuctionValue, bidRequest, auctionUUID);

        productAuctionValue.setHighestbid(bidRequest.getBidAmount());
        productAuctionValue.setHighestBidderId(bidRequest.getUserId());
        productAuctionRepository.save(productAuctionValue);

        return new BaseResponse(HttpStatus.OK.value(), String.format(Constants.SUCCESSFULLY_PLACED_BID_FOR_AUCTION_UUID, auctionUUID));
    }

    @Override
    public void deleteProductFromAuctionSlot(Long auctionId) throws AuctionServiceException {
        Optional<ProductAuction> productAuction = productAuctionRepository.findById(auctionId);
        if (productAuction.isEmpty()) {
            throw new ResourceNotFoundException(String.format(Constants.PRODUCT_AUCTION_NOT_FOUND_FOR_AUCTION_ID, auctionId));
        }
        productAuctionRepository.deleteById(auctionId);
    }

    @Override
    public List<ProductAuctionResponse> findAllAuctions() {
        List<ProductAuction> productAuctions = productAuctionRepository.findAll();
        return productAuctions.stream().map(this::createProductAuctionRespone).toList();
    }

    @Override
    public ProductAuctionResponse findProductAuction(Long auctionId) throws AuctionServiceException {
        Optional<ProductAuction> productAuction = productAuctionRepository.findById(auctionId);
        ProductAuction productAuctionVaue = productAuction.orElseThrow(() -> {
            log.info("Product Auction not found for auctionId: {}", auctionId);
            return new ResourceNotFoundException(String.format(Constants.PRODUCT_AUCTION_NOT_FOUND_FOR_AUCTION_ID, auctionId));
        });

        return createProductAuctionRespone(productAuctionVaue);
    }

    @Override
    public List<ProductAuction> findEndedAuctions() {
        return productAuctionRepository.findByStatus(ProductAuctionStatusEnum.ENDED);
    }

    @Override
    public List<ProductAuctionResponse> getProuductAuctionsForSlotId(Long slotId) {
        List<ProductAuction> productAuctions = productAuctionRepository.findByAuctionSlot(slotId);
        return productAuctions.stream().map(this::createProductAuctionRespone).toList();
    }

    private void verifyBid(ProductAuction productAuction, BidRequest bidRequest, String auctionUUID) throws AuctionServiceException {
        BigDecimal basePrice = productAuction.getBasePrice();
        BigDecimal highestBid = productAuction.getHighestbid();
        BigDecimal bidAmount = bidRequest.getBidAmount();

        if (!ProductAuctionStatusEnum.ACTIVE.equals(productAuction.getStatus())) {
            log.error("auction is not active of auctionUUID: [{}]", auctionUUID);
            throw new BadRequestException(String.format(Constants.ERR_MSG_AUCTION_WITH_UUID_IS_NOT_ACTIVE, auctionUUID));
        }
        if (bidAmount.compareTo(basePrice) < 0) {
            log.error("bidAmount: [{}] is lower than basePrice: [{}]", bidAmount, basePrice);
            throw new BadRequestException(String.format(Constants.ERR_MSG_BID_AMOUNT_IS_LOWER_THAN_BASE_PRICE, bidAmount, basePrice));
        }
        if (Objects.nonNull(highestBid) && bidRequest.getBidAmount().compareTo(highestBid) <= 0) {
            log.error("bidAmount: [{}] should be more than current highest bid: [{}]", bidAmount, highestBid);
            throw new BadRequestException(String.format(Constants.ERR_MSG_BID_AMOUNT_LOWER_THAN_OR_EQUALS_TO_HIGHEST_BID, bidAmount, highestBid));
        }
    }

    private ProductAuction createProductAuction(String productUUID, AuctionSlot auctionSlot, ProductResponse productResponse) {
        return ProductAuction.builder()
                .productUUID(productUUID)
                .productName(productResponse.getProductName())
                .auctionSlot(auctionSlot)
                .auctionUUID(UUID.randomUUID().toString())
                .basePrice(productResponse.getBasePrice())
                .status(ProductAuctionStatusEnum.INACTIVE)
                .build();
    }

    private ProductAuctionResponse createProductAuctionRespone(ProductAuction productAuction) {
        return ProductAuctionResponse.builder()
                .auctionUUID(productAuction.getAuctionUUID())
                .productUUID(productAuction.getProductUUID())
                .basePrice(productAuction.getBasePrice())
                .highestbid(productAuction.getHighestbid())
                .productName(productAuction.getProductName())
                .status(productAuction.getStatus())
                .highestBidderId(productAuction.getHighestBidderId())
                .slotId(productAuction.getAuctionSlot().getSlotId())
                .build();
    }
}
