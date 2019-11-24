package com.csis656.twitter.twitterservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class TwitterUser extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String username;

    @NotBlank
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private TwitterUser() {

    }

    private TwitterUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static TwitterUser create(@NonNull String username,
                                     @NonNull String password) {

        return new TwitterUser(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
