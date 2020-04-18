package com.duduanan.auth.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class FeignConfiguration {

    @Autowired
    private OAuth2ClientContext clientContext;

    @Bean
//    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {

        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        clientCredentialsResourceDetails.setAccessTokenUri("http://localhost:8090/api-uaa/oauth/token");
        clientCredentialsResourceDetails.setClientId("user-service");
        clientCredentialsResourceDetails.setGrantType("client_credentials");
        clientCredentialsResourceDetails.setClientSecret("password");
        clientCredentialsResourceDetails.setScope(Arrays.asList("server"));
        return clientCredentialsResourceDetails;
    }

    @Bean
    public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails(), oauth2ClientContext);
    }
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(clientContext, clientCredentialsResourceDetails());
    }

}
