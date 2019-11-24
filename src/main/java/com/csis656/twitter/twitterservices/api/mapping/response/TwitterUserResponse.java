package com.csis656.twitter.twitterservices.api.mapping.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class TwitterUserResponse implements Serializable {

    private final UUID id;
    private final String username;


    public TwitterUserResponse(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }
}

