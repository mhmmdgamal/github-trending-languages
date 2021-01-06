package com.gemography.githubtrendinglanguages.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LanguageDto {
    private String name;
    private Integer count;
    private List<RepositoryDto> repositories;
}
