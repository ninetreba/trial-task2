package com.example.trial.task.services;

import com.example.trial.task.models.Person;

import java.util.List;

public interface PersonService {
    public List<Person> getAll();
    public Person findByUsername(String username);
    public Person findById(Long id);
    public void delete(Long id);

}
