package com.practice.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aleksey Konkin
 * @since 24.12.2022
 */
@Configuration
public class ClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
