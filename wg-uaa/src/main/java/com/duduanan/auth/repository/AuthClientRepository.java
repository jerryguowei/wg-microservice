package com.duduanan.auth.repository;

import com.duduanan.auth.entity.AuthClient;
import org.springframework.data.repository.CrudRepository;

public interface AuthClientRepository extends CrudRepository<AuthClient, Integer> {

    AuthClient findAuthClientByClientId(String clientId);
}
