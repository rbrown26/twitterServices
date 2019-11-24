package com.csis656.twitter.twitterservices.repository;

import com.csis656.twitter.twitterservices.model.Tweet;

import java.util.List;
import java.util.UUID;

public interface TweetRepository extends BaseRepository<Tweet, Long> {

    List<Tweet> findAllById(UUID createdBy);

    Tweet findFirstById(UUID createdBy);
}
