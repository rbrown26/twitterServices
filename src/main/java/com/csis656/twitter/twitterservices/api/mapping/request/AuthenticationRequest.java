package com.csis656.twitter.twitterservices.api.mapping.request;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

    private String username;

    private String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
