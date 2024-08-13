package com.test.auctionservice.config;

import com.test.auctionservice.common.AuctionServiceConfigReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ApplicationConfig {

    @Autowired
    private AuctionServiceConfigReader auctionServiceConfigReader;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(auctionServiceConfigReader.getConnectionTimeOut()))
                .setReadTimeout(Duration.ofSeconds(auctionServiceConfigReader.getReadTimeOut()))
                .build();
    }
}
