package com.tomaszdebski.decerto.controller;

import com.tomaszdebski.decerto.dto.QuoteDto;
import com.tomaszdebski.decerto.service.QuoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public List<QuoteDto> allQuotes(@RequestParam Integer pageNo,
                                    @RequestParam Integer pageSize) {
        return quoteService.getAllQuote(pageNo, pageSize);
    }

    @PostMapping
    public QuoteDto addQuote(@RequestBody QuoteDto newQuote) {
        return quoteService.addQuote(newQuote);
    }

    @GetMapping("/{id}")
    public QuoteDto getQuote(@PathVariable Long id) {
        return quoteService.getQuote(id);
    }

    @PutMapping("/{id}")
    public QuoteDto updateQuote(@RequestBody QuoteDto newQuote, @PathVariable Long id) {
        return quoteService.updateQuote(newQuote, id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
    }

}
