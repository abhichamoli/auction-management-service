package com.test.auctionservice.client;

import com.test.auctionservice.common.AuctionServiceConfigReader;
import com.test.auctionservice.common.ProductManagerAPIIdentifier;
import com.test.auctionservice.dto.ProductResponse;
import com.test.auctionservice.exception.AuctionServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ProductManagerClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AuctionServiceConfigReader auctionServiceConfigReader;

    public ProductResponse getProduct(String productUUID) throws AuctionServiceException {
        String url = StringUtils.join(auctionServiceConfigReader.getProductManagerServiceUrl(), ProductManagerAPIIdentifier.GET_PRODUCT_BY_UUID.getUrl());
        ProductResponse productResponse = null;

        try{
            log.info("product-manager|GET|getProduct|productUUID: [{}]", productUUID);
           productResponse = restTemplate.getForObject(url, ProductResponse.class, productUUID);
        }catch (RestClientException ex){
            log.error("error occurred when fetching product for productUUID: [{}]", productUUID);
            throw new AuctionServiceException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return productResponse;
    }

}
