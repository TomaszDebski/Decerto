package com.tomaszdebski.decerto.utility;

import com.tomaszdebski.decerto.entity.Quote;
import com.tomaszdebski.decerto.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Utility {

    @Autowired
    QuoteRepository quoteRepository;

    @PostConstruct
    public void method(){
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName1").withAuthorLastName("LastName1").withContent("content1").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName2").withAuthorLastName("LastName2").withContent("content2").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName3").withAuthorLastName("LastName3").withContent("content3").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName4").withAuthorLastName("LastName4").withContent("content4").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName5").withAuthorLastName("LastName5").withContent("content5").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName6").withAuthorLastName("LastName6").withContent("content6").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName7").withAuthorLastName("LastName7").withContent("content7").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName8").withAuthorLastName("LastName8").withContent("content8").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName9").withAuthorLastName("LastName9").withContent("content9").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName10").withAuthorLastName("LastName10").withContent("content10").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName11").withAuthorLastName("LastName11").withContent("content11").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName12").withAuthorLastName("LastName12").withContent("content12").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName13").withAuthorLastName("LastName13").withContent("content13").build());
        quoteRepository.save(Quote.builder().withAuthorFirstName("firstName14").withAuthorLastName("LastName14").withContent("content14").build());
    }
}
