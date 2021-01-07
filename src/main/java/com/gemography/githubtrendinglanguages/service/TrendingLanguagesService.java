package com.gemography.githubtrendinglanguages.service;

import com.gemography.githubtrendinglanguages.dto.LanguageDto;
import com.gemography.githubtrendinglanguages.dto.RepositoryDto;
import com.gemography.githubtrendinglanguages.dto.TrendingLanguagesDto;
import com.gemography.githubtrendinglanguages.provider.consumer.TrendingReposConsumer;
import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingRepoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrendingLanguagesService {
    private final TrendingReposConsumer trendingReposConsumer;

    public TrendingLanguagesDto fetchTrendingLanguages() {
        return buildTrendingLanguagesDto();
    }

    private TrendingLanguagesDto buildTrendingLanguagesDto() {
        return new TrendingLanguagesDto(OK.value(), getLanguages(getGroupedLanguages()));
    }

    private Map<String, List<ProviderTrendingRepoDto>> getGroupedLanguages() {
        log.info("Start fetching github trending repos in the last 30 days");
        return trendingReposConsumer.fetchTrendingRepos()
                .getProviderTrendingRepos()
                .stream()
                .filter(filterNotFoundLanguages())
                .collect(Collectors.groupingBy(groupByLanguages()));
    }

    private Predicate<ProviderTrendingRepoDto> filterNotFoundLanguages() {
        return providerTrendingRepo ->
                providerTrendingRepo.getLanguage() != null && !providerTrendingRepo.getLanguage().isEmpty();
    }

    private Function<ProviderTrendingRepoDto, String> groupByLanguages() {
        return ProviderTrendingRepoDto::getLanguage;
    }

    private List<LanguageDto> getLanguages(Map<String, List<ProviderTrendingRepoDto>> groupedLanguages) {
        return groupedLanguages.keySet().stream()
                .map(mapGroupedLanguagesToLanguageDto(groupedLanguages))
                .collect(Collectors.toList());
    }

    private Function<String, LanguageDto> mapGroupedLanguagesToLanguageDto(Map<String, List<ProviderTrendingRepoDto>> groupedLanguages) {
        return language -> buildLanguageDto(groupedLanguages, language, getRepositories(groupedLanguages, language));
    }

    private List<RepositoryDto> getRepositories(Map<String, List<ProviderTrendingRepoDto>> groupedLanguages, String language) {
        return groupedLanguages.get(language)
                .stream()
                .map(RepositoryDto::new)
                .collect(Collectors.toList());
    }

    private LanguageDto buildLanguageDto(Map<String, List<ProviderTrendingRepoDto>> groupedLanguages, String language, List<RepositoryDto> repositories) {
        return new LanguageDto(language, groupedLanguages.get(language).size(), repositories);
    }
}
