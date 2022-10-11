package com.personsService.services;

import com.personsService.dao.PersonDAO;
import com.personsService.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Transactional
    public List<Person> findAllPersons() {
        return personDAO.getAllPerson();
    }

    @Transactional
    public Person findPersonById(long id) {
        return personDAO.findPersonById(id);
    }

    @Transactional
    public Person savePerson(Person person) {
        return personDAO.saveTPerson(person);
    }

    @Transactional
    public Person updatePerson(long id, Person person) {
        return personDAO.updateTPerson(id, person);
    }

    @Transactional
    public boolean deletePersonById(long id) {
        return personDAO.deletePersonById(id);
    }
}
