package com.bunker.authservice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.bunker.authservice.service.AuthClientDetailsService;
import com.bunker.authservice.service.CustomUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthClientDetailsService authClientDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private DataSource dataSource;

    private String privateKey = "yourPrivateKey"; // This should come from a environment variable in production
    private String publicKey = "yourPublicKey"; // This should come from a environment variable in production

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
	endpoints.tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer())
		.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
	oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(encoder)
		.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	clients.withClientDetails(authClientDetailsService);
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
	JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	converter.setSigningKey(privateKey);
//        converter.setVerifierKey(publicKey);
	return converter;
    }

    @Bean
    public TokenStore tokenStore() {
	return new JwtTokenStore(tokenEnhancer());
    }

}
