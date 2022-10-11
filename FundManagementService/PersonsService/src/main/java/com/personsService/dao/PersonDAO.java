package com.personsService.dao;

import com.personsService.model.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> getAllPerson();

    Person findPersonById(long id);

    Person saveTPerson(Person person);

    Person updateTPerson(long id, Person person);

    boolean deletePersonById(long id);
}
