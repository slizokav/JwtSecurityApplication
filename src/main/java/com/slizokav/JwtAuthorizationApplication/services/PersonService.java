package com.slizokav.JwtAuthorizationApplication.services;

import com.slizokav.JwtAuthorizationApplication.model.Person;
import com.slizokav.JwtAuthorizationApplication.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(Person person) {
        String password = passwordEncoder.encode(person.getPassword());
        person.setPassword(password);
        personRepository.save(person);
    }


}
