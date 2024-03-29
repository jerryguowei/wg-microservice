package com.duduanan;

import com.duduanan.auth.constant.AuthConstant;
import com.duduanan.auth.token.CustomOauth2AccessToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFeignClients
public class WgUaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WgUaaApplication.class, args);
    }

}
