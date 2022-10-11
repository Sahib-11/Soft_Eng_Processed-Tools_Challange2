package com.personsService.dao;

import com.personsService.model.Person;
import com.personsService.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonDAOImpl implements PersonDAO{

    @Autowired
    private PersonRepo personRepo;

    /**
     * Finds all persons
     * @return
     */
    @Override
    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }

    /**
     * Finds person by taking id as input
     * @param id
     * @return
     */
    @Override
    public Person findPersonById(long id) {
        Optional<Person> personObj = personRepo.findById(id);
        if(personObj != null) {
            return personObj.get();
        }
        return null;
    }

    /**
     * Saves person
     * @param person
     * @return
     */
    @Override
    public Person saveTPerson(Person person) {
       Person createPerson = personRepo
               .save(Person.builder()
                       .name(person.getName())
                       .address(person.getAddress())
                       .postcode(person.getPostcode())
                       .age(person.getAge())
                       .job(person.getJob())
                       .email(person.getEmail())
                       .phoneNo(person.getPhoneNo())
                       .build());
        return createPerson;
    }

    /**
     * Updates person
     * @param id
     * @param person
     * @return
     */
    public Person updateTPerson(long id, Person person) {
        Optional<Person> personObj = personRepo.findById(id);
        if(personObj != null) {
            Person updatePerson = personObj.get();
            updatePerson.setName(person.getName());
            updatePerson.setAddress(person.getAddress());
            updatePerson.setPostcode(person.getPostcode());
            updatePerson.setAge(person.getAge());
            updatePerson.setJob(person.getJob());
            updatePerson.setEmail(person.getEmail());
            updatePerson.setPhoneNo(person.getPhoneNo());
            personRepo.save(updatePerson);
            return updatePerson;
        }
        return null;
    }


    /**
     * Deletes person by taking id as input
     * @param id
     * @return
     */
    @Override
    public boolean deletePersonById(long id) {
        Optional<Person> personObj = personRepo.findById(id);
        if(personObj != null) {
            personRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
