package com.gemography.githubtrendinglanguages.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {
    private String name;
    private Integer count;
    private List<RepositoryDto> repositories;
}
