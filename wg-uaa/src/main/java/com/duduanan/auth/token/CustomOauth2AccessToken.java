package com.duduanan.auth.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.Serializable;


public class CustomOauth2AccessToken extends DefaultOAuth2AccessToken implements Serializable {

    private static final long serialVersionUID = -4489853763677180453L;
    private  String jwtAccessToken;

    public CustomOauth2AccessToken(String value) {
        super(value);
    }
    public CustomOauth2AccessToken(OAuth2AccessToken auth2AccessToken){
        super(auth2AccessToken);
    }

    public String getJwtAccessToken() {
        return jwtAccessToken;
    }

    public void setJwtAccessToken(String jwtAccessToken) {
        this.jwtAccessToken = jwtAccessToken;
    }
}
