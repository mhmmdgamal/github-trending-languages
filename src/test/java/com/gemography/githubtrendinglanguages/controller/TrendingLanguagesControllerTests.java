package com.gemography.githubtrendinglanguages.controller;

import com.gemography.githubtrendinglanguages.dto.TrendingLanguagesDto;
import com.gemography.githubtrendinglanguages.service.TrendingLanguagesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class TrendingLanguagesControllerTests {
    @Mock
    private TrendingLanguagesService trendingLanguagesService;

    @InjectMocks
    private TrendingLanguagesController trendingLanguagesController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(trendingLanguagesController).build();
    }

    @Test
    public void fetchTrendingLanguages_whenSuccess_thenReturnCode200() throws Exception {
        fetchTrendingLanguages_whenSuccess_thenReturnCode200_Mocks();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/repositories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(OK.value()));
    }

    private void fetchTrendingLanguages_whenSuccess_thenReturnCode200_Mocks() {
        when(trendingLanguagesService.fetchTrendingLanguages())
                .thenReturn(new TrendingLanguagesDto(OK.value(), null));
    }

    @Test
    public void fetchTrendingLanguages_whenThrowException_thenReturnCode503() throws Exception {
        fetchTrendingLanguages_whenThrowException_thenReturnCode503_Mocks();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/repositories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(SERVICE_UNAVAILABLE.value()));
    }

    private void fetchTrendingLanguages_whenThrowException_thenReturnCode503_Mocks() {
        when(trendingLanguagesService.fetchTrendingLanguages())
                .thenReturn(new TrendingLanguagesDto(SERVICE_UNAVAILABLE.value(), null));
    }
}
