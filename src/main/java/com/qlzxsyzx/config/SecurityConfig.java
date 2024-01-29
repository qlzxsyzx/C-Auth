package com.qlzxsyzx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Autowired
    private DataSource dataSource;

    @Value("${uaa.jwt.sign-key}")
    private String signKey;

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 返回你想要的密码编码器
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        // 返回你自定义的ClientDetailsService的实现
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        CustomTokenEnhancer customTokenEnhancer = new CustomTokenEnhancer();
        customTokenEnhancer.setSigningKey(signKey);
        // 给refresh_token的authentication添加信息
        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
        defaultUserAuthenticationConverter.setUserDetailsService(customUserDetailsService);
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(defaultUserAuthenticationConverter);
        customTokenEnhancer.setAccessTokenConverter(defaultAccessTokenConverter);
        return customTokenEnhancer;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
