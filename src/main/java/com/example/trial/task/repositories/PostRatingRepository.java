package com.example.trial.task.repositories;

import com.example.trial.task.models.QuoteRating;
import com.example.trial.task.models.QuoteSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRatingRepository extends JpaRepository<QuoteRating, Long> {

    @Query("SELECT r FROM QuoteRating r WHERE r.quote.id = :postId AND r.person.id = :userId")
    QuoteRating findUserRating(@Param("postId") Long postId, @Param("userId") Long userId);

}
