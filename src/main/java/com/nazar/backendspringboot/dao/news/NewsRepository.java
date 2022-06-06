package com.nazar.backendspringboot.dao.news;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    boolean existsByUrl(String url);
}
