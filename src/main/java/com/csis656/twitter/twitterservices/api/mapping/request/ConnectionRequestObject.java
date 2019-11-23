package com.csis656.twitter.twitterservices.api.mapping.request;

public class ConnectionRequestObject {
    private int followed;
    private int follower;
    private int id;

    public ConnectionRequestObject(int followed, int follower, int id) {
        this.followed = followed;
        this.follower = follower;
        this.id = id;
    }

    public int getFollowed() {
        return followed;
    }

    public int getFollower() {
        return follower;
    }

    public int getId() {
        return id;
    }
}
