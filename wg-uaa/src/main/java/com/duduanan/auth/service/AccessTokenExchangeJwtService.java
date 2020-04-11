package com.duduanan.auth.service;

import com.duduanan.auth.constant.AuthConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AccessTokenExchangeJwtService {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private JwtAccessTokenConverter jwtTokenEnhancer;

    public String exhangeJwt(String tokenValue) throws InternalAuthenticationServiceException {
        OAuth2AccessToken jwtObject = (OAuth2AccessToken) redisTemplate.opsForValue().get(tokenRedisKey(tokenValue));
        if (jwtObject != null) {
            return jwtObject.getValue();
        }

        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        OAuth2Authentication authentication = tokenStore.readAuthentication(tokenValue);

        if (accessToken != null && authentication != null) {
            if (accessToken.isExpired()) {
                throw new InternalAuthenticationServiceException("token is expired.");
            }

            OAuth2AccessToken enhancedAccesToken = jwtTokenEnhancer.enhance(accessToken, authentication);
            storeJwtToken(enhancedAccesToken);
            return enhancedAccesToken.getValue();
        }
        throw new InternalAuthenticationServiceException("token is invalid.");
    }


    private String tokenRedisKey(String tokenValue) {
        return AuthConstant.TOKEN_TO_JWT + ":" + tokenValue;
    }

    private void storeJwtToken(OAuth2AccessToken enhancedAccesToken) {
        int seconds = enhancedAccesToken.getExpiresIn();
        String key = tokenRedisKey(enhancedAccesToken.getAdditionalInformation().get(AccessTokenConverter.JTI).toString());
        redisTemplate.opsForValue().set(key, enhancedAccesToken, seconds, TimeUnit.SECONDS);
    }
}
