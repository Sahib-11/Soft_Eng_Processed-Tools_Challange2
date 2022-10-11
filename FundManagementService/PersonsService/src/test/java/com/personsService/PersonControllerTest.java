package com.personsService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personsService.controller.PersonController;
import com.personsService.model.Person;
import com.personsService.services.PersonService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @MockBean
    PersonService personService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfindAll() throws Exception {
        Person person = new Person(01L,"MyName", "address", "3000", 20, "dev", "email@mail.com", "99012912");
        List<Person> persons = Arrays.asList(person);

        Mockito.when(personService.findAllPersons()).thenReturn(persons);

        mockMvc.perform(get("/person/persons"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", Matchers.hasSize(1)))
                        .andExpect(jsonPath("$[0].name", Matchers.is("MyName")));
    }

    @Test
    public void getPerson() throws Exception {
        Person person2 = new Person(02L,"MyName2", "address", "3000", 20, "dev", "email@mail.com", "99012912");

        Mockito.when(personService.findPersonById(2)).thenReturn(person2);

        mockMvc.perform(get("/person/{id}", 2))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", Matchers.is("MyName2")));
    }

    @Test
    public void createPerson() throws Exception {
        Person person3 = new Person(01L,"MyName3", "address", "3000", 20, "dev", "email@mail.com", "99012912");

        Mockito.when(personService.savePerson(person3)).thenReturn(person3);

        mockMvc.perform(post("/person/person")
                        .content(asJsonString(person3))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", Matchers.is("MyName3")));

    }

    @Test
    public void updatePerson() throws Exception {
        Person person4 = new Person(01L,"MyName4Update", "address", "3000", 20, "dev", "email@mail.com", "99012912");

        Mockito.when(personService.updatePerson(1, person4)).thenReturn(person4);

        mockMvc.perform(put("/person/{id}", 1)
                        .content(asJsonString(person4))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.name", Matchers.is("MyName4Update")));

    }

    @Test
    public void deletePersonById() throws Exception {

        Mockito.when(personService.deletePersonById(1)).thenReturn(true);


        mockMvc.perform(delete("/person/{id}", 1))
                .andExpect(status().isAccepted());

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
