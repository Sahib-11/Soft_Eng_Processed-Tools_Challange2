package com.personsService.controller;

import com.personsService.model.Person;
import com.personsService.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * Return persons list
     * @return
     */
    @GetMapping(path = "/persons")
    public ResponseEntity<List<Person>> findAll(){
        try {
            return new ResponseEntity<>(personService.findAllPersons(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Returns person object by taking id as input
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
        try {
            Person person = personService.findPersonById(id);

            // Returning data
            if (person != null) {
                return new ResponseEntity<>(person, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new person
     * @param person
     * @return
     */
    @PostMapping(path = "/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createPerson = personService.savePerson(person);
        return new ResponseEntity<>(createPerson, HttpStatus.OK);
    }

    /**
     * Updates person
     * @param id
     * @param person
     * @return
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Person updatePerson = personService.updatePerson(id, person);
        if(updatePerson != null) {
            return new ResponseEntity<>(updatePerson, HttpStatus.CREATED);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


        /**
         * Deletes person by taking in id as input
         * @param id
         * @return
         */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deletePersonById(@PathVariable("id") long id) {
        try {
            boolean deleted = personService.deletePersonById(id);

            // Deleted person response
            if (deleted == true) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
