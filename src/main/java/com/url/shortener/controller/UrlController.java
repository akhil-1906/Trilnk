package com.url.shortener.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortener.entity.UrlEntity;
import com.url.shortener.service.UrlService;

@RestController
@RequestMapping("/api")
public class UrlController {

	@Autowired
	private UrlService urlService;

	@PostMapping("/shorten")
	public String shortenUrl(@RequestBody String url) {
		return urlService.createShortUrl(url);
	}

	@GetMapping("/shorten/{shortCode}")
	public ResponseEntity<UrlEntity> redirect(@PathVariable String shortCode) {
		UrlEntity entity = urlService.getOriginalUrl(shortCode);
		if (entity == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entity);
	}
	
	@GetMapping("/shorten")
	public ResponseEntity<Optional<List<UrlEntity>>> fetchAll() {
		Optional<List<UrlEntity>> entity = urlService.fetchAll();
		if (entity == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entity);
	}

	@DeleteMapping("/shorten")
	public String deleteUrl(@RequestParam String url) {
		if (urlService.deleteUrl(url)) {
			return "Deleted Successfully";
		}
		return "Failed";
	}
}