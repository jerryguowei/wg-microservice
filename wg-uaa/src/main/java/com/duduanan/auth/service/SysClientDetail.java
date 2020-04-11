package com.duduanan.auth.service;

import com.duduanan.auth.entity.AuthClient;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import java.util.*;
import java.util.stream.Collectors;

public class SysClientDetail implements ClientDetails {

    private static final long serialVersionUID = -4483671837450594897L;
    private String clientId;
    private Set<String> resourceIds;
    private String clientSecret;
    private Set<String> scopes;
    private Set<String> authorizedGrantTypes;
    private Set<String> redirectUris;
    private Set<GrantedAuthority> grantedAuthorities;
    private int tokenValidSeconds;
    private int refreshTokenValidSeconds;
    private boolean autoApprove;

    public SysClientDetail(AuthClient client) {
        this.clientId = client.getClientId();
        this.resourceIds = client.getResourceIds() != null ? Arrays.stream(client.getResourceIds().split(","))
                .collect(Collectors.toSet()) : Collections.emptySet();
        this.clientSecret = client.getClientSecret();
        this.scopes = client.getScopes() != null ? Arrays.stream(client.getScopes().split(","))
                .collect(Collectors.toSet()) : Collections.emptySet();
        this.authorizedGrantTypes = client.getAuthorizedGrantTypes() != null
                ? Arrays.stream(client.getAuthorizedGrantTypes().split(",")).collect(Collectors.toSet())
                : Collections.emptySet();
        this.redirectUris = client.getWebServerRedirectUri() != null ? Arrays.stream(client.getWebServerRedirectUri()
                .split(",")).collect(Collectors.toSet()) : Collections.emptySet();
        this.grantedAuthorities = client.getClientRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());
        this.tokenValidSeconds = client.getAccessTokenValidity();
        this.refreshTokenValidSeconds = client.getRefreshTokenValidity();
        this.autoApprove = client.isAutoApprove();
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return redirectUris;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return tokenValidSeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValidSeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }
}
