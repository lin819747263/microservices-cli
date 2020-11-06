package com.linmsen.oauth2.store;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "linmsen.oauth2.token.store", name = "type", havingValue = "authJwt")
public class AuthJwtTokenStore {
}
