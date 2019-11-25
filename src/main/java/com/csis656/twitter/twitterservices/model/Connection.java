package com.csis656.twitter.twitterservices.model;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Connection extends BaseEntity {

    @Column(nullable = false)
    private UUID followed;

    @Column(nullable = false)
    private UUID follower;

    private Connection() {

    }

    private Connection(UUID followed, UUID follower) {
        this.followed = followed;
        this.follower = follower;
    }

    public static Connection create(@NonNull UUID followed,
                              @NonNull UUID follower) {

        return new Connection(followed, follower);
    }

    public UUID getFollowed() {
        return followed;
    }

    public UUID getFollower() {
        return follower;
    }
}
