package com.url.shortener.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "urls")
public class UrlEntity {
	@Id
	private String id;
	private String shortCode;
	private String originalUrl;
    private LocalDateTime createdAt;
}
