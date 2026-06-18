package com.URL.URLShortner.DTO;

public class UrlExpiration {
    private String message;

    public UrlExpiration(){}

    public UrlExpiration(String tooManyRequests) {
        this.message = tooManyRequests;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
