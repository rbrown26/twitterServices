package com.csis656.twitter.twitterservices.api.controller;

import com.csis656.twitter.twitterservices.api.mapping.request.ConnectionRequestObject;
import com.csis656.twitter.twitterservices.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "conn")
public class ConnectionController {
    private ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @RequestMapping(value = "/connection", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity connect(@RequestBody ConnectionRequestObject connectionRequestObject) {
        connectionService.create(connectionRequestObject);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/connection/follows", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getFollows(@RequestBody ConnectionRequestObject connectionRequestObject) {
        connectionService.getFollowedCountForUserId(connectionRequestObject.getId());

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/connection/followers", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity getFollowers(@RequestBody ConnectionRequestObject connectionRequestObject) {
        connectionService.getFollowersCountByUserId(connectionRequestObject.getId());

        return ResponseEntity.ok().build();
    }
}
