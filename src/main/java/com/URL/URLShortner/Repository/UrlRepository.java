package com.URL.URLShortner.Repository;

import com.URL.URLShortner.Entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping,Long> {
    UrlMapping findByShortCode(String shortCode);

}
