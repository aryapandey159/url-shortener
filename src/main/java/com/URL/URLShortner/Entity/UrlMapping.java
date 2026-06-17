package com.URL.URLShortner.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class UrlMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;

    private String shortCode;

    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Long clickCount = 0L;

    private LocalDateTime expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public UrlMapping() {
    }
    public UrlMapping(UrlMapping urlMapping){
        this.id = urlMapping.getId();
        this.originalUrl = urlMapping.getOriginalUrl();
        this.shortCode = urlMapping.getShortCode();
        this.createdAt = urlMapping.getCreatedAt();
        this.clickCount = urlMapping.getClickCount();
        this.expiryDate = urlMapping.getExpiryDate();
    }
}
