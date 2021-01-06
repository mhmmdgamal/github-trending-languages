package com.gemography.githubtrendinglanguages.controller;

import com.gemography.githubtrendinglanguages.github.consumer.TrendingReposConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TrendingLanguagesController {

    private final TrendingReposConsumer trendingReposConsumer;

    @GetMapping("/repositories")
    public ResponseEntity<String> fetchTrendingRepos() {
        return trendingReposConsumer.fetchTrendingRepos();
    }
}
