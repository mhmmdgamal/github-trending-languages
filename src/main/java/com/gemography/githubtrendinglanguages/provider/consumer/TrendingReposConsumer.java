package com.gemography.githubtrendinglanguages.provider.consumer;

import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingReposDto;
import com.gemography.githubtrendinglanguages.provider.exception.ProviderGeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrendingReposConsumer {
    private final Environment environment;
    private final RestTemplate restTemplate;

    public ProviderTrendingReposDto fetchTrendingRepos() {
        try {
            ProviderTrendingReposDto providerTrendingReposDto = restTemplate.getForObject(prepareUrlWithLastDays(), ProviderTrendingReposDto.class);
            log.info("Success Fetching github trending repos");
            log.info("Provider Repos Response: {}", providerTrendingReposDto);
            return providerTrendingReposDto;
        } catch (Exception e) {
            log.error("Error while Fetching github trending repos", e);
            throw new ProviderGeneralException("Error while fetching repos from github");
        }
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
