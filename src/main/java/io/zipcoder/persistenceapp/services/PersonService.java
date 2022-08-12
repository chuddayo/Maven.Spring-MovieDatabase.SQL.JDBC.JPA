package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
