package com.gemography.githubtrendinglanguages.service;

import com.gemography.githubtrendinglanguages.provider.consumer.TrendingReposConsumer;
import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingReposDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrendingLanguagesService {
    private final TrendingReposConsumer trendingReposConsumer;

    public ProviderTrendingReposDto fetchTrendingLanguages() {

        return trendingReposConsumer.fetchTrendingRepos();
    }
}
