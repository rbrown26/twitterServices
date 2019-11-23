package com.csis656.twitter.twitterservices.service;

import com.csis656.twitter.twitterservices.api.mapping.request.ConnectionRequestObject;
import com.csis656.twitter.twitterservices.model.Connection;
import com.csis656.twitter.twitterservices.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {

    private ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Connection create(ConnectionRequestObject connectionRequestObject) {
        Connection connection = Connection.create(connectionRequestObject.getFollowed(),
                connectionRequestObject.getFollower()
        );

        return connectionRepository.save(connection);
    }

    public int getFollowersCountByUserId(int id) {
        List<Connection> connectionList = connectionRepository.findFollowersById(id);

        return connectionList.size();
    }

    public int getFollowedCountForUserId(int id) {
        List<Connection> connectionList = connectionRepository.findFollowedById(id);

        return connectionList.size();
    }
}
