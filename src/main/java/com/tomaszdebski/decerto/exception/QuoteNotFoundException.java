package com.tomaszdebski.decerto.exception;

public class QuoteNotFoundException extends RuntimeException{

    public QuoteNotFoundException(Long id) {
        super("Could not find quote " + id);
    }
}
