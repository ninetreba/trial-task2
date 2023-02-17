package com.example.trial.task.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Quote_Rating",
        uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}))
public class QuoteRating {

    public static final short LIKE_VALUE = 1;
    public static final short DISLIKE_VALUE = -1;

    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "val")
    private int value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Quote quote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    protected Person person;


    public QuoteRating(Person person, short value, Quote quote) {
        this.value = value;
        this.person = person;
        this.quote = quote;
    }

    public QuoteRating() {

    }


}