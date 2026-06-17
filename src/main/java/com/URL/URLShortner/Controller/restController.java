package com.URL.URLShortner.Controller;

import com.URL.URLShortner.DTO.UrlExpiration;
import com.URL.URLShortner.DTO.UrlRequest;
import com.URL.URLShortner.DTO.UrlResponse;
import com.URL.URLShortner.Service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    public UrlExpiration OrignalUrl(@PathVariable String shortCode){

        String originalUrl = urlService.findOrignalUrl(shortCode);
        UrlExpiration urlExpiration = new UrlExpiration();
        if(originalUrl.equals("Url is expired")){
            urlExpiration.setMessage("Url is expired");
            return urlExpiration;
        }
        urlExpiration.setMessage(originalUrl);
        return urlExpiration;
    }

    @GetMapping("/redirect/{shortCode}")
    public UrlExpiration redirectUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {

        String originalUrl =
                urlService.findOrignalUrl(shortCode);

        UrlExpiration urlExpiration = new UrlExpiration();
        if(originalUrl.equals("Url is expired")){
            urlExpiration.setMessage("Url is expired");
            return urlExpiration;
        }
        response.sendRedirect(originalUrl);
        urlExpiration.setMessage("Url is Working");
        return urlExpiration;
    }

    //To find how much time a Url clicked
    @GetMapping("/analytics/{shortCode}")
    public UrlResponse analytics(@PathVariable String shortCode){
        UrlResponse urlResponse= new UrlResponse();
        Long numberOfClicks= urlService.getNumberOfClicks(shortCode);
        urlResponse.setClicks(numberOfClicks);

        return urlResponse;
    }

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/redis")
    public void redisRest(){
         redisTemplate.opsForValue().set("email","gmail@email.com");
        Object salary= redisTemplate.opsForValue().get("salary");
        int a= 1;
    }
}
