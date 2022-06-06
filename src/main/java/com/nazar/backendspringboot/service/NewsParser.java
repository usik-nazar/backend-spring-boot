package com.nazar.backendspringboot.service;

import com.nazar.backendspringboot.domain.News;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class NewsParser {

    public List<News> parse(Document document) {
        return document.select(".article_list")
                .stream()
                .map(a -> {
                    String title = a.select("h3 a").text();
                    String url = a.select("h3 a").attr("abs:href");
                    String author = a.select(".article_author").text();
                    return new News(title, url, author);
                })
                .collect(toList());
    }
}
