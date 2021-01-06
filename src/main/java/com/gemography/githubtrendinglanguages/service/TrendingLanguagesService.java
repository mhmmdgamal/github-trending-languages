package com.gemography.githubtrendinglanguages.service;

import com.gemography.githubtrendinglanguages.dto.LanguageDto;
import com.gemography.githubtrendinglanguages.dto.RepositoryDto;
import com.gemography.githubtrendinglanguages.dto.TrendingLanguagesDto;
import com.gemography.githubtrendinglanguages.provider.consumer.TrendingReposConsumer;
import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingRepoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrendingLanguagesService {
    private final TrendingReposConsumer trendingReposConsumer;

    public TrendingLanguagesDto fetchTrendingLanguages() {
        return new TrendingLanguagesDto(200, getLanguages(getGroupedLanguages()));
    }

    private Map<String, List<ProviderTrendingRepoDto>> getGroupedLanguages() {
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
        return providerTrendingRepo -> providerTrendingRepo.getLanguage();
    }

    private List<LanguageDto> getLanguages(Map<String, List<ProviderTrendingRepoDto>> groupedLanguages) {
        return groupedLanguages.keySet().stream()
                .map(mapToLanguageDto(groupedLanguages))
                .collect(Collectors.toList());
    }

    private Function<String, LanguageDto> mapToLanguageDto(Map<String, List<ProviderTrendingRepoDto>> groupedLanguages) {
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
