package com.csis656.twitter.twitterservices.api.mapping.request;

import java.util.UUID;

public class ConnectionRequestObject {
    private UUID followed;
    private UUID follower;
    private UUID id;

    public ConnectionRequestObject(UUID followed, UUID follower, UUID id) {
        this.followed = followed;
        this.follower = follower;
        this.id = id;
    }

    public UUID getFollowed() {
        return followed;
    }

    public UUID getFollower() {
        return follower;
    }

    public UUID getId() {
        return id;
    }
}
