package com.qlzxsyzx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class TokenConfig {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean(name = "cauthorizationServerTokenServices")
    public AuthorizationServerTokenServices tokenServices(TokenStore tokenStore){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setTokenEnhancer(jwtAccessTokenConverter);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(3600); // 令牌默认有效期为1小时
        tokenServices.setRefreshTokenValiditySeconds(604800); // 刷新令牌默认有效期为7天
        return tokenServices;
    }
}
