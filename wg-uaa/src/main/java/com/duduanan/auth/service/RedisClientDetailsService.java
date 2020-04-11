package com.duduanan.auth.service;

import com.duduanan.auth.constant.AuthConstant;
import com.duduanan.auth.entity.AuthClient;
import com.duduanan.auth.repository.AuthClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

@Service
public class RedisClientDetailsService implements ClientDetailsService {
    private Logger logger = LoggerFactory.getLogger(RedisClientDetailsService.class.getName());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private AuthClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        ClientDetails clientDetails = (ClientDetails) redisTemplate.opsForValue().get(clientRedisKey(clientId));
        if(clientDetails == null){
            clientDetails = getClientAndCache(clientId);
        }
        if(clientDetails == null){
            throw new NoSuchClientException("client id and password is invalid.");
        }
        return clientDetails;
    }


    private String clientRedisKey(String clientId) {
        return AuthConstant.CACHE_CLIENT_KEY + ":" + clientId;
    }

    private ClientDetails getClientAndCache(String clientId) {
        AuthClient authClient = clientRepository.findAuthClientByClientId(clientId);
        if(authClient != null) {
            ClientDetails clientDetails = new SysClientDetail(authClient);
            redisTemplate.opsForValue().set(clientRedisKey(clientId), clientDetails);
            logger.info("cache clientId:{}, {}", clientId, clientDetails);
            return clientDetails;
        }
        return null;
    }
}
