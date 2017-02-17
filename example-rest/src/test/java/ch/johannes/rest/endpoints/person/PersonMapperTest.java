package ch.johannes.rest.endpoints.person;

import ch.johannes.example.data.dao.person.Gender;
import ch.johannes.example.data.dao.person.Person;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonMapperTest {

    @Test
    public void apply() throws Exception {
        //arrange
        Person person = new Person(Gender.FEMALE, "Vera", "Hinterseen");
        PersonMapper mapper = new PersonMapper();

        //act
        final PersonTO personTO = mapper.apply(person);

        //assert
        assertThat(personTO.getGender(), CoreMatchers.is("FEMALE"));
        assertThat(personTO.getFirstname(), CoreMatchers.is("Vera"));
        assertThat(personTO.getLastname(), CoreMatchers.is("Hinterseen"));

    }

}