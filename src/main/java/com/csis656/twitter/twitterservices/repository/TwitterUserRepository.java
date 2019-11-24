package com.csis656.twitter.twitterservices.repository;

import com.csis656.twitter.twitterservices.model.TwitterUser;

import java.util.Optional;

public interface TwitterUserRepository extends BaseRepository<TwitterUser, Long> {

    Optional<TwitterUser> findByUsername(String username);
}
