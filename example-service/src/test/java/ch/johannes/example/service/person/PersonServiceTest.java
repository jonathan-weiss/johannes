package ch.johannes.example.service.person;

import ch.johannes.example.data.dao.person.Gender;
import ch.johannes.example.data.dao.person.Person;
import ch.johannes.example.data.dao.person.PersonDao;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PersonServiceTest {

    @Test
    public void getAllPerson() throws Exception {

        //arrange
        Person person = new Person(Gender.FEMALE, "Vera", "Hinterseen");
        PersonDao personDao = Mockito.mock(PersonDao.class);
        Mockito.when(personDao.getAllPerson()).thenReturn(Collections.singletonList(person));
        PersonService personService = new PersonService(personDao);

        //act
        final List<Person> allPerson = personService.getAllPerson();

        //assert
        assertThat(allPerson.size(), CoreMatchers.is(1));
        assertThat(allPerson.get(0).getGender(), CoreMatchers.is(Gender.FEMALE));
        assertThat(allPerson.get(0).getFirstname(), CoreMatchers.is("Vera"));
        assertThat(allPerson.get(0).getLastname(), CoreMatchers.is("Hinterseen"));

    }

}