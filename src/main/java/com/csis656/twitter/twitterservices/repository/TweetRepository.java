package com.csis656.twitter.twitterservices.repository;

import com.csis656.twitter.twitterservices.model.Tweet;

import java.util.List;

public interface TweetRepository extends BaseRepository<Tweet, Long> {

    List<Tweet> findAllByUserId(String userId);

    Tweet findFirstByUserId(String userId);
}
