package com.tomaszdebski.decerto.controller;

import com.tomaszdebski.decerto.entity.Quote;
import com.tomaszdebski.decerto.exception.QuoteNotFoundException;
import com.tomaszdebski.decerto.repository.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteRepository quoteRepository;

    @Test
    public void getAllQ() throws Exception
    {
        Quote quote = new Quote(1L, "authorFirstName", "authorLastName" , "content");
        Quote quote2 = new Quote(2L, "authorFirstName2", "authorLastName2" , "content2");
        List<Quote> quotes = Arrays.asList(quote, quote2);
        when(quoteRepository.findAll()).thenReturn(quotes);

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/quotes")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].authorFirstName").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].authorLastName").isNotEmpty());

    }

    @Test
    public void checkOneQuote() throws Exception{
        Quote quote = new Quote(1L, "authorFirstName", "authorLastName" , "content");
        when(quoteRepository.findById(1L)).thenReturn(Optional.of(quote));

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/quotes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.authorFirstName").value("authorFirstName"))
                .andExpect(jsonPath("$.authorLastName").value("authorLastName"))
                .andExpect(jsonPath("$.content").value("content"));
    }

    @Test
    public void checkOneQuoteThrowException() throws Exception{
        when(quoteRepository.findById(1L)).thenThrow(new QuoteNotFoundException(1L));

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/quotes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof QuoteNotFoundException));
    }
}
