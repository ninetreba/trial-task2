package com.example.trial.task.services;

import com.example.trial.task.exception.BusinessRuntimeException;
import com.example.trial.task.exception.ErrorCodeEnum;
import com.example.trial.task.models.*;
import com.example.trial.task.repositories.PostRatingRepository;
import com.example.trial.task.repositories.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@RequiredArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final PostRatingRepository postRatingRepository;
    private final PersonServiceImpl personService;


    public List<QuoteSQL> getWorst5Quotes(){
        return quoteRepository.getWorst5Quotes();
    }

    public List<QuoteSQL> getTop5Quotes(){
        return quoteRepository.getTop5Quotes();
    }

    public void vote(QuoteRatingDto quoteRatingDto, boolean like) {
        Person person = personService.findById(quoteRatingDto.getIdPerson());

        Quote quote = getQuote(quoteRatingDto.getIdQuote());

        QuoteRating rating = postRatingRepository.findUserRating(quoteRatingDto.getIdQuote(), person.getId());

        if (rating != null) {
            throw new BusinessRuntimeException(ErrorCodeEnum.CANNOT_VOTE_MORE_THAN_ONCE);
        }

        rating = new QuoteRating();
        rating.setPerson(person);
        rating.setValue(like ? QuoteRating.LIKE_VALUE : QuoteRating.DISLIKE_VALUE);
        rating.setQuote(quote);

        postRatingRepository.save(rating);
    }


    public List<Quote> getQuotes(){
        return quoteRepository.findAll();
    }

    public Quote getQuote(Long id) {
        return quoteRepository.findById(id).orElseThrow(() ->
                new BusinessRuntimeException(ErrorCodeEnum.QUOTE_NOT_FOUND));
    }

    public Quote getRandomQuote() {
        Random random = new Random();
        Long randomQuoteId = random.nextLong(quoteRepository.count()) + 1;
        return getQuote(randomQuoteId);
    }

    public Quote createQuote(QuoteDto quoteDto) {
        Person person = personService.findById(quoteDto.getPersonId());

        Quote quote = new Quote();
        quote.setDateOfCreation(LocalDateTime.now());
        quote.setContent(quoteDto.getContent());
        quote.setPersonPosted(person);

        quoteRepository.save(quote);
        return quote;
    }

    public Quote updateQuote(QuoteDto quoteDto, long id) {
        Quote oldQuote = quoteRepository.findById(id).orElseThrow(
                () -> new BusinessRuntimeException(ErrorCodeEnum.QUOTE_NOT_FOUND));

        Person person = personService.findById(quoteDto.getPersonId());

        oldQuote.setContent(quoteDto.getContent());
        oldQuote.setPersonPosted(person);

        quoteRepository.save(oldQuote);
        return oldQuote;
    }


    public void deleteQuote(Long id) {
        quoteRepository.findById(id).orElseThrow(() ->
                new BusinessRuntimeException(ErrorCodeEnum.QUOTE_NOT_FOUND));
        quoteRepository.deleteById(id);
    }



}
