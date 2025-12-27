package com.example.prod_ready_feature.configs;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;


import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Configuration
public class RestClientConfig {

    @Value("${employeeService.base.url}")
    private String BASE_URL;

    @Bean
    @Qualifier("employeeRestClient")
    RestClient getEmployeeRestClient(){
        return (RestClient) RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeaders(headers ->
                        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                )
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(req,res)->{
                    throw  new RuntimeException("Server error occured");
        })
                .build();
    }
}
