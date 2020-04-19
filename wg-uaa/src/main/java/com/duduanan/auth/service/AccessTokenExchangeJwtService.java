package com.duduanan.auth.service;

import com.duduanan.auth.token.CustomOauth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenExchangeJwtService {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtTokenEnhancer;

    public String exhangeJwt(String tokenValue) throws InternalAuthenticationServiceException {

        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);


        if (accessToken != null) {
            if (accessToken.isExpired()) {
                throw new InternalAuthenticationServiceException("token is expired.");
            }
            if(accessToken instanceof CustomOauth2AccessToken){
                return ((CustomOauth2AccessToken) accessToken).getJwtAccessToken();
            }
            OAuth2Authentication authentication = tokenStore.readAuthentication(tokenValue);
            if(authentication == null){
                throw new InternalAuthenticationServiceException("token is invalid.");
            }
            OAuth2AccessToken enhancedAccessToken = jwtTokenEnhancer.enhance(accessToken, authentication);
            return enhancedAccessToken.getValue();
        }
        throw new InternalAuthenticationServiceException("token is invalid.");
    }
}
