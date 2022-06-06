package com.nazar.backendspringboot.service;

import com.nazar.backendspringboot.dao.news.NewsEntity;
import com.nazar.backendspringboot.dao.news.NewsRepository;
import com.nazar.backendspringboot.dto.PageableResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.PageRequest.of;

@Service
@AllArgsConstructor
public class NewsWebService {

    private final NewsRepository repository;

    public PageableResponse<NewsEntity> get(int page, int size) {
        PageableResponse<NewsEntity> response = new PageableResponse<>();
        Page<NewsEntity> news = repository.findAll(of(page, size));
        response.setItems(news.getContent());
        response.setTotalPages(news.getTotalPages());
        response.setTotalItems(news.getTotalElements());
        return response;
    }
}
