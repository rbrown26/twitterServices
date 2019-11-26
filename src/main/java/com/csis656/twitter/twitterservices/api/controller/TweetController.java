package com.csis656.twitter.twitterservices.api.controller;

import com.csis656.twitter.twitterservices.api.mapping.request.TweetRequestObject;
import com.csis656.twitter.twitterservices.model.Connection;
import com.csis656.twitter.twitterservices.model.Tweet;
import com.csis656.twitter.twitterservices.service.ConnectionService;
import com.csis656.twitter.twitterservices.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "tweet")
public class TweetController {
    private TweetService tweetService;
    private ConnectionService connectionService;

    @Autowired
    public TweetController(TweetService tweetService, ConnectionService connectionService) {
        this.tweetService = tweetService;
        this.connectionService = connectionService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity add(@RequestBody TweetRequestObject tweetRequestObject) {
        tweetService.create(tweetRequestObject);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{createdBy}/all", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<List<Tweet>> getAllTweetsByCreatedByOrderByDateCreatedDesc(@PathVariable("createdBy") UUID createdBy) {
        List<Tweet> tweets = tweetService.getAllTweetsByCreatedByOrderByDateCreatedDesc(createdBy);
        if (tweets.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/feed", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<List<Tweet>> getTweetsByFollowed(@PathVariable("userId") UUID id) {
        List<Connection> connections = connectionService.getAllByFollower(id);
        List<Tweet> feedTweets = new ArrayList<>();

        for (Connection connection : connections) {
            UUID connectionId = connection.getFollowed();
            List<Tweet> connectionTweets = tweetService.getAllTweetsByCreatedByOrderByDateCreatedDesc(connectionId);

            if (!ListUtils.isEmpty(connectionTweets)) {
                feedTweets.add(connectionTweets.get(0));
            }
        }

        return new ResponseEntity<>(feedTweets, HttpStatus.OK);
    }
}
