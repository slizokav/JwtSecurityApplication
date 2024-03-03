package com.slizokav.JwtAuthorizationApplication.repositories;

import com.slizokav.JwtAuthorizationApplication.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String name);
}
