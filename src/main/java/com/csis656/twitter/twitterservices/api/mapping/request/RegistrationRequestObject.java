package com.csis656.twitter.twitterservices.api.mapping.request;

public class RegistrationRequestObject {
    private String password;
    private String username;

    public RegistrationRequestObject(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}

