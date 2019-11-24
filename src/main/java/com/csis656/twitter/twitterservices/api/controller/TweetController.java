package com.csis656.twitter.twitterservices.api.controller;

import com.csis656.twitter.twitterservices.api.mapping.request.TweetRequestObject;
import com.csis656.twitter.twitterservices.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity get(@RequestBody TweetRequestObject tweetRequestObject) {
        tweetService.getAllTweetsByUserId(tweetRequestObject.getCreatedBy());

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/tweet", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getFollowers(@RequestBody TweetRequestObject tweetRequestObject) {
        tweetService.getFirstTweetByUserId(tweetRequestObject.getCreatedBy());

        return ResponseEntity.ok().build();
    }
}
