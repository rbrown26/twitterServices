package com.csis656.twitter.twitterservices.api.controller;

import com.csis656.twitter.twitterservices.model.TwitterUser;
import com.csis656.twitter.twitterservices.service.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "users")
public class TwitterUserController {
    private TwitterUserService twitterUserService;

    @Autowired
    public TwitterUserController(TwitterUserService connectionService) {
        this.twitterUserService = connectionService;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<List<TwitterUser>> findAll(@PathVariable("userId") UUID id) {
        List<TwitterUser> twitterUsers = twitterUserService.findAll();
        if (twitterUsers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        for (TwitterUser twitterUser : twitterUsers) {
            if (twitterUser.getId().equals(id)) {
                twitterUsers.remove(twitterUser);
                break;
            }
        }

        return new ResponseEntity<>(twitterUsers, HttpStatus.OK);
    }
}
