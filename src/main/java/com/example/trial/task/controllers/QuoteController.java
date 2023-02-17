package com.example.trial.task.controllers;

import com.example.trial.task.models.QuoteDto;
import com.example.trial.task.models.QuoteRatingDto;
import com.example.trial.task.models.Quote;
import com.example.trial.task.models.QuoteSQL;
import com.example.trial.task.services.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/quote")
public class QuoteController {
    private final QuoteService quoteService;


    @GetMapping
    public List<Quote> getQuotes(){
        return quoteService.getQuotes();
    }

    @GetMapping("/getTop5Quotes")
    public List<QuoteSQL> getTop5Quotes(){
        return quoteService.getTop5Quotes();
    }

    @GetMapping("/getWorst5Quotes")
    public List<QuoteSQL> getWorst5Quotes(){
        return quoteService.getWorst5Quotes();
    }

    @PostMapping("/rate/like")
    public ResponseEntity<String> like(@RequestBody QuoteRatingDto quoteRatingDto) {
        quoteService.vote(quoteRatingDto, true);
        return new ResponseEntity<String>("ok.", HttpStatus.OK);
    }

    @PostMapping("/rate/dislike")
    public ResponseEntity<String> dislike(@RequestBody QuoteRatingDto quoteRatingDto) {
        quoteService.vote(quoteRatingDto, false);
        return new ResponseEntity<String>("ok.", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable("id") long id){
        return new ResponseEntity<Quote>(quoteService.getQuote(id), HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<Quote> getRandomQuote() {
        return new ResponseEntity<Quote>(quoteService.getRandomQuote(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody QuoteDto quoteDto) {
        return new ResponseEntity<Quote>(quoteService.createQuote(quoteDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable("id") long id,@RequestBody QuoteDto quoteDto) {
        return new ResponseEntity<Quote>(quoteService.updateQuote(quoteDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuote(@PathVariable("id") Long id) {
        quoteService.deleteQuote(id);
        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }


}
