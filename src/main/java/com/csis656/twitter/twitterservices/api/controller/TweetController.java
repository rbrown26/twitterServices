package com.csis656.twitter.twitterservices.api.controller;

import com.csis656.twitter.twitterservices.api.mapping.request.TweetRequestObject;
import com.csis656.twitter.twitterservices.api.mapping.response.TweetResponse;
import com.csis656.twitter.twitterservices.api.mapping.response.TwitterUserResponse;
import com.csis656.twitter.twitterservices.model.Tweet;
import com.csis656.twitter.twitterservices.service.TweetService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "tweet")
public class TweetController {
    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
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

    @RequestMapping(value = "/tweet", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getFollowersByCreatedBy(@RequestBody TweetRequestObject tweetRequestObject) {
        tweetService.getFirstTweetByCreatedBy(tweetRequestObject.getCreatedBy());

        return ResponseEntity.ok().build();
    }
}
