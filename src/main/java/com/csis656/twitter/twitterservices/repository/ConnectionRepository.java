package com.csis656.twitter.twitterservices.repository;

import com.csis656.twitter.twitterservices.model.Connection;

import java.util.List;

public interface ConnectionRepository extends BaseRepository<Connection, Long> {

    List<Connection> findFollowersById(int id);

    List<Connection> findFollowedById(int id);
}
