package com.csis656.twitter.twitterservices.model;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Connection extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private int followed;

    @NotBlank
    @Column(nullable = false)
    private int follower;

    private Connection() {

    }

    private Connection(int followed, int follower) {
        this.followed = followed;
        this.follower = follower;
    }

    public static Connection create(@NonNull int followed,
                              @NonNull int follower) {

        return new Connection(followed, follower);
    }

    public int getFollowed() {
        return followed;
    }

    public int getFollower() {
        return follower;
    }
}
