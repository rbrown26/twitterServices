package com.csis656.twitter.twitterservices.config.security;

import com.csis656.twitter.twitterservices.model.User;
import com.csis656.twitter.twitterservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AuthorizationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private UserService userService;

    @Autowired
    public AuthorizationConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return emailAddress -> {
            User user = userService.getUserByEmailAddress(emailAddress);
            return new org.springframework.security.core.userdetails.User(user.getEmailAddress(), user.getPassword(),
                    true, true, true, true, AuthorityUtils.createAuthorityList(user.getEmailAddress()));
        };
    }
}
