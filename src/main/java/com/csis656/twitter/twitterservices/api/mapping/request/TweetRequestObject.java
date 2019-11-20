package com.csis656.twitter.twitterservices.api.mapping.request;

import java.util.Date;

public class TweetRequestObject {
    private String message;
    private String createdBy;
    private int numberOfLikes;
    private Date createdAt;
    private Date updatedAt;


    public TweetRequestObject(String message, String createdBy, int numberOfLikes, Date createdAt, Date updatedAt) {
        this.message = message;
        this.createdBy = createdBy;
        this.numberOfLikes = numberOfLikes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
