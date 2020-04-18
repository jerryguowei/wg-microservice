package com.duduanan.wgusercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOAuth2Client
public class WgUsercenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WgUsercenterApplication.class, args);
    }

}
