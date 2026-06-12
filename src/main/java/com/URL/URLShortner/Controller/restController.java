package com.URL.URLShortner.Controller;

import com.URL.URLShortner.DTO.UrlRequest;
import com.URL.URLShortner.Entity.UrlMapping;
import com.URL.URLShortner.Service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
public class restController {
    @Autowired
    UrlService urlService;


    @PostMapping("/shorten")
    public String sortUrl(@RequestBody UrlRequest request){
        String str = urlService.createSortUrl(request.getUrl());
        String finalString = "http://localhost:8080/"+""+str;
        return finalString;
    }
    @GetMapping("/{shortCode}")
    public String OrignalUrl(@PathVariable String shortCode){
        return urlService.findOrignalUrl(shortCode);
    }

    @GetMapping("/redirect/{shortCode}")
    public void redirectUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {

        String originalUrl =
                urlService.findOrignalUrl(shortCode);

        response.sendRedirect(originalUrl);
    }
}
