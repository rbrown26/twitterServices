package com.csis656.twitter.twitterservices.model;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class Tweet extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String message;

//    @Column
//    private int numberOfLikes;

    @Column(nullable = false)
    private UUID createdBy;


    private Tweet(String message, UUID createdBy) {
        this.message = message;
        this.createdBy = createdBy;
    }

    public static Tweet create(@NonNull String message,
                               @NonNull UUID createdBy) {

        return new Tweet(message, createdBy);
    }

    public String getMessage() {
        return message;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

//    public int getNumberOfLikes() {
//        return numberOfLikes;
//    }
}

