package com.url.shortener.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.url.shortener.entity.UrlEntity;
import com.url.shortener.repository.UrlRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlService {
	private final Base62Generator base62;
	private final UrlRepository urlRepo;
	private final StringRedisTemplate redisTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public String createShortUrl(String originalUrl) {
		Optional<UrlEntity> existing = urlRepo.findByOriginalUrl(originalUrl);
		if (existing.isPresent())
			return existing.get().getShortCode();

		String shortCode = base62.generate(7);
		UrlEntity entity = new UrlEntity();
		entity.setShortCode(shortCode);
		entity.setOriginalUrl(originalUrl);
		entity.setCreatedAt(LocalDateTime.now());
		urlRepo.save(entity);
		try {
			String json = objectMapper.writeValueAsString(entity);
			redisTemplate.opsForValue().set("url:" + shortCode, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shortCode;
	}

	public UrlEntity getOriginalUrl(String shortCode) {
		try {
			String cachedJson = redisTemplate.opsForValue().get("url:" + shortCode);
			if (cachedJson != null) {
				return objectMapper.readValue(cachedJson, UrlEntity.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		UrlEntity entity = urlRepo.findByShortCode(shortCode).orElseThrow(() -> new RuntimeException("Not found"));

		try {
			String json = objectMapper.writeValueAsString(entity);
			redisTemplate.opsForValue().set("url:" + shortCode, json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return entity;
	}

	public boolean deleteUrl(String shortCode) {

		Optional<UrlEntity> entity = urlRepo.findByShortCode(shortCode);
		if (entity.isEmpty()) {
			return false;
		}
		urlRepo.deleteByShortCode(shortCode);
		redisTemplate.delete("url:" + shortCode);
		redisTemplate.opsForHash().delete("url:" + shortCode, shortCode);
		return true;
	}

	public Optional<List<UrlEntity>> fetchAll() {
		Optional<List<UrlEntity>> entity = Optional.ofNullable(urlRepo.findAll());
		return entity;
	}
}
