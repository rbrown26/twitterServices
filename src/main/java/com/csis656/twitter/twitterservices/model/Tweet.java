package com.csis656.twitter.twitterservices.model;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Tweet extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String message;

    @Column
    private int numberOfLikes;

    @NotBlank
    @Column(nullable = false)
    private String createdBy;

    @NotBlank
    @Column(nullable = false)
    private String createdAt;

    @Column
    private String updatedAt;

    private Tweet(String message, String createdBy, int numberOfLikes, String createdAt, String updatedAt) {
        this.message = message;
        this.numberOfLikes = numberOfLikes;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Tweet create(@NonNull String message,
                               @NonNull String createdBy,
                               int numberOfLikes,
                               String createdAt) {

        return new Tweet(message, createdBy, numberOfLikes, createdAt, null);
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }
}

