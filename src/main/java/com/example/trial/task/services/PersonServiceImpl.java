package com.example.trial.task.services;

import com.example.trial.task.exception.BusinessRuntimeException;
import com.example.trial.task.exception.ErrorCodeEnum;
import com.example.trial.task.models.Person;
import com.example.trial.task.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        List<Person> persons = personRepository.findAll();
        log.info("IN getAll - {} persons found", persons.size());
        return persons;
    }


    @Override
    public Person findByUsername(String username) {
        Person result = personRepository.findByUsername(username);
         log.info("IN findByUsername - person: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public Person findById(Long id) {
        Person result = personRepository.findById(id).orElseThrow(() -> new BusinessRuntimeException(ErrorCodeEnum.USER_NOT_FOUND));

        log.info("IN findById - person: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
        log.info("IN delete - person with id: {} successfully deleted");
    }


}
