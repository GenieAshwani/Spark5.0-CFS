package com.cfs.Order_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConnectionConfig {


    @Bean
    public RestTemplate getInstance()
    {
        return new RestTemplate();
    }
}
