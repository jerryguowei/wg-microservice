package com.duduanan.auth.config;

import com.duduanan.auth.common.config.KeyStoreProperty;
import com.duduanan.auth.service.RedisClientDetailsService;
import com.duduanan.auth.service.SysUserDetailsService;
import com.duduanan.auth.token.CustomJwtAccessTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;
import java.security.*;
import java.security.cert.Certificate;
import java.util.LinkedHashMap;
import java.util.Map;

@EnableAuthorizationServer
@Configuration
@EnableConfigurationProperties(KeyStoreProperty.class)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private SysUserDetailsService userDetailsService;

    @Autowired
    private RedisClientDetailsService clientDetailsService;

    @Autowired
    @Qualifier("userAuthenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;
    @Autowired
    @Qualifier("customBasicAuthenticationEntryPoint")
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private KeyStoreProperty keyStoreProperty;

    @Value("${wg.auth-issuer}")
    private String authIssuer;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .authenticationEntryPoint(authenticationEntryPoint)
                .passwordEncoder(passwordEncoder).allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenEnhancer(accessTokenConverter())
                .exceptionTranslator(webResponseExceptionTranslator);
    }

    @Bean
    public TokenStore tokenStore() {
        TokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

    @Bean
    public KeyPair jwsKeyPair() {
        KeyStore keyStore;
        try {

            keyStore = KeyStore.getInstance(keyStoreProperty.getType());
            char[] password = keyStoreProperty.getPassword() != null ? keyStoreProperty.getPassword().toCharArray() : null;
            keyStore.load(keyStoreProperty.getLocation().getInputStream(), password);
            char[] secret = keyStoreProperty.getSecret().toCharArray();
            Key privateKey = keyStore.getKey(keyStoreProperty.getAlias(), secret);
            Certificate certificate = keyStore.getCertificate(keyStoreProperty.getAlias());
            PublicKey publicKey = certificate.getPublicKey();
            return new KeyPair(publicKey, (PrivateKey) privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
        converter.setKeyPair(jwsKeyPair());
        DefaultAccessTokenConverter accessTokenConverter = new CustomAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new DefaultUserAuthenticationConverter());
        converter.setAccessTokenConverter(accessTokenConverter);
        return converter;
    }

    public class CustomAccessTokenConverter extends  DefaultAccessTokenConverter {
        @Override
        public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
            Map<String, String>  result = (Map<String, String>) super.convertAccessToken(token, authentication);
            result.put("iss", authIssuer);
            return result;
        }
    }
}

@Configuration
@Order(101)
class UserConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean("userAuthenticationManagerBean")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
