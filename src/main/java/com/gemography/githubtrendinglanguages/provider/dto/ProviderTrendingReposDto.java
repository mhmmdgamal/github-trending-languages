package com.gemography.githubtrendinglanguages.provider.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderTrendingReposDto {
    @JsonProperty("items")
    List<ProviderTrendingRepoDto> providerTrendingRepos;
}
