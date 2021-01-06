package com.gemography.githubtrendinglanguages.controller;

import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingReposDto;
import com.gemography.githubtrendinglanguages.service.TrendingLanguagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TrendingLanguagesController {

    private final TrendingLanguagesService trendingLanguagesService;

    @GetMapping("/repositories")
    public ResponseEntity<ProviderTrendingReposDto> fetchTrendingLanguages() {
        return ResponseEntity.ok(trendingLanguagesService.fetchTrendingLanguages());
    }
}
