package com.gemography.githubtrendinglanguages.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TrendingLanguagesDto {
    private Integer code;
    List<LanguageDto> languages;
}
