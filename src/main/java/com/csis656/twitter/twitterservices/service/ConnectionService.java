package com.csis656.twitter.twitterservices.service;

import com.csis656.twitter.twitterservices.api.mapping.request.ConnectionRequestObject;
import com.csis656.twitter.twitterservices.model.Connection;
import com.csis656.twitter.twitterservices.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public void deleteByFollowedAndFollower(UUID followed, UUID follower) {
        connectionRepository.deleteByFollowedAndFollower(followed, follower);
    }

    public List<Connection> getAllByFollower(UUID id) {
        return connectionRepository.getAllByFollower(id);
    }

    public List<Connection> getAllByFollowed(UUID id) {
        return connectionRepository.getAllByFollowed(id);
    }

    public int getFollowingCountByUserId(UUID id) {
        List<Connection> connectionList = connectionRepository.getAllByFollower(id);

        return connectionList.size();
    }

    public int getFollowedCountForUserId(UUID id) {
        List<Connection> connectionList = connectionRepository.getAllByFollowed(id);

        return connectionList.size();
    }
}
