package com.gemography.githubtrendinglanguages.provider.consumer;

import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingRepoDto;
import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingReposDto;
import com.gemography.githubtrendinglanguages.provider.exception.ProviderGeneralException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrendingReposConsumerTests {
    @Mock
    private Environment environment;
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TrendingReposConsumer trendingReposConsumer;

    @Test
    public void fetchTrendingRepos_whenSuccess_thenAssertNotNull() {
        fetchTrendingRepos_whenSuccess_thenAssertNotNull_Mocks();

        ProviderTrendingReposDto providerTrendingReposDto = trendingReposConsumer.fetchTrendingRepos();

        assertNotNull(providerTrendingReposDto);
    }

    private void fetchTrendingRepos_whenSuccess_thenAssertNotNull_Mocks() {
        when(environment.getProperty("github.query.repos.url"))
                .thenReturn("QUERY_URL");
        when(environment.getProperty("github.query.repos.lastDays"))
                .thenReturn("30");
        when(restTemplate.getForObject(anyString(), any()))
                .thenReturn(initProviderTrendingRepos());
    }

    private ProviderTrendingReposDto initProviderTrendingRepos() {
        ProviderTrendingReposDto providerTrendingReposDto = new ProviderTrendingReposDto();

        ProviderTrendingRepoDto providerTrendingRepoDto = new ProviderTrendingRepoDto();
        providerTrendingRepoDto.setId(1L);
        providerTrendingRepoDto.setName("mohamed");
        providerTrendingRepoDto.setLanguage("Java");

        providerTrendingReposDto.setProviderTrendingRepos(Arrays.asList(providerTrendingRepoDto));

        return providerTrendingReposDto;
    }

    @Test
    public void fetchTrendingRepos_whenExceptionOccurred_thenThrowProviderGeneralException() {
        fetchTrendingRepos_whenExceptionOccurred_thenThrowProviderGeneralException_Mocks();

        assertThrows(ProviderGeneralException.class, () -> trendingReposConsumer.fetchTrendingRepos());
    }

    private void fetchTrendingRepos_whenExceptionOccurred_thenThrowProviderGeneralException_Mocks() {
        when(environment.getProperty("github.query.repos.url"))
                .thenReturn("QUERY_URL");
        when(environment.getProperty("github.query.repos.lastDays"))
                .thenReturn("30");
        when(restTemplate.getForObject(anyString(), any()))
                .thenThrow(RuntimeException.class);
    }

}
