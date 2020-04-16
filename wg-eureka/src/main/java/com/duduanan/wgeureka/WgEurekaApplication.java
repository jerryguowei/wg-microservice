package com.duduanan.wgeureka;

import ch.qos.logback.core.hook.ShutdownHook;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.guice.EurekaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;

@SpringBootApplication
@EnableEurekaServer
public class WgEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WgEurekaApplication.class, args);
    }
}
