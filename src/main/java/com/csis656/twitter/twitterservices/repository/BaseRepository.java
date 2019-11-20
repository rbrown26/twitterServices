package com.csis656.twitter.twitterservices.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
interface BaseRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);

    void deleteAll();
}
