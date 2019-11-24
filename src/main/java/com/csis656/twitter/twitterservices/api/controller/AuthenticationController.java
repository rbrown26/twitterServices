package com.csis656.twitter.twitterservices.api.controller;

import com.csis656.twitter.twitterservices.api.exception.AuthenticationException;
import com.csis656.twitter.twitterservices.api.mapping.request.AuthenticationRequest;
import com.csis656.twitter.twitterservices.api.mapping.request.RegistrationRequestObject;
import com.csis656.twitter.twitterservices.api.mapping.response.TwitterUserResponse;
import com.csis656.twitter.twitterservices.config.util.JwtTokenUtil;
import com.csis656.twitter.twitterservices.model.TwitterUser;
import com.csis656.twitter.twitterservices.service.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;

    private TwitterUserService twitterUserService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TwitterUserService twitterUserService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.twitterUserService = twitterUserService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity register(@RequestBody RegistrationRequestObject registrationRequest) {
        twitterUserService.create(registrationRequest);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<TwitterUserResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        String authenticationUsername = authenticationRequest.getUsername();
        authenticate(authenticationUsername, authenticationRequest.getPassword());

        // Reload twitterUser post auth to populate token
        final TwitterUser twitterUser = twitterUserService.getUserByUsername(authenticationUsername);



        return ResponseEntity.ok(new TwitterUserResponse(twitterUser.getId(), authenticationUsername));
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("TwitterUser is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }
}
