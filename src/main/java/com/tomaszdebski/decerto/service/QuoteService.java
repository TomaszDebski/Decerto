package com.tomaszdebski.decerto.service;

import com.tomaszdebski.decerto.dto.QuoteDto;

import java.util.List;

public interface QuoteService {
    List<QuoteDto> getAllQuote(Integer pageNo, Integer pageSize);

    QuoteDto addQuote(QuoteDto newQuote);

    QuoteDto getQuote(Long id);

    QuoteDto updateQuote(QuoteDto newQuote, Long id);

    void deleteQuote(Long id);

}
