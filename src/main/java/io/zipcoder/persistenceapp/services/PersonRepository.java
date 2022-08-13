package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {
    @Query ("SELECT p FROM Person p WHERE p.mobile = :phoneNumber")
    Person findPersonByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    @Query ("SELECT p FROM Person p WHERE p.lastName = :surname")
    Iterable<Person> findAllByLastName(@Param("surname") String lastName);
}
