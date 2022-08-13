package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService service){
        this.personService = service;
    }

    // POST /people - create person
    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.add(person), HttpStatus.CREATED);
    }

    // PUT /people - update person
    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable Integer id) {
        return new ResponseEntity<>(personService.update(person, id), HttpStatus.OK);
    }

    // GET /people/{id} - get person by ID
    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Integer id){
        return new ResponseEntity<>(personService.getById(id),HttpStatus.OK);
    }

    // DELETE /people/{id} - delete person with given id
    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Integer id) {
        return new ResponseEntity<>(personService.remove(id),HttpStatus.OK);
    }

    // GET /people - get all people
    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getAllPerson() {
        return new ResponseEntity<>(personService.getPeople(),HttpStatus.OK);
    }

    // GET /people/reverselookup/{mobileNumber} - get Person by phone number
    @GetMapping("/people/reverselookup/{mobileNumber}")
    public ResponseEntity<Person> getPersonByPhone(@PathVariable String mobileNumber) {
        return new ResponseEntity<>(personService.getByPhoneNumber(mobileNumber), HttpStatus.OK);
    }

    // GET /people/surname/{lastName} - get People with given last name
    @GetMapping("/people/surname/{lastName}")
    public ResponseEntity<Iterable<Person>> getPeopleByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(personService.getPeopleByLastName(lastName), HttpStatus.OK);
    }

    // GET /people/firstname/stats
    @GetMapping("/people/firstname/stats")
    public ResponseEntity<Map<String, Integer>> getFirstNameFreq() {
        return new ResponseEntity<>(personService.getFirstNameFrequencies(), HttpStatus.OK);
    }
}
