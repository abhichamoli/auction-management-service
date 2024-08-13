package com.test.auctionservice.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auction-service")
@Data
public class AuctionServiceConfigReader {
    int connectionTimeOut;
    int readTimeOut;
    String productManagerServiceUrl;
    String kafkaBoostrapServer;
    String kafkaTopic;
}
