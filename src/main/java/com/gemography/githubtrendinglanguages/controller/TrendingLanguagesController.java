package com.gemography.githubtrendinglanguages.controller;

import com.gemography.githubtrendinglanguages.provider.consumer.TrendingReposConsumer;
import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingRepoDto;
import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingReposDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TrendingLanguagesController {

    private final TrendingReposConsumer trendingReposConsumer;

    @GetMapping("/repositories")
    public ResponseEntity<ProviderTrendingReposDto> fetchTrendingRepos() {
        return trendingReposConsumer.fetchTrendingRepos();
    }
}
