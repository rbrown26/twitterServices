package com.csis656.twitter.twitterservices.api.mapping.request;

public class RegistrationRequestObject {
    private String password;

    private String emailAddress;

    public RegistrationRequestObject(String password, String emailAddress) {
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}

