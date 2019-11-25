package com.csis656.twitter.twitterservices.api.mapping.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class TweetResponse implements Serializable {

    private final UUID id;
    private final String message;

    public TweetResponse(UUID id, String message) {
        this.id = id;
        this.message = message;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }
}
