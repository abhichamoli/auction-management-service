package com.test.bidservice.config;

import com.test.bidservice.common.BidServiceConfigReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Controller
public class ApplicationConfig {

    @Autowired
    BidServiceConfigReader bidServiceConfigReader;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(bidServiceConfigReader.getConnectionTimeOut()))
                .setReadTimeout(Duration.ofSeconds(bidServiceConfigReader.getReadTimeOut()))
                .build();
    }
}
