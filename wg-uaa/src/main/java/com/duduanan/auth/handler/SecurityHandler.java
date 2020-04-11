package com.duduanan.auth.handler;

import org.apache.tomcat.util.http.ResponseUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Configuration
public class SecurityHandler {

    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                OAuth2Exception oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
                ResponseEntity<OAuth2Exception> response = super.translate(oAuth2Exception);

                ResponseEntity.status(oAuth2Exception.getHttpErrorCode());
                response.getBody().addAdditionalInformation("status", oAuth2Exception.getHttpErrorCode() + "");
                response.getBody().addAdditionalInformation("message", oAuth2Exception.getMessage());
                return response;
            }
        };
    }

    @Bean
    public AuthenticationEntryPoint customBasicAuthenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            private String realmName = "Realm";
            private ObjectMapper objectMapper = new ObjectMapper();

            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
                    throws IOException {
                response.addHeader("WWW-Authenticate", "Basic realm=\"" + realmName + "\"");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                Map<String, Object> errorAttributes = new LinkedHashMap<>();
                errorAttributes.put("timestamp", new Date());
                errorAttributes.put("status", response.getStatus());
                errorAttributes.put("message", authException.getMessage());
                response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                response.getOutputStream().println(objectMapper.writeValueAsString(errorAttributes));
            }
        };
    }
}
