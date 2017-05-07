package com.github.wkennedy;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerOAuthConfig extends OAuth2AuthorizationServerConfiguration {
//
    //extends AuthorizationServerConfigurerAdapter {

    public AuthorizationServerOAuthConfig(BaseClientDetails details, AuthenticationManager authenticationManager, ObjectProvider<TokenStore> tokenStore, ObjectProvider<AccessTokenConverter> tokenConverter, AuthorizationServerProperties properties) {
        super(details, authenticationManager, tokenStore, tokenConverter, properties);
        details.setScope(Collections.singletonList("read"));
        details.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

//
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    public AuthorizationServerOAuthConfig(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
//      endpoints.authenticationManager(authenticationManager);
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("myclient")
//                .secret("mysecret")
//                .authorizedGrantTypes("authorization_code", "refresh_token", "password", "client_credentials")
//                .authorities("ROLE_USER")
//                .redirectUris("http://localhost:8080/api/person")
//                .scopes("read", "write", "trust")
//                .autoApprove(true);
//    }

}
