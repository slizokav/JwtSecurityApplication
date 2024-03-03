package com.slizokav.JwtAuthorizationApplication.services;

import com.slizokav.JwtAuthorizationApplication.model.Person;
import com.slizokav.JwtAuthorizationApplication.repositories.PersonRepository;
import com.slizokav.JwtAuthorizationApplication.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService implements UserDetailsService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByUsername(username);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь с таким именем не найден");
        }
        return new PersonDetails(person.get());
    }
}
