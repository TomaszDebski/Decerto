package com.tomaszdebski.decerto.controller;

import com.tomaszdebski.decerto.entity.Quote;
import com.tomaszdebski.decerto.exception.QuoteNotFoundException;
import com.tomaszdebski.decerto.repository.QuoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuoteController {

    private final QuoteRepository quoteRepository;

    public QuoteController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @GetMapping("/quotes")
    public List<Quote> allQuotes() {
        return quoteRepository.findAll();
    }

    @PostMapping("/quotes")
    public Quote newEmployee(@RequestBody Quote newQuote) {
        return quoteRepository.save(newQuote);
    }

    @GetMapping("/quotes/{id}")
    Quote getQuote(@PathVariable Long id) {
        return quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }

    @PutMapping("/quotes/{id}")
    Quote updateQuote(@RequestBody Quote newQuote, @PathVariable Long id) {

        return quoteRepository.findById(id)
                .map(quote -> {
                    quote.setAuthorFirstName(newQuote.getAuthorFirstName());
                    quote.setAuthorLastName(newQuote.getAuthorLastName());
                    quote.setContent(newQuote.getContent());
                    return quoteRepository.save(quote);
                })
                .orElseGet(() -> {
                    newQuote.setId(id);
                    return quoteRepository.save(newQuote);
                });
    }

    @DeleteMapping("/quotes/{id}")
    void deleteQuote(@PathVariable Long id) {
        quoteRepository.deleteById(id);
    }
}
