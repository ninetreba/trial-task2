package com.example.trial.task.services;

import com.example.trial.task.models.QuoteDto;
import com.example.trial.task.models.QuoteRatingDto;
import com.example.trial.task.models.Quote;
import com.example.trial.task.models.QuoteSQL;

import java.util.List;

public interface QuoteService {

    public List<QuoteSQL> getWorst5Quotes();
    public List<QuoteSQL> getTop5Quotes();
    public void deleteQuote(Long id);
    public Quote updateQuote(QuoteDto quoteDto, long id);
    public Quote createQuote(QuoteDto quoteDto);
    public Quote getRandomQuote();
    public Quote getQuote(Long id);
    public List<Quote> getQuotes();
    public void vote(QuoteRatingDto quoteRatingDto, boolean like);

}
