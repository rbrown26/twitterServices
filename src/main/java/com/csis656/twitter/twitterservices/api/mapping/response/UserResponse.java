package com.csis656.twitter.twitterservices.api.mapping.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class UserResponse implements Serializable {

    private final UUID id;

    private final String emailAddress;

    private final String token;

    public UserResponse(UUID id, String emailAddress, String token) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.token = token;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public String getEmailAddress() {
        return emailAddress;
    }

    @JsonProperty
    public String getToken() {
        return token;
    }
}

