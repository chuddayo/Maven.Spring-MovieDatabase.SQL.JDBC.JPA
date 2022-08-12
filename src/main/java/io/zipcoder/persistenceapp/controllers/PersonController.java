package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service){
        this.service = service;
    }

    // POST /people - create person
    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(service.add(person), HttpStatus.CREATED);
    }

    // PUT /people - update person
    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable Integer id) {
        return new ResponseEntity<>(service.update(person, id), HttpStatus.OK);
    }

    // GET /people/{id} - get person by ID
    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Integer id){
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    // DELETE /people/{id} - delete person with given id
    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Integer id) {
        return new ResponseEntity<>(service.remove(id),HttpStatus.OK);
    }
}
