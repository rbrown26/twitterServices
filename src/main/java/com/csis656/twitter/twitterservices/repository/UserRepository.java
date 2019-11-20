package com.csis656.twitter.twitterservices.repository;

import com.csis656.twitter.twitterservices.model.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmailAddress(String emailAddress);
}
