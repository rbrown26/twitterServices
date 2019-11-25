package com.csis656.twitter.twitterservices.api.controller;

import com.csis656.twitter.twitterservices.api.mapping.request.ConnectionRequestObject;
import com.csis656.twitter.twitterservices.model.Connection;
import com.csis656.twitter.twitterservices.model.TwitterUser;
import com.csis656.twitter.twitterservices.service.ConnectionService;
import com.csis656.twitter.twitterservices.service.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "connection")
public class ConnectionController {
    private ConnectionService connectionService;
    private TwitterUserService twitterUserService;

    @Autowired
    public ConnectionController(ConnectionService connectionService, TwitterUserService twitterUserService) {
        this.connectionService = connectionService;
        this.twitterUserService = twitterUserService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity create(@RequestBody ConnectionRequestObject connectionRequestObject) {
        connectionService.create(connectionRequestObject);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @CrossOrigin
    public ResponseEntity delete(@RequestBody ConnectionRequestObject connectionRequestObject) {
        connectionService.deleteByFollowedAndFollower(connectionRequestObject.getFollowed(), connectionRequestObject.getFollower());

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{userId}/following", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<List<TwitterUser>> getFollows(@PathVariable("userId") UUID id) {
        List<Connection> connections = connectionService.getAllByFollower(id);
        List<TwitterUser> twitterUsers = new ArrayList<>();
        for (Connection connection : connections) {
            TwitterUser twitterUser = twitterUserService.getUserById(connection.getFollowed());
            twitterUsers.add(twitterUser);
        }

        return new ResponseEntity<>(twitterUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/followers/count", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getCountFollowsById(@PathVariable("userId") UUID id) {
        int followingCount = connectionService.getFollowedCountForUserId(id);

        return new ResponseEntity(followingCount, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/followers", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<List<TwitterUser>> getFollowers(@PathVariable("userId") UUID id) {
        List<Connection> connections = connectionService.getAllByFollowed(id);
        List<TwitterUser> twitterUsers = new ArrayList<>();
        for (Connection connection : connections) {
            TwitterUser twitterUser = twitterUserService.getUserById(connection.getFollower());
            twitterUsers.add(twitterUser);
        }

        return new ResponseEntity<>(twitterUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/following/count", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getCountFollowersById(@PathVariable("userId") UUID id) {
        int followersCount = connectionService.getFollowingCountByUserId(id);

        return new ResponseEntity(followersCount, HttpStatus.OK);
    }
}
