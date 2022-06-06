package com.nazar.backendspringboot.dao.news;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String url;
    private String title;
    private String author;
}
