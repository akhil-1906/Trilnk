package com.url.shortener.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.url.shortener.entity.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
    Optional<UrlEntity> findByShortCode(String shortCode);
    Optional<UrlEntity> findByOriginalUrl(String longUrl);
    void deleteByShortCode(String shortCode);
}