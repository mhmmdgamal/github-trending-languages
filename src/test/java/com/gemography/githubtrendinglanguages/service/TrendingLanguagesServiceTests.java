package com.gemography.githubtrendinglanguages.service;

import com.gemography.githubtrendinglanguages.dto.TrendingLanguagesDto;
import com.gemography.githubtrendinglanguages.provider.consumer.TrendingReposConsumer;
import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingRepoDto;
import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingReposDto;
import com.gemography.githubtrendinglanguages.provider.exception.ProviderGeneralException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class TrendingLanguagesServiceTests {
    @Mock
    private TrendingReposConsumer trendingReposConsumer;

    @InjectMocks
    private TrendingLanguagesService trendingLanguagesService;

    @Test
    public void fetchTrendingLanguages_whenSucceed_thenReturnProviderTrendingReposDto() {
        fetchTrendingLanguages_whenSucceed_thenReturnProviderTrendingReposDto_Mocks();

        TrendingLanguagesDto trendingLanguages = trendingLanguagesService.fetchTrendingLanguages();

        assertNotNull(trendingLanguages);
        assertEquals(OK.value(), trendingLanguages.getCode());
    }

    private void fetchTrendingLanguages_whenSucceed_thenReturnProviderTrendingReposDto_Mocks() {
        when(trendingReposConsumer.fetchTrendingRepos())
                .thenReturn(initProviderTrendingReposDto());
    }

    private ProviderTrendingReposDto initProviderTrendingReposDto() {
        ProviderTrendingReposDto providerTrendingReposDto = new ProviderTrendingReposDto();
        providerTrendingReposDto.setProviderTrendingRepos(Arrays.asList(buildProviderTrendingRepoDto()));

        return providerTrendingReposDto;
    }

    private ProviderTrendingRepoDto buildProviderTrendingRepoDto() {
        ProviderTrendingRepoDto providerTrendingRepoDto = new ProviderTrendingRepoDto();
        providerTrendingRepoDto.setId(1L);
        providerTrendingRepoDto.setName("mohamed");
        providerTrendingRepoDto.setLanguage("Java");
        providerTrendingRepoDto.setHtmlUrl("url");
        return providerTrendingRepoDto;
    }

    @Test
    public void fetchTrendingLanguages_whenFailure_thenThrowGeneralProviderException() {
        fetchTrendingLanguages_whenFailure_thenThrowGeneralProviderException_Mocks();

        assertThrows(ProviderGeneralException.class, () -> trendingLanguagesService.fetchTrendingLanguages());
    }

    private void fetchTrendingLanguages_whenFailure_thenThrowGeneralProviderException_Mocks() {
        when(trendingReposConsumer.fetchTrendingRepos())
                .thenThrow(new ProviderGeneralException("Error while fetching repos from github"));
    }
}
