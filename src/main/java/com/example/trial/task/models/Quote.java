package com.example.trial.task.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "QUOTE", schema = "PUBLIC")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Quote")
    private Long id;

    private String content;

    @Column(name = "DATE_OF_CREATION")
    private LocalDateTime dateOfCreation;


    // дочерняя сущность

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_Posted", referencedColumnName = "ID_PERSON")
    private Person personPosted;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quote")
    private List<QuoteRating> quoteRatings = new ArrayList<>();



}
