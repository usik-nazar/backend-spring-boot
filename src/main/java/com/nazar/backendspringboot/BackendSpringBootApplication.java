package com.nazar.backendspringboot;

import com.nazar.backendspringboot.service.NewsLoader;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableScheduling
@AllArgsConstructor
@SpringBootApplication
public class BackendSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendSpringBootApplication.class, args);
    }

    private final NewsLoader newsLoader;

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void load() {
        newsLoader.loadLast();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
