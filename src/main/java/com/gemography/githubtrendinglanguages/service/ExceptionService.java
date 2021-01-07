package com.gemography.githubtrendinglanguages.service;

import com.gemography.githubtrendinglanguages.dto.TrendingLanguagesDto;
import com.gemography.githubtrendinglanguages.provider.exception.ProviderGeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Slf4j
@RestControllerAdvice
public class ExceptionService {
    @ExceptionHandler(value = ProviderGeneralException.class)
    public ResponseEntity<TrendingLanguagesDto> handleProviderGeneralException(ProviderGeneralException ex) {
        log.error("Start handling ProviderGeneralException");
        return ResponseEntity
                .status(SERVICE_UNAVAILABLE)
                .body(new TrendingLanguagesDto(SERVICE_UNAVAILABLE.value(), Collections.emptyList()));
    }
}
