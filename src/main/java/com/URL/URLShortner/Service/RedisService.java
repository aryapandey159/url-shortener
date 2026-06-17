package com.URL.URLShortner.Service;

import com.URL.URLShortner.Entity.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    public void Set(String sorturl,String longurl){
//        redisTemplate.opsForValue().set(sorturl,longurl);
//    }

//    public UrlMapping Get(String url) {
//         Object o= redisTemplate.opsForValue().get(url);
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(o.toString(),UrlMapping.class);
//    }

    @Autowired
    private ObjectMapper objectMapper;

    public void Set(String key, UrlMapping urlMapping) {

        redisTemplate.opsForValue()
                .set(key, objectMapper.writeValueAsString(urlMapping));
    }

    public UrlMapping Get(String key) {

        String json = redisTemplate.opsForValue().get(key);

        if (json == null) {
            return null;
        }

        return objectMapper.readValue(json, UrlMapping.class);
    }

}
