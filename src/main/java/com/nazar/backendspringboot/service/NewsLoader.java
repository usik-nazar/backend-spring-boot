package com.nazar.backendspringboot.service;

import com.nazar.backendspringboot.domain.News;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
@AllArgsConstructor
public class NewsLoader {

    private final static String url = "https://www.pravda.com.ua/rus/tags/meditsina/";

    private final NewsParser parser;
    private final NewsHandler handler;

    public void loadLast() {
        Document document = load();
        if (isNull(document)) return;

        List<News> news = parser.parse(document);
        news.forEach(handler::handle);
    }

    private Document load() {
        try {
            return Jsoup.connect(url)
                    .ignoreContentType(true)
                    .get();
        } catch (IOException e) {
            log.error("Details", e);
            return null;
        }
    }
}
