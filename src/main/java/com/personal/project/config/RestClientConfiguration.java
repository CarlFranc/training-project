package com.personal.project.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
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
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(200);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);

        CloseableHttpClient closeableHttpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .build();

        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory(closeableHttpClient))
                .defaultApiVersion("1.0")
                .apiVersionInserter(ApiVersionInserter.useHeader("API-version"))
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .build();
    }

}
