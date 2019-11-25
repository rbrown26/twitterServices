package com.csis656.twitter.twitterservices.repository;

import com.csis656.twitter.twitterservices.model.Connection;

import java.util.List;
import java.util.UUID;

public interface ConnectionRepository extends BaseRepository<Connection, Long> {

    List<Connection> getAllByFollower(UUID id);

    List<Connection> findFollowersById(UUID id);

    List<Connection> findFollowedById(UUID id);
}
