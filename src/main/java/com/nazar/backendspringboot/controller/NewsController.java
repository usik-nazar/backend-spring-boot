package com.nazar.backendspringboot.controller;

import com.nazar.backendspringboot.dao.news.NewsEntity;
import com.nazar.backendspringboot.dto.PageableResponse;
import com.nazar.backendspringboot.service.NewsWebService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("news")
public class NewsController {

    private final NewsWebService service;

    @GetMapping
    public PageableResponse<NewsEntity> get(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return service.get(page, size);
    }
}
