package com.gemography.githubtrendinglanguages.dto;

import lombok.*;

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
