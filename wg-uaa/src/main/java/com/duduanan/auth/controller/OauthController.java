package com.duduanan.auth.controller;

import com.duduanan.auth.config.OpenIdConfiguration;
import com.duduanan.auth.feign.UserCenterFeignClient;
import com.duduanan.auth.service.AccessTokenExchangeJwtService;
import com.duduanan.commons.entity.SysUser;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.Optional;

@RestController
public class OauthController {

    @Autowired
    @Qualifier("jwsKeyPair")
    private KeyPair keyPair;

    @Value("${wg.auth-issuer}")
    private String authIssuer;

    @Autowired
    private AccessTokenExchangeJwtService accessTokenExchangeJwtService;

    @Resource
    private UserCenterFeignClient userCenterFeignClient;


    @PostMapping("/.well-known/home")
    public String home() {
         SysUser user = userCenterFeignClient.findUserByUsername("jerryguowei");
        System.out.println(user);
        return "home";
    }


    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

    @GetMapping(value = "/.well-known/openid-configuration", produces = MediaType.APPLICATION_JSON_VALUE)
    public OpenIdConfiguration getOpenIdConfiguraation() {
        OpenIdConfiguration openIdConfiguration = new OpenIdConfiguration(authIssuer);
        return openIdConfiguration;
    }

    @RequestMapping(value = "/.well-known/exchange_token")

    public String exhangeToken(@RequestParam("access_token") Optional<String> accessToken) {

        if (!accessToken.isPresent()) {
            throw new InternalAuthenticationServiceException("access_token is required");
        }

        String jwtToken = accessTokenExchangeJwtService.exhangeJwt(accessToken.get());

        return jwtToken;
    }
}
