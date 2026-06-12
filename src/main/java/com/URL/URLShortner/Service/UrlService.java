package com.URL.URLShortner.Service;

import com.URL.URLShortner.Entity.UrlMapping;
import com.URL.URLShortner.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    public String createSortUrl(String orignalUrl){
        String randomUrl = generateSortCode();

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(orignalUrl);
        urlMapping.setShortCode(randomUrl);

        urlRepository.save(urlMapping);
        return randomUrl;
    }

    public String generateSortCode(){
        return UUID.randomUUID()
                .toString()
                .substring(0,6);
    }
    public String findOrignalUrl(String sortUrl){
        UrlMapping urlMapping = urlRepository.findByShortCode(sortUrl);
        if(urlMapping != null){
            return urlMapping.getOriginalUrl();
        }else{
            throw new IllegalArgumentException("Short code not found");
        }
    }
}
