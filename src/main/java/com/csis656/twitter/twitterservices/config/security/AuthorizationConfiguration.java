package com.csis656.twitter.twitterservices.config.security;

import com.csis656.twitter.twitterservices.model.TwitterUser;
import com.csis656.twitter.twitterservices.service.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AuthorizationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private TwitterUserService twitterUserService;

    @Autowired
    public AuthorizationConfiguration(TwitterUserService twitterUserService) {
        this.twitterUserService = twitterUserService;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            TwitterUser twitterUser = twitterUserService.getUserByUsername(username);
            return new org.springframework.security.core.userdetails.User(twitterUser.getUsername(), twitterUser.getPassword(),
                    true, true, true, true, AuthorityUtils.createAuthorityList(twitterUser.getUsername()));
        };
    }
}
