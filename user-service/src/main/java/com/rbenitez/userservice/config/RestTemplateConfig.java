package com.rbenitez.userservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean //para inyectarlo
    @LoadBalanced // <-- Cuando utiliza Eureka
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
