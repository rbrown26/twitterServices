package com.csis656.twitter.twitterservices.repository;

import com.csis656.twitter.twitterservices.model.TwitterUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TwitterUserRepository extends BaseRepository<TwitterUser, Long> {

    Optional<TwitterUser> findByUsername(String username);

    TwitterUser getUserById(UUID id);

    List<TwitterUser> findAll();
}
