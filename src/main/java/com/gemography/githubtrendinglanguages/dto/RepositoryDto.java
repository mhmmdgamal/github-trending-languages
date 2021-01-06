package com.gemography.githubtrendinglanguages.dto;

import com.gemography.githubtrendinglanguages.provider.dto.ProviderTrendingRepoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RepositoryDto {
    private String name;
    private String url;

    public RepositoryDto(ProviderTrendingRepoDto providerTrendingRepoDto) {
        name = providerTrendingRepoDto.getName();
        url = providerTrendingRepoDto.getHtmlUrl();
    }
}
