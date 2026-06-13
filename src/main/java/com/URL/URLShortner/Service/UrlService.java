package com.URL.URLShortner.Service;

import com.URL.URLShortner.Entity.UrlMapping;
import com.URL.URLShortner.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        LocalDateTime currentDateTime = LocalDateTime.now();
        urlMapping.setCreatedAt(currentDateTime);

        LocalDateTime expireDateTime = currentDateTime.plusDays(2);
        urlMapping.setExpiryDate(expireDateTime);

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
        Long count=0L;
        if(urlMapping.getClickCount() != null){
            count = urlMapping.getClickCount()+1;
        }
        urlMapping.setClickCount(count);
        urlRepository.save(urlMapping);

        //Adding this to know weather our url is expired or not
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiredDateTime= urlMapping.getExpiryDate();
        if (currentDateTime.isAfter(expiredDateTime)){
            return "Url is expired";
        }

        if(urlMapping != null){
            return urlMapping.getOriginalUrl();
        }else{
            throw new IllegalArgumentException("Short code not found");
        }
    }

    //To give the number of counts
    public Long getNumberOfClicks(String sortUrl){
        UrlMapping urlMapping = urlRepository.findByShortCode(sortUrl);
        Long count=0L;
        if(urlMapping.getClickCount() != null){
            count = urlMapping.getClickCount()+1;
        }
        return count;
    }
}
