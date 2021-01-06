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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrendingLanguagesService {
    private final TrendingReposConsumer trendingReposConsumer;

    public TrendingLanguagesDto fetchTrendingLanguages() {
        Map<String, List<ProviderTrendingRepoDto>> groupedLanguages =
                trendingReposConsumer.fetchTrendingRepos()
                        .getProviderTrendingRepos()
                        .stream()
                        .filter(providerTrendingRepo -> providerTrendingRepo.getLanguage() != null)
                        .collect(Collectors.groupingBy(providerTrendingRepo -> providerTrendingRepo.getLanguage()));

        List<LanguageDto> languageDtoList =
                groupedLanguages.keySet().stream()
                        .map(language -> {
                            List<RepositoryDto> repositories = groupedLanguages.get(language)
                                            .stream()
                                            .map(RepositoryDto::new)
                                            .collect(Collectors.toList());

                            LanguageDto languageDto = new LanguageDto();
                            languageDto.setCount(groupedLanguages.get(language).size());
                            languageDto.setName(language);
                            languageDto.setRepositories(repositories);
                            return languageDto;
                        })
                        .collect(Collectors.toList());

        return new TrendingLanguagesDto(200, languageDtoList);
    }
}
