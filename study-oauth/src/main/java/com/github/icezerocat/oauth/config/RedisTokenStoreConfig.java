package com.github.icezerocat.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Description: redis的token配置
 * CreateDate:  2021/1/11 9:26
 *
 * @author zero
 * @version 1.0
 */
@Configuration
public class RedisTokenStoreConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisTokenStoreConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * 配置Token存储到Redis中
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(this.redisConnectionFactory);
    }

}
