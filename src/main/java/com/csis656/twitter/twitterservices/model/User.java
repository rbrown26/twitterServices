package com.csis656.twitter.twitterservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class User extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String emailAddress;

    @NotBlank
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public static User create(@NonNull String emailAddress,
                              @NonNull String password) {

        return new User(emailAddress, password);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
