package com.nazar.backendspringboot.service;

import com.nazar.backendspringboot.dao.news.NewsEntity;
import com.nazar.backendspringboot.dao.news.NewsRepository;
import com.nazar.backendspringboot.domain.News;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NewsHandler {

    private final NewsRepository repository;

    public void handle(News news) {
        if (repository.existsByUrl(news.url())) return;

        log.info("Saving {} news", news.url());

        NewsEntity entity = new NewsEntity();
        entity.setTitle(news.title());
        entity.setUrl(news.url());
        entity.setAuthor(news.author());

        repository.save(entity);
    }
}
