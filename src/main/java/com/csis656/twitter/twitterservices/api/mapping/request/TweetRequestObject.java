package com.csis656.twitter.twitterservices.api.mapping.request;

import java.util.Date;
import java.util.UUID;

public class TweetRequestObject {
    private UUID id;
    private String message;
    private UUID createdBy;
//    private int numberOfLikes;
//    private Date createdAt;
//    private Date updatedAt;


    public TweetRequestObject(UUID id, String message, UUID createdBy) {
        this.id = id;
        this.message = message;
        this.createdBy = createdBy;
//        this.numberOfLikes = numberOfLikes;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
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
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
}
