package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository){
        this.repository = repository;
    }

    public Person add(Person person){
        return repository.save(person);
    }

    public Person update(Person person, Integer id) {
        Person updatedPerson = repository.findOne(id);
        updatedPerson.setBirthday(person.getBirthday());
        updatedPerson.setFirstName(person.getFirstName());
        updatedPerson.setLastName(person.getLastName());
        updatedPerson.setMobile(person.getMobile());
        updatedPerson.setHomeId(person.getHomeId());
        return repository.save(updatedPerson);
    }

    public Person getById(Integer id){
        return repository.findOne(id);
    }

    public Boolean remove(Integer id) {
        repository.delete(id);
        return true;
    }

    public Iterable<Person> getPeople() {
        return repository.findAll();
    }

    public Person getByPhoneNumber(String phoneNumber) {
        return repository.findPersonByPhoneNumber(phoneNumber);
    }

    public Iterable<Person> getPeopleByLastName(String lastName) {
        return repository.findAllByLastName(lastName);
    }

    public Map<String, Integer> getFirstNameFrequencies() {
        Iterable<Person> personIterable = repository.findAll();
        Map<String, Integer> freqMap = new HashMap<>();
        for (Person p : personIterable) {
            if (freqMap.containsKey(p.getFirstName())) {
                freqMap.put(p.getFirstName(), 1 + freqMap.get(p.getFirstName()));
            } else {
                freqMap.put(p.getFirstName(), 1);
            }
        }
        return freqMap;
    }
}
