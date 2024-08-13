package com.test.bidservice.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bid-service")
@Data
public class BidServiceConfigReader {
    String auctionServiceUrl;
    int readTimeOut;
    int connectionTimeOut;
}
