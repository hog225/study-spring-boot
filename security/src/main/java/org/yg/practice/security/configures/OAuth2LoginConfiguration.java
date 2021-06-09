package org.yg.practice.security.configures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

@Configuration
public class OAuth2LoginConfiguration {

    // @EnableWebSecurity
    // public static class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    //     @Override
    //     protected void configure(HttpSecurity http) throws Exception {
    //         http
    //             .authorizeRequests(authorize -> authorize
    //                 .anyRequest().authenticated()
    //             )
    //             .oauth2Login(withDefaults());
    //     }
    // }

    // // @Bean
    // // public ClientRegistrationRepository clientRegistrationRepository() {
    // //     return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    // // }

    // private ClientRegistration kakaoClientRegistration() {
    //     return ClientRegistration.withRegistrationId("google")
    //         .clientId("google-client-id")
    //         .clientSecret("google-client-secret")
    //         .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
    //         .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
    //         .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
    //         .scope("openid", "profile", "email", "address", "phone")
    //         .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
    //         .tokenUri("https://www.googleapis.com/oauth2/v4/token")
    //         .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
    //         .userNameAttributeName(IdTokenClaimNames.SUB)
    //         .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
    //         .clientName("Google")
    //         .build();
    // }
}