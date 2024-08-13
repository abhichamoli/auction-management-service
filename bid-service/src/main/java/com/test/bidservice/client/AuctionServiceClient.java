package com.test.bidservice.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bidservice.common.AuctionServiceAPIIdentifier;
import com.test.bidservice.common.BidServiceConfigReader;
import com.test.bidservice.dto.BaseResponse;
import com.test.bidservice.dto.BidRequest;
import com.test.bidservice.exception.BidServiceException;
import com.test.bidservice.exception.ServiceUnAvailableException;
import com.test.bidservice.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
public class AuctionServiceClient {

    @Autowired
    private BidServiceConfigReader bidServiceConfigReader;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Retryable(value = { HttpServerErrorException.ServiceUnavailable.class }, maxAttempts = 3,  backoff = @Backoff(delay = 2000))
    public BaseResponse placeBid(String auctionUUID, BidRequest bidRequest) throws BidServiceException {
        String baseUrl = StringUtils.join(bidServiceConfigReader.getAuctionServiceUrl(), AuctionServiceAPIIdentifier.PLACE_BID.getUrl());
        BaseResponse baseResponse = null;
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .buildAndExpand(auctionUUID)
                .toUriString();

        try {
            log.info("auction-service|POST|placeBid|auctionUUID: [{}]|bidRequest: {}", auctionUUID, bidRequest.toString());
            baseResponse = restTemplate.postForObject(url, bidRequest, BaseResponse.class);
        }catch (HttpServerErrorException.ServiceUnavailable ex) {
            log.error("Auction Service unavailable while placing bid for auctionUUID: [{}] error msg: {}", auctionUUID, ex.getMessage());
            throw new ServiceUnAvailableException("Auction Service unavailable: " + ex.getMessage());
        }
        catch (HttpClientErrorException ex) {
            String message = extractResponse(ex.getResponseBodyAsString());
            log.error("error occured while placing bid for auctionUUID: [{}] error msg: {}", auctionUUID, ex.getMessage());
            throw new BidServiceException(message,HttpStatus.BAD_REQUEST);
        }
        return baseResponse;
    }

    private String extractResponse(String message) throws BidServiceException {
        String response = StringUtils.EMPTY;
        try {
            JsonNode responseJson = objectMapper.readTree(message);
            response = responseJson.get("error").asText();
        } catch (Exception e) {
            log.error("Failed to parse error response: {}", message);
            throw new BidServiceException(Constants.ERROR_MSG_INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
