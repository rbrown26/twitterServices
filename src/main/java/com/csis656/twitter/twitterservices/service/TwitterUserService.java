package com.csis656.twitter.twitterservices.service;

import com.csis656.twitter.twitterservices.api.mapping.request.RegistrationRequestObject;
import com.csis656.twitter.twitterservices.model.TwitterUser;
import com.csis656.twitter.twitterservices.repository.TwitterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterUserService {
    private static final int ENCODING_STRENGTH = 12;

    private TwitterUserRepository twitterUserRepository;

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(ENCODING_STRENGTH);
    }

    @Autowired
    public TwitterUserService(TwitterUserRepository twitterUserRepository) {
        this.twitterUserRepository = twitterUserRepository;
    }

    public List<TwitterUser> findAll() {
        return twitterUserRepository.findAll();
    }

    public TwitterUser create(RegistrationRequestObject registrationRequestObject) {
        TwitterUser twitterUser = TwitterUser.create(registrationRequestObject.getUsername(),
                passwordEncoder().encode(registrationRequestObject.getPassword()));

        return twitterUserRepository.save(twitterUser);
    }

    public TwitterUser getUserByUsername(String username) throws UsernameNotFoundException {
        return twitterUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Could not find username: " + username));
    }
}
