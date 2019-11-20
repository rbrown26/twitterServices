package com.csis656.twitter.twitterservices.api.mapping.request;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

    private String emailAddress;

    private String password;

    public AuthenticationRequest(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
