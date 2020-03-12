package com.galvanize.controllers;

import com.galvanize.entities.Person;
import com.galvanize.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;
//    @GetMapping("/hello")
//    public Person createPerson(@RequestParam String name,
//                               @RequestParam String email,
//                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate) {
//
//        return new Person(name, email, birthDate);
//    }

    // CREATE
    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    // READ
    @GetMapping
    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable Long id){
        return personRepository.findById(id);
    }

    // UPDATE
    @PatchMapping("/person/{id}")
    public Person updatePerson(@RequestParam String email, @PathVariable Long id) {
        return personRepository.updateEmail(id, email);
    }

    // DELETE
    @DeleteMapping("/person/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        boolean deleted = personRepository.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound()
                    .header("error", "ID: " + id + "does not exist")
                    .build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
