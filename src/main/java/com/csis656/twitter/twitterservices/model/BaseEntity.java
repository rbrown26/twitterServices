package com.csis656.twitter.twitterservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @JsonIgnore
    private Date dateCreated = new Date();

    @JsonIgnore
    private Date lastChangedDate = new Date();

    public UUID getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastChangedDate() {
        return lastChangedDate;
    }
}
