package com.example.trial.task.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuoteSQL {
    private Long like;
    private Long post_id;

    public QuoteSQL(Long post_id, Long like) {
        this.like = like;
        this.post_id = post_id;
    }

}
