package com.csis656.twitter.twitterservices.service;

import com.csis656.twitter.twitterservices.api.mapping.request.TweetRequestObject;
import com.csis656.twitter.twitterservices.model.Tweet;
import com.csis656.twitter.twitterservices.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TweetService {

    private TweetRepository tweetRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public Tweet create(TweetRequestObject tweetRequestObject) {
        Tweet tweet = Tweet.create(tweetRequestObject.getMessage(),
                tweetRequestObject.getCreatedBy()
//                tweetRequestObject.getNumberOfLikes(),
//                new Date().toString()
                );

        return tweetRepository.save(tweet);
    }

    public List<Tweet> getAllTweetsByUserId(UUID createdBy) {
        return tweetRepository.findAllById(createdBy);
    }

    public Tweet getFirstTweetByUserId(UUID createdBy) {
        return tweetRepository.findFirstById(createdBy);
    }
}