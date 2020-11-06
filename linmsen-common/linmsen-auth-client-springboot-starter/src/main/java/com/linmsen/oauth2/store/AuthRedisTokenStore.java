package com.linmsen.oauth2.store;


import com.linmsen.oauth2.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

@ConditionalOnProperty(prefix = "linmsen.oauth2.token.store", name = "type", havingValue = "redis")
public class AuthRedisTokenStore {

    @Bean
    public TokenStore tokenStore(RedisConnectionFactory connectionFactory, SecurityProperties securityProperties) {
        return new CustomRedisTokenStore(connectionFactory, securityProperties);
    }
}
