package com.qlzxsyzx.config;

import com.qlzxsyzx.entity.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    // 自定义Token增强器，可以添加额外的信息到Token中

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getPrincipal() instanceof CustomUserDetails){
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("userId", details.getUserId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        }
        return super.enhance(accessToken, authentication);
    }


}
