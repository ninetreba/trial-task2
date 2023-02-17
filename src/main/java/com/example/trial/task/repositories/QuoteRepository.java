package com.example.trial.task.repositories;

import com.example.trial.task.models.Quote;
import com.example.trial.task.models.QuoteSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query("select new com.example.trial.task.models.QuoteSQL(pr.quote.id, count(pr.Id)) from QuoteRating pr group by pr.quote.id, pr.value" +
            " having pr.value = -1 order by count(pr.Id) desc limit 5")
    List<QuoteSQL> getWorst5Quotes();

    @Query("select new com.example.trial.task.models.QuoteSQL(pr.quote.id, count(pr.Id)) from QuoteRating pr group by pr.quote.id, pr.value" +
            " having pr.value = 1 order by count(pr.Id) desc limit 5")
    List<QuoteSQL> getTop5Quotes();

}

