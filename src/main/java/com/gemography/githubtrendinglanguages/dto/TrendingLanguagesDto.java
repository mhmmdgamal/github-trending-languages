package com.gemography.githubtrendinglanguages.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TrendingLanguagesDto {
    private Integer code;
    List<LanguageDto> languages;
}
