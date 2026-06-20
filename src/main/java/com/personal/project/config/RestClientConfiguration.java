package com.personal.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.RestClient;

@Configuration
class RestClientConfiguration {

    @Bean
    RestClient restClient() {
        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .defaultApiVersion("1.0")
                .apiVersionInserter(ApiVersionInserter.useHeader("API-version"))
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .build();
    }

}
