package com.gemography.githubtrendinglanguages.controller;

import com.gemography.githubtrendinglanguages.dto.TrendingLanguagesDto;
import com.gemography.githubtrendinglanguages.service.TrendingLanguagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/github")
@RequiredArgsConstructor
public class TrendingLanguagesController {

    private final TrendingLanguagesService trendingLanguagesService;

    @GetMapping("/trending/languages")
    public ResponseEntity<TrendingLanguagesDto> fetchTrendingLanguages() {
        return ResponseEntity.ok(trendingLanguages());
    }

    private TrendingLanguagesDto trendingLanguages() {
        log.info("Start fetch github trending languages");
        return trendingLanguagesService.fetchTrendingLanguages();
    }
}
