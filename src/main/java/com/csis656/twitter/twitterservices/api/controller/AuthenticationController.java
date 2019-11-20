package com.csis656.twitter.twitterservices.api.controller;

import com.csis656.twitter.twitterservices.api.exception.AuthenticationException;
import com.csis656.twitter.twitterservices.api.mapping.request.AuthenticationRequest;
import com.csis656.twitter.twitterservices.api.mapping.request.RegistrationRequestObject;
import com.csis656.twitter.twitterservices.api.mapping.response.UserResponse;
import com.csis656.twitter.twitterservices.config.util.JwtTokenUtil;
import com.csis656.twitter.twitterservices.model.User;
import com.csis656.twitter.twitterservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;

    private UserService userService;

    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity register(@RequestBody RegistrationRequestObject registrationRequest) {
        userService.create(registrationRequest);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<UserResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        String authenticationEmail = authenticationRequest.getEmailAddress();
        authenticate(authenticationEmail, authenticationRequest.getPassword());

        // Reload user post auth to populate token
        final User user = userService.getUserByEmailAddress(authenticationEmail);
        Map<String, Object> roleMap = new HashMap<>();
        String userEmail = user.getEmailAddress();
        roleMap.put("sub", userEmail);
        final String token = jwtTokenUtil.generateToken(roleMap);

        return ResponseEntity.ok(new UserResponse(user.getId(), userEmail, token));
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
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }
}
