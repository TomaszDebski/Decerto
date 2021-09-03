package com.tomaszdebski.decerto.controller;

import com.tomaszdebski.decerto.dto.QuoteDto;
import com.tomaszdebski.decerto.exception.QuoteNotFoundException;
import com.tomaszdebski.decerto.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.List;

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
    private QuoteService quoteService;

    @Test
    public void getAllQuotes() throws Exception
    {
        QuoteDto quoteDto = QuoteDto.builder()
                .withId(1L)
                .withAuthorFirstName("firstName1")
                .withAuthorLastName("LastName1")
                .withContent("content1").build();
        QuoteDto quoteDto2 = QuoteDto.builder()
                .withId(2L)
                .withAuthorFirstName("firstName2")
                .withAuthorLastName("LastName2")
                .withContent("content2").build();
        List<QuoteDto> quotes = Arrays.asList(quoteDto, quoteDto2);
        when(quoteService.getAllQuote(1,10)).thenReturn(quotes);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("pageNo", "1");
        requestParams.add("pageSize", "10");

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/quotes")
                        .params(requestParams)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].authorFirstName").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].authorLastName").isNotEmpty());

    }

    @Test
    public void checkOneQuote() throws Exception{
        QuoteDto quoteDto = QuoteDto.builder()
                .withId(1L)
                .withAuthorFirstName("authorFirstName")
                .withAuthorLastName("authorLastName")
                .withContent("content").build();
        when(quoteService.getQuote(1L)).thenReturn(quoteDto);

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
        when(quoteService.getQuote(1L)).thenThrow(new QuoteNotFoundException(1L));

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/quotes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof QuoteNotFoundException));
    }
}
