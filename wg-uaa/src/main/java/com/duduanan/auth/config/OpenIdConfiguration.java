package com.duduanan.auth.config;

import org.springframework.stereotype.Component;

public class OpenIdConfiguration {
    private String issuer;
    private String authorizationEndpoint;
    private String tokenEndPoint;
    private String userinfoEndpoint;
    private String jwksUri;
    private String revocationEndpoint;

    public OpenIdConfiguration(String issuer) {
        if(!issuer.endsWith("/")) {
            issuer += "/";
        }
        this.issuer = issuer;
        this.authorizationEndpoint= issuer + "authorize";
        this.tokenEndPoint = issuer + "oauth/token";
        this.userinfoEndpoint = issuer + "userinfo";
        this.jwksUri = issuer + ".well-known/jwks.json";
        this.revocationEndpoint = issuer + "oauth/revoke";
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }

    public void setAuthorizationEndpoint(String authorizationEndpoint) {
        this.authorizationEndpoint = authorizationEndpoint;
    }

    public String getTokenEndPoint() {
        return tokenEndPoint;
    }

    public void setTokenEndPoint(String tokenEndPoint) {
        this.tokenEndPoint = tokenEndPoint;
    }

    public String getUserinfoEndpoint() {
        return userinfoEndpoint;
    }

    public void setUserinfoEndpoint(String userinfoEndpoint) {
        this.userinfoEndpoint = userinfoEndpoint;
    }

    public String getJwksUri() {
        return jwksUri;
    }

    public void setJwksUri(String jwksUri) {
        this.jwksUri = jwksUri;
    }

    public String getRevocationEndpoint() {
        return revocationEndpoint;
    }

    public void setRevocationEndpoint(String revocationEndpoint) {
        this.revocationEndpoint = revocationEndpoint;
    }
}
