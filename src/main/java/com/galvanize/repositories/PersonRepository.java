package com.galvanize.repositories;

import com.galvanize.entities.Person;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {
    List<Person> people = new ArrayList<>();

    Long nextId = 10L;

    {
        people.add(new Person(nextId++, "Al","simmons@mercgov.gov", LocalDate.of(1992, 05, 15)));
        people.add(new Person(nextId++, "Jason", "wynn@mercgov.gov", LocalDate.of(1992, 05, 15)));
        people.add(new Person(nextId++, "Todd", "mcfarlane@mcfarlane.com", LocalDate.of(1961, 03, 16)));
    }

    // CREATE
    public Person save(Person person) {
        person.setId(nextId++);
        people.add(person);
        return person;
    }

    public List<Person> findAll() {
        return people;
    }

    public Person findById(Long id) {
        for (Person p : people) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Person updateEmail(Long id, String email) {
        Person p = findById(id);
        if (p != null) {
            p.setEmail(email);
        } else {
            throw new RuntimeException("No person found.");
        }

        return p;
    }

    public boolean delete(Long id) {
        Person p = findById(id);
        if (p != null) {
            people.remove(p);
            return true;
        } else {
            return false;
        }
    }
}
