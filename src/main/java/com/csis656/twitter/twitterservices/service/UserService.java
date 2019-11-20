package com.csis656.twitter.twitterservices.service;

import com.csis656.twitter.twitterservices.api.mapping.request.RegistrationRequestObject;
import com.csis656.twitter.twitterservices.model.User;
import com.csis656.twitter.twitterservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final int ENCODING_STRENGTH = 12;

    private UserRepository userRepository;

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(ENCODING_STRENGTH);
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(RegistrationRequestObject registrationRequestObject) {
        User user = User.create(registrationRequestObject.getEmailAddress(),
                passwordEncoder().encode(registrationRequestObject.getPassword()));

        return userRepository.save(user);
    }

    public User getUserByEmailAddress(String emailAddress) throws UsernameNotFoundException {
        return userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new UsernameNotFoundException("Could not find email: " + emailAddress));
    }
}
