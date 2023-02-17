package com.example.trial.task.models;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERSON", schema = "PUBLIC")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSON")
    private Long id;

    private String username;
    private String name;
    private String email;
    private String password;

    @Column(name = "DATE_OF_CREATION")
    private LocalDateTime dateOfCreation;


    // родит сущность
    @OneToMany(mappedBy = "personPosted")
    private List<Quote> quotes;


}
