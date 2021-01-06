package com.gemography.githubtrendinglanguages.github.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TrendingReposConsumer {
    private final Environment environment;
    private final RestTemplate restTemplate;

    public ResponseEntity<String> fetchTrendingRepos() {
        return restTemplate.getForEntity(prepareUrlWithLastDays(), String.class);
    }

    private String prepareUrlWithLastDays() {
        return getQueryUrl().replace("{0}", getDateOfLast(getQueryLastDays()));
    }

    private String getQueryUrl() {
        return environment.getProperty("github.query.repos.url");
    }

    private Long getQueryLastDays() {
        return Long.parseLong(environment.getProperty("github.query.repos.lastDays"));
    }

    private String getDateOfLast(Long lastDays) {
        return LocalDateTime.now().minusDays(lastDays).toString();
    }
}
