package com.csis656.twitter.twitterservices.api.mapping.request;

public class ConnectionRequestObject {
    private int followed;

    private int follower;

    public ConnectionRequestObject(int followed, int follower) {
        this.followed = followed;
        this.follower = follower;
    }

    public int getFollowed() {
        return followed;
    }

    public int getFollower() {
        return follower;
    }
}
