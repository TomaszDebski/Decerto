package com.tomaszdebski.decerto.service;

import com.tomaszdebski.decerto.dto.QuoteDto;
import com.tomaszdebski.decerto.entity.Quote;
import com.tomaszdebski.decerto.exception.QuoteNotFoundException;
import com.tomaszdebski.decerto.repository.QuoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {

    QuoteRepository quoteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<QuoteDto> getAllQuote(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Quote> pagedResult = quoteRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent().stream().
                    map(this::convertToDto)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<QuoteDto>();
        }
    }

    public QuoteDto addQuote(QuoteDto newQuote) {
        Quote save = quoteRepository.save(convertToEntity(newQuote));
        return convertToDto(save);
    }

    public QuoteDto getQuote(Long id){
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
        return convertToDto(quote);
    }

    public QuoteDto updateQuote(QuoteDto newQuote, Long id) {
       return quoteRepository.findById(id)
                .map(quote -> {
                    quote.setAuthorFirstName(newQuote.getAuthorFirstName());
                    quote.setAuthorLastName(newQuote.getAuthorLastName());
                    quote.setContent(newQuote.getContent());
                    return convertToDto(quoteRepository.save(quote));
                })
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }

    public void deleteQuote(Long id) {
        if(!quoteRepository.existsById(id)){
            throw new QuoteNotFoundException(id);
        }
        quoteRepository.deleteById(id);
    }

    private QuoteDto convertToDto(Quote post) {
        QuoteDto quoteDto = modelMapper.map(post, QuoteDto.class);
        return quoteDto;
    }

    private Quote convertToEntity(QuoteDto quoteDto) {
        Quote quote = modelMapper.map(quoteDto, Quote.class);
        return quote;
    }
}
